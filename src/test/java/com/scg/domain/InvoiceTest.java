/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
class InvoiceTest {
	Invoice invoice;
	PersonalName name;
	Consultant consultant;
	ClientAccount client;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		client = new ClientAccount("KitKat", null);
		Month invoiceMonth = LocalDate.now().getMonth();
		invoice = new Invoice(client, invoiceMonth, 2017);
		name = new PersonalName("LastN", "FirstN");
		consultant = new Consultant(name);
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.Invoice#addLineItem(com.scg.domain.InvoiceLineItem)}.
	 */
	@Test
	final void testAddLineItem() {
		InvoiceLineItem item = new InvoiceLineItem(LocalDate.now(), consultant, Skill.PROJECT_MANAGER, 1);
		invoice.addLineItem(item);
		assertEquals(1, invoice.getTotalHours());
		assertEquals(150, invoice.getTotalCharges());
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.Invoice#extractLineItems(com.scg.domain.TimeCard)}.
	 */
	@Test
	final void testExtractLineItems() {
		TimeCard tc = new TimeCard(consultant, LocalDate.now());
		invoice.extractLineItems(tc);
		assertEquals(0, invoice.getTotalHours());
		assertEquals(0, invoice.getTotalCharges());
		
		
		ConsultantTime consultantTime = new ConsultantTime(LocalDate.now(), client, Skill.SYSTEM_ARCHITECT, 8);
		tc.addConsultantTime(consultantTime);
		invoice.extractLineItems(tc);
		assertEquals(8, invoice.getTotalHours());
		assertEquals(1600, invoice.getTotalCharges());
		
		tc = new TimeCard(consultant, LocalDate.now());
		consultantTime = new ConsultantTime(LocalDate.now(), client, Skill.SOFTWARE_ENGINEER, 8);
		tc.addConsultantTime(consultantTime);
		invoice.extractLineItems(tc);
		assertEquals(16, invoice.getTotalHours());
		assertEquals(2800, invoice.getTotalCharges());
	}

	/**
	 * Test method for totalCharges() and totalHours()}.
	 */
	@Test
	final void testGetTotals() {
		InvoiceLineItem item = new InvoiceLineItem(LocalDate.now(), consultant, Skill.PROJECT_MANAGER, 1);
		invoice.addLineItem(item);
		item = new InvoiceLineItem(LocalDate.now(), consultant, Skill.SYSTEM_ARCHITECT, 10);
		invoice.addLineItem(item);

		assertEquals(11, invoice.getTotalHours());
		assertEquals(2150, invoice.getTotalCharges());

	}

}
