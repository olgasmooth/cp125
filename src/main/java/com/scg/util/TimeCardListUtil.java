/**
 * 
 */
package com.scg.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Utility class for processing TimeCard lists.
 * 
 * @author olgas
 *
 */
public class TimeCardListUtil {
	public List<TimeCard> timeCards = new ArrayList<TimeCard>();// the list of time cards to sort

	// Sorts this list into ascending order, by the start date
	public static void sortByStartDate(List<TimeCard> timeCards) {

		Collections.sort(timeCards, (t1, t2) -> {
			int diff = 0;
			if (t1 != t2) {
				diff = ((TimeCard) t1).getWeekStartingDay().compareTo(((TimeCard) t2).getWeekStartingDay());
			}
			return diff;
		});

	}

	// Sorts this list into ascending order by consultant name
	public static void sortByConsultantName(List<TimeCard> timeCards) {
		Collections.sort(timeCards, new TimeCardConsultantComparator());
	}

	// Get a list of TimeCards that cover dates that fall within a date range. Each
	// time card *may* have time entries through out one week beginning with the
	// time card start date.
	// Returns a list of TimeCards having dates fall within the date range.
	public static List<TimeCard> getTimeCardsForDateRange(List<TimeCard> timeCards, DateRange dateRange) {
		List<TimeCard> tc = new ArrayList<TimeCard>();

		for (TimeCard t : timeCards) {
			if (dateRange.isInRange(t.getWeekStartingDay())) {
				tc.add(t);
			}
		}
		return tc;
	}

	// Returns a list of TimeCards for the specified consultant.
	public static List<TimeCard> getTimeCardsForConsultant(List<TimeCard> timeCards, Consultant consultant) {
		List<TimeCard> tc = new ArrayList<TimeCard>();

		for (TimeCard t : timeCards) {
			if (consultant.compareTo(t.getConsultant()) == 0) {
				tc.add(t);
			}
		}
		return tc;

	}
}
