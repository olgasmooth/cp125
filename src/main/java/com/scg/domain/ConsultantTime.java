/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * A consultants time, maintains date, skill, account and hours data. This
 * represent a single time entry on a time card.
 * 
 * @author olgas
 *
 */
public final class ConsultantTime {
	LocalDate date; // The date this instance occurred
	Account account; // The account to charge the hours to; either a Client or NonBillableAccount.
	Skill skillType; // The skill type
	Integer hours; // The number of hours, which must be positive.

	// Creates a new instance of ConsultantTime.
	public ConsultantTime(LocalDate date, Account account, Skill skillType, int hours) {
		this.date = date;
		this.account = account;
		this.skillType = skillType;
		this.hours = hours;
	}

	// Getter for account property.
	public Account getAccount() {
		return this.account;

	}

	// Getter for date property.
	LocalDate getDate() {
		return this.date;

	}

	// Getter for hours property.
	public int getHours() {
		return this.hours;
	}

	// Getter for skill property.
	public Skill getSkill() {
		return this.skillType;
	}

	public int hashCode() {
		return Objects.hash(date, account, skillType);
	}

	// Determines if the time is billable.
	public boolean isBillable() {
		return this.account.isBillable();

	}

	// Setter for account property.
	public void setAccount(Account account) {
		this.account = account;

	}

//	Setter for date property.
	public void setDate(LocalDate date) {
		this.date = date;
	}

//Setter for hours property.
	public void setHours(int hours) {
		this.hours = hours;
	}

	// Creates a string representation of the consultant time.
	public String toString() {
		return String.format("%s %s %d %s", this.account.toString(), this.date.toString(), this.hours, this.skillType);
	}

}
