/**
 * 
 */
package com.scg.domain;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.Thread.State;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.scg.util.Address;
import com.scg.util.StateCode;

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

	// Construct an Invoice for a client. The time period is set from the beginning
	// to the end of the month specified.
	public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
		this.client = client;
		this.invoiceMonth = invoiceMonth;
		this.invoiceYear = invoiceYear;
		this.readCompanyDataFromFile();
		// this.businessAddress = new Address("1616 Index Ct.", "Renton", StateCode.WA,
		// "98055");
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
//	List<ConsultantTime> clientHours = new ArrayList<ConsultantTime>();
		for (ConsultantTime hours : timeCard.getBillableHoursForClient(this.getClientAccount().getName())) {
			if (hours.getDate().getMonth() == this.getInvoiceMonth()) {
				InvoiceLineItem lineItem = new InvoiceLineItem(hours.getDate(), timeCard.consultant, hours.getSkill(),
						hours.getHours());
				this.addLineItem(lineItem);
			}
		}
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

		InvoiceHeader header = new InvoiceHeader(companyName, businessAddress, client, LocalDate.now(),
				LocalDate.of(this.invoiceYear, this.getInvoiceMonth(), 1));
		InvoiceFooter footer = new InvoiceFooter(companyName);
		StringBuilder invoiceString = new StringBuilder();

		invoiceString.append(header.toString());
		int pageNumber = 1;
		for (InvoiceLineItem lineItem : invoiceItems) {
			invoiceString.append(lineItem.toString());

			pageNumber++;
			if (pageNumber % 5 == 0) {
				invoiceString.append("\n\n\n\n");
				invoiceString.append(footer.toString());

				footer.incrementPageNumber();
				invoiceString.append(header.toString());
			}
		}
		invoiceString.append(String.format("\nTotal:\n$%d", this.getTotalCharges()));
		invoiceString.append("\n\n\n\n");
		invoiceString.append(footer.toString());
		return invoiceString.toString();
	}

	// Create a string representation of this object, suitable for printing.
	public String toString() {
		return "Invoice toString";
	}

	private void readCompanyDataFromFile() {
		InputStream stream = ClassLoader.getSystemResourceAsStream(PROP_FILE_NAME);
		Reader reader = new InputStreamReader(stream);
		int i;
		StringBuilder companyData = new StringBuilder();
		String companyAddress[] = { "" };

		try {

			while ((i = reader.read()) != -1) {
				char c = (char) i;
				if (c != '=') {
					continue;
				} else {
					do {
						i = reader.read();
						if (i == -1) {
							break;
						}
						c = (char) i;
						companyData.append(c);

					} while (c != '\n');
				}
			}
			companyAddress = companyData.toString().split(System.lineSeparator()); // "\r\n" for files created on MS
																					// Windows OS

			reader.close();
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		companyName = companyAddress[0];
		businessAddress = new Address(companyAddress[1], companyAddress[2], StateCode.valueOf(companyAddress[3]),
				companyAddress[4]);
	}
}
