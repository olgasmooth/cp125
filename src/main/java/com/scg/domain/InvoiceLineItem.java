/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author olgas
 *
 */
public final class InvoiceLineItem {
	LocalDate date; // date the service was provided
	Consultant consultant; // name of consultant providing the service
	Skill skill; // the service/skill provided
	int hours; // number of hours

	// Construct an InvoiceLineItem
	public InvoiceLineItem(LocalDate date, Consultant consultant, Skill skill, int hours) {
		this.date = date;
		this.consultant = consultant;
		this.skill = skill;
		this.hours = hours;
	}

	// Get the charge for this line item.
	public int getCharge(){

		return this.getSkill().getRate() * this.getHours();
	}

	// Get the consultant for this line item.
	public Consultant getConsultant() {
		return this.consultant;
	}

	// Get the date for this line item.
	public LocalDate getDate() {
		return this.date;
	}

	// Get the hours for this line item.
	public int getHours() {
		return this.hours;
	}

	// Get the skill for this line item.
	public Skill getSkill() {
		return this.skill;
	}

	// Print the date, consultant, skill, hours and charge for this line item.
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		return String.format("%-15s %-40s %-20s %8d %,10.2f\n", dateFormatter.format(date), consultant.toString(),
				skill.toString(), this.getHours(), (float)this.getCharge());
	}

}
