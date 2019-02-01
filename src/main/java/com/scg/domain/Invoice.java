/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

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

	ClientAccount client;
	Month invoiceMonth;
	Integer invoiceYear;
	List<InvoiceLineItem> invoiceItems = new ArrayList<InvoiceLineItem>();
//	InvoiceLineItem InvoiceLineItem;

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
		for(InvoiceLineItem lineItem: this.invoiceItems) {
			total += lineItem.getHours();
		}
          return total;
	}

	// Create a formatted string containing the printable invoice.
	public String toReportString() {
		return "toReportString";

	}

	// Create a string representation of this object, suitable for printing.
	public String toString() {
		return  "Invoice toString";

	}

}
