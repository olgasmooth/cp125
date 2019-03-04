package com.scg.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
class TimeCardTest {
	PersonalName personalName;
	Consultant consultant;
	TimeCard tc;
	LocalDate date;
	PersonalName contact;
	String companyName;
	Account account;
	Skill skillType;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		personalName = new PersonalName("consLastN", "consFirstN", "consM");
		consultant= new Consultant(personalName);
		tc = new TimeCard(consultant, LocalDate.now());
				
		date = LocalDate.now();
		contact = new PersonalName("LastN", "FirstN");
		companyName = "KitKat";

		account = new ClientAccount(companyName, contact);

		skillType = Skill.PROJECT_MANAGER;
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#TimeCard(com.scg.domain.Consultant, java.time.LocalDate)}.
	 */
	@Test//????
	final void testTimeCard() {
		assertEquals(tc.consultant, tc.getConsultant());
		assertEquals(tc.date, tc.getWeekStartingDay());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#addConsultantTime(com.scg.domain.ConsultantTime)}.
	 */
	@Test
	final void testAddConsultantTime() {
		ConsultantTime consultantTime = new ConsultantTime(date, account, skillType, 8);
		tc.addConsultantTime(consultantTime);
		
		assertEquals(1, tc.getConsultingHours().size());
		tc.addConsultantTime(consultantTime);
		assertEquals(2, tc.getConsultingHours().size());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getBillableHoursForClient(java.lang.String)}.
	 */
	@Test
	final void testGetBillableHoursForClient() {
		ConsultantTime consultantTime = new ConsultantTime(date, account, skillType, 8);
		tc.addConsultantTime(consultantTime);
		
		List<ConsultantTime>  hours= tc.getBillableHoursForClient(companyName);
		assertEquals(1, hours.size());
		assertEquals(8, hours.get(0).getHours());
		
		ConsultantTime consultantTime2 = new ConsultantTime(date, NonBillableAccount.VACATION, skillType, 10);
		tc.addConsultantTime(consultantTime2);
		
		hours= tc.getBillableHoursForClient(companyName);
		assertEquals(1, hours.size());
		assertEquals(8, hours.get(0).getHours());
		
		ConsultantTime consultantTime3 = new ConsultantTime(date, account, Skill.SYSTEM_ARCHITECT, 16);
		tc.addConsultantTime(consultantTime3);
		
		hours= tc.getBillableHoursForClient(companyName);
		assertEquals(2, hours.size());
		assertEquals(8, hours.get(0).getHours());
		assertEquals(16, hours.get(1).getHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getConsultant()}.
	 */
	@Test //??????
	final void testGetConsultant() {		
		assertEquals(consultant, tc.getConsultant());
	
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getConsultingHours()}.
	 */
	@Test
	final void testGetConsultingHours() {
		//ConsultantTime consultantTime = new ConsultantTime(date, account, Skill.SYSTEM_ARCHITECT, 16);
	//	tc.addConsultantTime(consultantTime);
		//List<ConsultantTime> consultantHours = new ArrayList<ConsultantTime>();
		assertEquals(tc.consultantHours, tc.getConsultingHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalBillableHours()}.
	 */
	@Test//???
	final void testGetTotalBillableHours() {
		ConsultantTime consultantTime2 = new ConsultantTime(date, NonBillableAccount.VACATION, skillType, 10);
		tc.addConsultantTime(consultantTime2);
		ConsultantTime consultantTime3 = new ConsultantTime(date, account, Skill.SYSTEM_ARCHITECT, 16);
		tc.addConsultantTime(consultantTime3);
		assertEquals(16, tc.getTotalBillableHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalHours()}.
	 */
	@Test
	final void testGetTotalHours() {
		ConsultantTime consultantTime2 = new ConsultantTime(date, NonBillableAccount.VACATION, skillType, 10);
	    tc.addConsultantTime(consultantTime2);
		ConsultantTime consultantTime3 = new ConsultantTime(date, account, Skill.SYSTEM_ARCHITECT, 16);
		tc.addConsultantTime(consultantTime3);
		assertEquals(26, tc.getTotalHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getTotalNonBillableHours()}.
	 */
	@Test
	final void testGetTotalNonBillableHours() {
		ConsultantTime consultantTime2 = new ConsultantTime(date, NonBillableAccount.VACATION, skillType, 10);
		tc.addConsultantTime(consultantTime2);
		ConsultantTime consultantTime3 = new ConsultantTime(date, account, Skill.SYSTEM_ARCHITECT, 16);
		tc.addConsultantTime(consultantTime3);
		assertEquals(10, tc.getTotalNonBillableHours());
	}

	/**
	 * Test method for {@link com.scg.domain.TimeCard#getWeekStartingDay()}.
	 */
	@Test
	final void testGetWeekStartingDay() {
		assertEquals(date, tc.getWeekStartingDay());
	}
  
	}
