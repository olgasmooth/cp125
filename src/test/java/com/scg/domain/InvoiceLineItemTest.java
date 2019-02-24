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
class InvoiceLineItemTest {

	/**
	 * Test method for {@link com.scg.domain.InvoiceLineItem#getCharge()}.
	 */
	@Test
	final void testGetCharge() {
		InvoiceLineItem lineItem = new InvoiceLineItem(null, null, Skill.SOFTWARE_ENGINEER, 1);
		assertEquals(150, lineItem.getCharge());
		lineItem = new InvoiceLineItem(null, null, Skill.SYSTEM_ARCHITECT, 1);
		assertEquals(200, lineItem.getCharge());
		lineItem = new InvoiceLineItem(null, null, Skill.SOFTWARE_TESTER, 1);
		assertEquals(100, lineItem.getCharge());
				
	}

}
