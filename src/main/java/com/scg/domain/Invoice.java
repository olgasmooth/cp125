/**
 * 
 */
package com.scg.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.scg.util.StateCode;
import com.scg.util.Address;

/**
 * Invoice encapsulates the attributes and behavior to create client invoices
 * for a given time period from time cards. The Invoice maintains are collection
 * of invoice line items; each containing date, hours and other billing
 * information, these constitute what is being billed for with this Invoice. The
 * invoice will limit the items billed on it to a single month and also has a
 * separate invoice date which reflects the date the invoice was generated. The
 * invoicing business' name and address are obtained from a properties file. The
 * name of the property file is specified by the PROP_FILE_NAME static member.
 * 
 * @author olgas
 *
 */
public final class Invoice {
	static String companyName; // = "The Small Consulting Group";
	static Address businessAddress;
	static String PROP_FILE_NAME = "invoice.properties";

	ClientAccount client;
	Month invoiceMonth;
	Integer invoiceYear;
	List<InvoiceLineItem> invoiceItems = new ArrayList<InvoiceLineItem>();

	//Construct an Invoice for a client. The time period is set from the beginning to the end of the month specified.
	public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
		this.client = client;
		this.invoiceMonth = invoiceMonth;
		this.invoiceYear = invoiceYear;
	}

	// Add an invoice line item to this Invoice.
	public void addLineItem(InvoiceLineItem lineItem) {
		this.invoiceItems.add(lineItem);

	}

	/*
	 * Extract the billable hours for this Invoice's client from the input TimeCard
	 * and add them to the collection of line items.
	 */
	public void extractLineItems(TimeCard timeCard) {
		/*for (ConsultantTime hours : timeCard.getBillableHoursForClient(this.getClientAccount().getName())) {
			if (hours.getDate().getMonth() == this.getInvoiceMonth()) {
				InvoiceLineItem lineItem = new InvoiceLineItem(hours.getDate(), timeCard.consultant, hours.getSkill(),
						hours.getHours());
				this.addLineItem(lineItem);
			}
		}*/
		
		List<ConsultantTime> hours = timeCard.getBillableHoursForClient(this.getClientAccount().getName());

        List<InvoiceLineItem> lineItems = hours.stream()
                .filter(h -> h.getDate().getMonth() == this.getInvoiceMonth())
                .map(h -> new InvoiceLineItem(h.getDate(), timeCard.consultant, h.getSkill(), h.getHours()))
                .collect(Collectors.toList());

        this.invoiceItems.addAll(lineItems);
	}

	// Get the client for this Invoice.
	public ClientAccount getClientAccount() {
		return client;
	}

	// Get the invoice month.
	public Month getInvoiceMonth() {
		return invoiceMonth;

	}

	// Get the start date for this Invoice, this is the earliest date a
	// ConsultantTime instance may have and still be billed on this invoice.
	public LocalDate getStartDate() {
		return LocalDate.of(this.invoiceYear, this.invoiceMonth, 1);

	}

	// Get the total charges for this Invoice.
	public int getTotalCharges() {
		int total = 0;
		for (InvoiceLineItem lineItem : this.invoiceItems) {
			total += lineItem.getCharge();
		}
		return total;
	}

	// Get the total hours for this Invoice.
	public int getTotalHours() {
		int total = 0;
		for (InvoiceLineItem lineItem : this.invoiceItems) {
			total += lineItem.getHours();
		}
		return total;
	}

	// Create a formatted string containing the printable invoice.
	public String toReportString() {
		this.readCompanyDataFromFile();
		InvoiceHeader header = new InvoiceHeader(companyName, businessAddress, client, LocalDate.now(),
				LocalDate.of(this.invoiceYear, this.getInvoiceMonth(), 1));
		InvoiceFooter footer = new InvoiceFooter(companyName);
		StringBuilder invoiceString = new StringBuilder();

		invoiceString.append(header.toString());
		int lineNumber = 0;
		int currentItem = 0;
		for (InvoiceLineItem lineItem : invoiceItems) {
			invoiceString.append(lineItem.toString());
			currentItem++;
			lineNumber++;
			if (lineNumber == 5) {
				lineNumber = 0;

				if(currentItem != invoiceItems.size()) {
					invoiceString.append("\n\n\n\n");
					invoiceString.append(footer.toString());
					footer.incrementPageNumber();
					invoiceString.append(header.toString());
				}
			}
		}
		invoiceString.append(String.format("\n%-83s %-4d %,.2f", "Total:", this.getTotalHours(), (float)this.getTotalCharges()));
		invoiceString.append("\n\n\n\n");
		invoiceString.append(footer.toString());
		return invoiceString.toString();
	}

	// Create a string representation of this object, suitable for printing.
	public String toString() {
		return this.toReportString();

	}

	private void readCompanyDataFromFile() {
	//	File infile = new File("src\\main\\resources\\invoice.properties");
		String filePath = String.format("src%smain%sresources%sinvoice.properties", File.separator, File.separator, File.separator);
        File infile = new File(filePath);
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(infile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		StringBuilder companyData = new StringBuilder();

		try {
			while (reader.ready()) {
				String str = reader.readLine();
				companyData.append(str.substring(str.indexOf("=") + 1));
				companyData.append('\n');
			}

			reader.close();
			stream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		String companyAddress[];
		companyAddress = companyData.toString().split("\n");

		companyName = companyAddress[0];
		businessAddress = new Address(companyAddress[1], companyAddress[2], StateCode.valueOf(companyAddress[3]),
				companyAddress[4]);
	}
}
