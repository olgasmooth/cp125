/**
 * 
 */
package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a time card capable of storing a collection of a consultant's
 * billable and non-billable hours for a week. The TimeCard maintains a
 * collection of ConsultantTime, and provides access to number of hours and time
 * billed to a particular client.
 * 
 * @author olgas
 *
 */
public class TimeCard implements Comparable<TimeCard>, Serializable {
	Consultant consultant; // The Consultant whose information this TimeCard records.
	LocalDate date; // The date of the first work day of the week this TimeCard records information
					// for.
	public List<ConsultantTime> consultantHours = new ArrayList<ConsultantTime>();

	// Creates a new instance of TimeCard
	public TimeCard(Consultant consultant, LocalDate weekStartingDay) {
		this.consultant = consultant;
		this.date = weekStartingDay;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((consultant == null) ? 0 : consultant.hashCode());
		result = prime * result + ((consultantHours == null) ? 0 : consultantHours.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeCard other = (TimeCard) obj;
		if (consultant == null) {
			if (other.consultant != null)
				return false;
		} else if (!consultant.equals(other.consultant))
			return false;
		if (consultantHours == null) {
			if (other.consultantHours != null)
				return false;
		} else if (!consultantHours.equals(other.consultantHours))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	// Add a ConsultantTime object to the collection maintained by this TimeCard.
	public void addConsultantTime(ConsultantTime consultantTime) {
		this.consultantHours.add(consultantTime);
	}

	/**
	 * 	Gets the billable hours (if any) in this TimeCard for the specified Client.
	 * @param clientName - name of the client
	 * @return Billable ConsultantTime List for the specified Client
	 */
	public List<ConsultantTime> getBillableHoursForClient(String clientName) {
		List<ConsultantTime> clientHours = new ArrayList<ConsultantTime>();
//		for (ConsultantTime hours : consultantHours) {
//			if (hours.getAccount().getName().equals(clientName) && hours.isBillable()) {
//				clientHours.add(hours);
//			}
//		}
		clientHours = consultantHours.stream()
				.filter(c -> c.getAccount().getName().equals(clientName) && c.isBillable())
				.collect(Collectors.toList());

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
		int totalHours = 0;
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


	/**
	 * Create a string representation of this object, 
	 * suitable for printing the entire time card.
	 * @return String representation of TimeCard
	 */
	public String toReportString() {
		DateTimeFormatter midDateFormatter = DateTimeFormatter.ofPattern("MMM dd, YYYY");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
		StringBuilder str = new StringBuilder("===============================================================================\n");

		str.append(String.format("Consultant:%s %s %s                    Week Starting: %s\n\n",
				this.consultant.getName().getLastName(), this.consultant.getName().getFirstName(),
				this.consultant.getName().getMiddleName(), midDateFormatter.format(this.getWeekStartingDay())));
		str.append("Billable Time:\n");
		str.append("Account                      		 Date         Hours   Skill\n");
		str.append("---------------------------------------  ----------  ------  ------------------\n");
		for (ConsultantTime time : consultantHours) {
			if (time.isBillable()) {
				str.append(String.format("%-40s %-16s %-3d %s\n", time.account.getName(),
						dateFormatter.format(time.getDate()), time.getHours(), time.getSkill().toString()));
			}
		}

		str.append("\nNon-Billable Time:\n");
		str.append("Account                      		 Date         Hours   Skill\n");
		str.append("---------------------------------------  ----------  ------  ------------------\n");
		for (ConsultantTime time : consultantHours) {
			if (!time.isBillable()) {
				str.append(String.format("%-40s %-16s %-3d %s\n", time.account.getName(),
						time.getDate().toString(), time.getHours(), time.getSkill().toString()));
			}
		}

		str.append("\nSummary:\n");
		str.append(String.format("%-55s %2d\n", "Total Billable:", this.getTotalBillableHours()));
		str.append(String.format("%-55s %2d\n", "Total Non-Billable: ", this.getTotalNonBillableHours()));
		str.append(String.format("%-55s %2d\n", "Total hours:", this.getTotalHours()));

		str.append("===============================================================================\n");
		return str.toString();

//		return String.format("%s %s %s %s", this.consultant.getName().getLastName(), 

	}

	// String representation of this object, consisting of the consultant name and
	// the time card week starting day.
	public String toString() {
		return String.format("%s %s %s %s", this.consultant.getName().getLastName(),
				this.consultant.getName().getFirstName(), this.consultant.getName().getMiddleName(),
				this.getWeekStartingDay().getDayOfWeek());
	}

	@Override
	public int compareTo(TimeCard arg0) {

		int diff = 0;
		if (this != arg0) {
			if ((diff = this.getWeekStartingDay().compareTo(arg0.getWeekStartingDay())) == 0)
				if ((diff = consultant.compareTo(arg0.consultant)) == 0)
					if ((diff = Integer.compare(getTotalBillableHours(), arg0.getTotalBillableHours())) == 0)
						diff = Integer.compare(getTotalNonBillableHours(), arg0.getTotalNonBillableHours());

		}
		return diff;
	}

}
