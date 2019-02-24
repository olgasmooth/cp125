/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
class ClientAccountTest {

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#getName()}.
	 */
	@Test
	final void testGetName() {
		String name = "KitKat";
		ClientAccount ca = new ClientAccount(name, null);
		assertEquals(name, ca.getName());
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#isBillable()}.
	 */
	@Test
	final void testIsBillable() {
		ClientAccount ca = new ClientAccount("CompanyName", null);
		assertTrue(ca.isBillable());
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.ClientAccount#ClientAccount(java.lang.String, com.scg.util.PersonalName)}.
	 */
	@Test // constructor
	final void testClientAccount() {
		PersonalName contact = new PersonalName("LastN", "FirstN");
		String name = "KitKat";
		ClientAccount ca = new ClientAccount(name, contact);
		assertEquals(name, ca.getName());
		assertTrue(contact.equals(ca.getContact()));
	}

	/**
	 * Test method for {@link com.scg.domain.ClientAccount#getContact()}.
	 */
	@Test
	final void testGetContact() {
		PersonalName contact = new PersonalName("LastN", "FirstN");
		ClientAccount ca = new ClientAccount(null, contact);
		assertEquals(contact, ca.getContact());
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.ClientAccount#setContact(com.scg.util.PersonalName)}.
	 */
	@Test
	final void testSetContact() {
		ClientAccount ca = new ClientAccount(null, null);
		PersonalName contact = new PersonalName("LastN", "FirstN");
		ca.setContact(contact);
		assertEquals(contact, ca.getContact());
	}
}
