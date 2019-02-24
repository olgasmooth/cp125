/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author olgas
 *
 */
class InvoiceFooterTest {

	/**
	 * Test method for {@link com.scg.domain.InvoiceFooter#incrementPageNumber()}.
	 */
	@Test
	final void testIncrementPageNumber() {
		InvoiceFooter footer = new InvoiceFooter(null);
		 assertEquals(1,footer.getPageNumber());
		footer.incrementPageNumber();
	  assertEquals(2,footer.getPageNumber());
		
	}

}
