/**
 * 
 */
package com.scg.domain;

import java.io.Serializable;

/**
 * Consepts for the account be not billable: sick, vacation, business developent
 * 
 * @author olgas
 *
 */

public enum NonBillableAccount implements Account, Serializable {
	SICK_LEAVE("Sick Leave"), 
	BUSINESS_DEVELOPMENT("Business Development"), 
	VACATION("Vacation");

	public final String accountName;
  
	private NonBillableAccount(String accountName) {
		this.accountName = accountName;
	}

	// gets the name of the account
	public String getName() {
		return this.accountName;
	}

	// true if account is billable, if not billable - false
	public boolean isBillable() {
		return false;
	}

	// Gets not billable account into string format
	@Override
	public String toString() {
		return this.accountName.toString();
	}
}
