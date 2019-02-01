/**
 * 
 */
package com.scg.domain;

/**
 * @author olgas
 * 
 *         Footer for Small Consulting Group invoices. The printed footer has
 *         the billing business' name and page number, formatted in a readable
 *         fashion.
 *
 */
public final class InvoiceFooter {
	private String businessName; // name of business to include in footer
	private int pageNumber = 0;

	// Construct an InvoiceFooter.
	public InvoiceFooter(String businessName) {
		this.businessName = businessName;
	}

	// Increment the current page number by one.
	public void incrementPageNumber() {
           pageNumber++;
	}

	// Print the formatted footer.
	public String toString() {
		return this.businessName.toString();
	}

}
