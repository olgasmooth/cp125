/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

/**
 * @author olga
 *
 */
class ConsultantTest {

	/**
	 * Test method for {@link com.scg.domain.Consultant#getName()}.
	 */
	@Test
	final void testGetName() {
		PersonalName name = new PersonalName("LastN", "FirstN");
		Consultant ct = new Consultant(name);
		assertEquals(name, ct.getName());

	}

	/**
	 * Test method for {@link com.scg.domain.Consultant#toString()}.
	 */
	@Test
	final void testToString() {
		PersonalName name = new PersonalName("LastN", "FirstN", "M");
		Consultant ct = new Consultant(name);
		assertEquals("LastN FirstN M", ct.toString());

	}

}
