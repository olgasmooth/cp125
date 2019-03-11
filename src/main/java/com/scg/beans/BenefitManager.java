/**
 * 
 */
package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;

/**
 * Tracks changes in employee benifits. Listens for any PropertyChangeEvent and
 * simply logs them. Additionally, Listens for any BenefitEvent and logs those
 * as well. No other actions are taken in response to any event.
 * 
 * @author olgas
 *
 */
public class BenefitManager implements BenefitListener, PropertyChangeListener, EventListener {

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) + " com.scg.beans.BenefitManager propertyChange");
		
		int newValue = (int)event.getNewValue();
		int oldValue = (int)event.getOldValue();
		StaffConsultant c = (StaffConsultant)event.getSource();
		System.out.printf("INFO: %s changed from %d to %d for %s\n", event.getPropertyName(), oldValue, newValue, c.getName());

	}

	@Override
	public void dentalCancellation(BenefitEvent event) {
		System.out.println(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) + " com.scg.beans.BenefitManager dentalCancellation");
		System.out.printf("INFO: %s cancelled dental, %s\n", event.getConsultant(), LocalDate.now());
		BenefitEvent.cancelDental(this, event.getConsultant(), event.getEffectiveDate());

	}

	@Override
	public void dentalEnrollment(BenefitEvent event) {
		System.out.println(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) + " com.scg.beans.BenefitManager dentalEnrollment");
		System.out.printf("INFO: %s enrolled in dental, %s\n", event.getConsultant(), LocalDate.now());
		BenefitEvent.enrollDental(this, event.getConsultant(), event.getEffectiveDate());
	}

	@Override
	public void medicalCancellation(BenefitEvent event) {
		System.out.println(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) + " com.scg.beans.BenefitManager medicalCancellation");
		System.out.printf("INFO: %s cancelled medical, %s\n", event.getConsultant(), LocalDate.now());
		BenefitEvent.cancelMedical(this, event.getConsultant(), event.getEffectiveDate());
	}

	@Override
	public void medicalEnrollment(BenefitEvent event) {
		System.out.println(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) + " com.scg.beans.BenefitManager medicalEnrollment");
		System.out.printf("INFO: %s enrolled in medical, %s\n", event.getConsultant(), LocalDate.now());
		BenefitEvent.enrollMedical(this, event.getConsultant(), event.getEffectiveDate());
	}

}
