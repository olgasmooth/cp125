/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;

import com.scg.util.Address;

/**
 * @author olgas Header for Small Consulting Group Invoices.
 */
public final class InvoiceHeader {
	private String businessName="ABC";
	Address businessAddress;
	ClientAccount client;
	LocalDate invoiceDate;
	LocalDate invoiceForMonth;

	// Construct an InvoiceHeader.
	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, LocalDate invoiceDate,
			LocalDate invoiceForMonth) {
		this.businessAddress = businessAddress;
		this.businessName = businessName;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.invoiceForMonth = invoiceForMonth;
	}

	// Print this InvoiceHeader.
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.businessName);
		str.append("\n");
		str.append(this.businessAddress.toString());
		str.append("\n\n");

		str.append(String.format("Invoice for:\n%s\n%s\n%s\n\n", this.client.getName(),
				this.client.getAddress().toString(), this.client.getContact().toString()));
		str.append(String.format("Invoice For Month of: %s %d\n", this.invoiceForMonth.getMonth().toString(),
				this.invoiceForMonth.getYear()));
		str.append(String.format("Invoice Date: %s\n\n", this.invoiceDate.toString()));
		str.append("Date		Consultant				Skill			Hours\nCharge\n");
		str.append("------------   -------------------------------------    --------------------    -------\n");

		return str.toString();
	}
	
	

}
