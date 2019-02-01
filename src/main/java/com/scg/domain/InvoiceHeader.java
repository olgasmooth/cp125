/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;

import com.scg.util.Address;

/**
 * @author olgas
 * Header for Small Consulting Group Invoices.
 */
public final class InvoiceHeader {
 private String businessName;
 Address businessAddress;
 ClientAccount client;
 LocalDate invoiceDate;
 LocalDate invoiceForMonth;
	
	//Construct an InvoiceHeader.
	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, LocalDate invoiceDate, LocalDate invoiceForMonth) 
	{
	   this.businessAddress = businessAddress;
	   this.businessName = businessName;
	   this.client = client;
	   this.invoiceDate = invoiceDate;
	   this.invoiceForMonth = invoiceForMonth;
	}
	
	//Print this InvoiceHeader.
	public String toString() {
		return String.format("%s %s %s %s %s", this.businessName, this.businessAddress, this.client, this.invoiceDate.toString(), this.invoiceForMonth.toString());
		
	}
	
	
}
