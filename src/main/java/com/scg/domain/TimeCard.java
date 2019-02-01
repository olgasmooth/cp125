/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a time card capable of storing a collection of a consultant's
 * billable and non-billable hours for a week. The TimeCard maintains a
 * collection of ConsultantTime, and provides access to number of hours and time
 * billed to a particular client.
 * 
 * @author olgas
 *
 */
public class TimeCard {
	Consultant consultant; // The Consultant whose information this TimeCard records.
	LocalDate date; // The date of the first work day of the week this TimeCard records information
					// for.
	public List<ConsultantTime> consultantHours = new ArrayList<ConsultantTime>();

	// Creates a new instance of TimeCard
	public TimeCard(Consultant consultant, LocalDate weekStartingDay) {
		this.consultant = consultant;
		this.date = weekStartingDay;
	}

	// Add a ConsultantTime object to the collection maintained by this TimeCard.
	public void addConsultantTime(ConsultantTime consultantTime) {
		this.consultantHours.add(consultantTime);
	}

	// Returns the billable hours (if any) in this TimeCard for the specified
	// Client.
	public List<ConsultantTime> getBillableHoursForClient(String clientName) {
		List<ConsultantTime> clientHours = new ArrayList<ConsultantTime>();
		for (ConsultantTime hours : consultantHours) {
			if (hours.getAccount().getName().equals(clientName) && hours.isBillable()) {
				clientHours.add(hours);
			}
		}
		return clientHours;

	}

	// Getter for consultant property.
	public Consultant getConsultant() {
		return this.consultant;
	}

	// Getter for consultingHours property.
	public List<ConsultantTime> getConsultingHours() {
		return consultantHours;
	}

	// Getter for billableHours property.
	public int getTotalBillableHours() {
		int billableHours = 0;
		for (ConsultantTime time : consultantHours) {
			if (time.isBillable()) {
				billableHours = billableHours + time.getHours();
			}
		}
		return billableHours;

	}

	// Getter for totalHours property.
	public int getTotalHours() {
		int totalHours  = 0;
		for (ConsultantTime time : consultantHours) {
			totalHours = totalHours + time.getHours();
		}
		return totalHours;
	}

	// Getter for totalNonBillableHours property.
	public int getTotalNonBillableHours() {
		int nonBillableHours = 0;
		for (ConsultantTime time : consultantHours) {
			if (!time.isBillable()) {
				nonBillableHours = nonBillableHours + time.getHours();
			}
		}
		return nonBillableHours;
	}

	// Getter for weekStartingDay property.
	public LocalDate getWeekStartingDay() {
		return this.date;
	}

	// Create a string representation of this object, suitable for printing the
	// entire time card.
	public String toReportString() {
		// String date = formatter.format(this.getWeekStartingDay());
		StringBuilder str = new StringBuilder("====================================================================\n");
		
		str.append (
		String.format("Consultant:%s %s %s                    Week Starting: %s\n\n", 
		this.consultant.getName().getLastName(), 
		this.consultant.getName().getFirstName(), 
		this.consultant.getName().getMiddleName(),
		this.getWeekStartingDay().toString()));
		str.append("Billable Time:\n");
		str.append("Account                      		Date        Hours  	Skill\n");
		str.append("-----------------------------------------------------------------\n");
		for (ConsultantTime time : consultantHours) {
			if (time.isBillable()) {
				str.append(String.format("%s 			%s		%d 		%s\n", time.account.getName(), time.getDate().toString(), time.getHours(),  time.getSkill().toString()));
			}
		}
		
		str.append("\nNon-Billable Time:\n");
		str.append("Account                      		Date        Hours  	Skill\n");
		str.append("-----------------------------------------------------------------\n");
		for (ConsultantTime time : consultantHours) {
			if (!time.isBillable()) {
				str.append(String.format("%s 			%s		%d 		%s\n", time.account.getName(), time.getDate().toString(), time.getHours(),  time.getSkill().toString()));
			}
		}
		
		str.append("\nSummary:");
		str.append(String.format("Total Billable: 				%d\n", this.getTotalBillableHours()));
		str.append(String.format("Total Non-Billable:			%d\n", this.getTotalNonBillableHours()));
		str.append(String.format("Total hours:					%d\n", this.getTotalHours()));
		
		str.append("====================================================================\n");
		return str.toString();
		
		
//		return String.format("%s %s %s %s", this.consultant.getName().getLastName(), 
				
	}

	// String representation of this object, consisting of the consultant name and
	// the time card week starting day.
	public String toString() {
		return String.format("%s %s %s %s", this.consultant.getName().getLastName(), 
				this.consultant.getName().getFirstName(), 
				this.consultant.getName().getMiddleName(), 
				this.getWeekStartingDay().getDayOfWeek());
	}

}
