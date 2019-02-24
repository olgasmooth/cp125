/**
 * 
 */
package com.scg.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Encapsulates a range of two dates, inclusive of the start date and end date.
 * 
 * @author olgas
 *
 */
public final class DateRange {
	LocalDate startDate;
	LocalDate endDate;

	// Construct a DateRange given two dates.
	public DateRange(java.time.LocalDate startDate, java.time.LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;

	}

	// Construct a DateRange for the given month, the date range shall span the
	// entire month, from the first day of the month through the last day of the
	// month.
	public DateRange(java.time.Month month, int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month.getValue() - 1);

		this.startDate = LocalDate.of(year, month, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		this.endDate = LocalDate.of(year, month, c.getActualMaximum(Calendar.DAY_OF_MONTH));

	}

	// Construct a DateRange given two date strings in the correct format.
	public DateRange(String start, String end) {
		this.startDate = LocalDate.parse(start);
		this.endDate = LocalDate.parse(end);
	}

	// Returns the end date for this DateRange.
	LocalDate getEndDate() {
		return this.endDate;

	}

	// Returns the start date for this DateRange.
	LocalDate getStartDate() {
		return this.startDate;

	}

	// Returns true if the specified date is within the range start date <= date <=
	// end date.
	boolean isInRange(LocalDate date) {
		return (this.getStartDate().compareTo(date) <= 0 && this.getEndDate().compareTo(date) > 0);
	}

}
