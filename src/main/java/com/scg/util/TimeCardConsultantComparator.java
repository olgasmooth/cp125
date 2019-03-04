package com.scg.util;

import java.util.Comparator;

import com.scg.domain.TimeCard;

public final class TimeCardConsultantComparator implements Comparator<TimeCard> {

	// constructor
	public TimeCardConsultantComparator() {
	}

	// Compares its two arguments, in ascending order by (in precedence order)
	// consultant, the starting date, total billable hours and lastly total
	// non-billable hours

	@Override
	public int compare(TimeCard arg0, TimeCard arg1) {
		int diff = 0;
		if (arg0 != arg1) {
		if ((diff = arg0.getConsultant().compareTo(arg1.getConsultant())) == 0)
		if ((diff = arg0.getWeekStartingDay().compareTo(arg1.getWeekStartingDay())) == 0)
		if ((diff = arg0.getWeekStartingDay().compareTo(arg1.getWeekStartingDay()))==0)
		if ((diff = Integer.compare(arg0.getTotalBillableHours(), arg1.getTotalBillableHours()))==0)
		diff = Integer.compare(arg0.getTotalNonBillableHours(), arg1.getTotalNonBillableHours());
		}
		return diff;
	}
}
