/**
 * 
 */
package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
class ConsultantTimeTest {

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#hashCode()}.
	 */
	@Test
	final void testHashCode() {
		LocalDate date = LocalDate.now();
		PersonalName contact = new PersonalName("LastN", "FirstN");
		String name = "KitKat";

		Account account = new ClientAccount(name, contact);

		Skill skillType = Skill.PROJECT_MANAGER;
		ConsultantTime ct1 = new ConsultantTime(date, account, skillType, 8);
		int h1 = ct1.hashCode();

		ConsultantTime ct2 = new ConsultantTime(date, account, skillType, 8);
		int h2 = ct2.hashCode();

		assertEquals(h1, h2);
		
		ConsultantTime ct3 = new ConsultantTime(date, account, Skill.SOFTWARE_ENGINEER, 8);
		int h3 = ct3.hashCode();
		assertNotEquals(h1, h3);
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.ConsultantTime#ConsultantTime(java.time.LocalDate, com.scg.domain.Account, com.scg.domain.Skill, int)}.
	 */
	@Test
	final void testConsultantTime() {
		LocalDate date = LocalDate.now();
		PersonalName contact = new PersonalName("LastN", "FirstN");
		String name = "KitKat";

		Account account = new ClientAccount(name, contact);

		Skill skillType = Skill.PROJECT_MANAGER;
		ConsultantTime ct = new ConsultantTime(date, account, skillType, 8);
		assertEquals(8,ct.getHours());
		assertEquals(Skill.PROJECT_MANAGER, ct.getSkill());
		assertEquals(account, ct.getAccount());
	}
	
	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#getAccount()}.
	 */
	@Test
	final void testGetAccount() {
		PersonalName contact = new PersonalName("LastN", "FirstN");
		Account account = new ClientAccount("KitKat", contact);
		ConsultantTime ct = new ConsultantTime(null, account, null, 8);	
		assertEquals(account, ct.getAccount());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#getDate()}.
	 */
	@Test
	final void testGetDate() {
		LocalDate date = LocalDate.now();
		ConsultantTime ct = new ConsultantTime(date, null, null, 8);
		assertEquals(date, ct.getDate());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#getHours()}.
	 */
	@Test
	final void testGetHours() {
		ConsultantTime ct = new ConsultantTime(null, null, null, 8);
		assertEquals(8, ct.getHours());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#getSkill()}.
	 */
	@Test
	final void testGetSkill() {
		ConsultantTime ct = new ConsultantTime(null, null, Skill.SOFTWARE_TESTER, 8);
		assertEquals(Skill.SOFTWARE_TESTER, ct.getSkill());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#isBillable()}.
	 */
	@Test
	final void testIsBillable() {
		Account account = new ClientAccount(null, null);		
		ConsultantTime ct = new ConsultantTime(null, account, Skill.SOFTWARE_TESTER, 8);
		assertTrue(ct.isBillable());
		
		ConsultantTime ct2 = new ConsultantTime(null, NonBillableAccount.VACATION, Skill.SOFTWARE_TESTER, 8);
		assertFalse(ct2.isBillable());
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.ConsultantTime#setAccount(com.scg.domain.Account)}.
	 */
	@Test
	final void testSetAccount() {			
		ConsultantTime ct = new ConsultantTime(null, null, Skill.SOFTWARE_TESTER, 8);
		String name = "KitKat";
		PersonalName contact = new PersonalName("LastN","FirstN", "M");
		Account account = new ClientAccount(name, contact);	
		ct.setAccount(account);
		assertEquals(account, ct.getAccount());
	}

	/**
	 * Test method for
	 * {@link com.scg.domain.ConsultantTime#setDate(java.time.LocalDate)}.
	 */
	@Test//??????
	final void testSetDate() {
		LocalDate date = LocalDate.now();
		ConsultantTime ct = new ConsultantTime(date, null, null, 0);
		assertEquals(ct.date, ct.getDate());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#setHours(int)}.
	 */
	@Test//???
	final void testSetHours() {
		ConsultantTime ct = new ConsultantTime(null, null, null, 7);
		assertEquals(7, ct.getHours());
	}

	/**
	 * Test method for {@link com.scg.domain.ConsultantTime#toString()}.
	 */
	@Test
	final void testToString() {
		LocalDate date = LocalDate.now();
		PersonalName contact = new PersonalName("LastN", "FirstN", "M");
		String name = "KitKat";

		Account account = new ClientAccount(name, contact);

		Skill skillType = Skill.PROJECT_MANAGER;
		ConsultantTime ct = new ConsultantTime(date, account, skillType, 8);
		String expected = String.format("LastN, FirstN M KitKat true %s 8 PROJECT MANAGER", date.toString());
		assertEquals(expected, ct.toString());
	}

	
}
