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
	private int pageNumber = 1;

	// Construct an InvoiceFooter.
	public InvoiceFooter(String businessName) {
		this.businessName = businessName;
	}

	// Increment the current page number by one.
	public void incrementPageNumber() {
		pageNumber++;
	}

	// for testing
	public int getPageNumber() {
		return this.pageNumber;
	}

	// Print the formatted footer.
	public String toString() {
//		return String.format("%s %67s %2d\n==================================================================================================\n\n",
//				this.businessName.toString(), "", "Page:", this.pageNumber);
		return String.format("%s %67s %2d\n==================================================================================================\n\n",
				this.businessName.toString(), "Page:", this.pageNumber);
	}

}
