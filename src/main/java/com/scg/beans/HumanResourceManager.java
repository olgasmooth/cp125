/**
 * 
 */
package com.scg.beans;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.event.EventListenerList;

import com.scg.domain.Consultant;


/**
 * Responsible for modifying the pay rate, sick leave and vacation hours of
 * staff consultants. Provide methods for registration of BenefitListeners, and
 * TerminationListeners.
 * 
 * @author olgas
 *
 */
public class HumanResourceManager {
	private EventListenerList listenerList = new EventListenerList();

	/**
	 * constructor
	 */
	public HumanResourceManager() {
	}

	/**
	 * Accepts the resignation of a consultant and fires a voluntary termination
	 * event for the consultant.
	 * 
	 * @param c
	 */
	public void acceptResignation(Consultant c) {
		TerminationEvent event = new TerminationEvent(this, c, true);
		  TerminationListener[] listeners = listenerList.getListeners(TerminationListener.class);
	        for (TerminationListener tl : listeners) {
	            tl.voluntaryTermination(event);
	        }
	}
	
	/**
	 * Fires an involuntary termination event for the staff consultant.
	 */
	public void terminate(Consultant c) {
		TerminationEvent event = new TerminationEvent(this, c, false);
		  TerminationListener[] listeners = listenerList.getListeners(TerminationListener.class);
	        for (TerminationListener tl : listeners) {
	            tl.forcedTermination(event);
	        }
	}

	/**
	 * Adds a benefit listener.
	 * 
	 * @param l
	 */
	public void addBenefitListener(BenefitListener l) {
		listenerList.add(BenefitListener.class, l);
	}

	/**
	 * Adds a termination listener.
	 * 
	 * @param l
	 */
	public void addTerminationListener(TerminationListener l) {
		listenerList.add(TerminationListener.class, l);
	}

	/**
	 * Sets the pay rate for a staff consultant and logs whether the pay rate change
	 * was approved or rejected (vetoed).
	 * 
	 * @param c
	 * @param newPayRate
	 */
	public void adjustPayRate(StaffConsultant c, int newPayRate) {
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.HumanResourceManager adjustPayRate\n");
		int currentRate = c.getPayRate();
		System.out.printf("INFO: %% change = (%d - %d)/%d = %.6f\n", newPayRate, currentRate, currentRate, (double)(newPayRate - currentRate)/currentRate);
		c.setPayRate(newPayRate);
	}

	/**
	 * Sets the sick leave hours for a staff consultant.
	 * 
	 * @param c
	 * @param newSickLeaveHours
	 */
	public void adjustSickLeaveHours(StaffConsultant c, int newSickLeaveHours) {
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.HumanResourceManager adjustSickLeaveHours\n");
		int currentValue = c.getSickLeaveHours();
		System.out.printf("INFO: %% change from %d to %d\n", currentValue, newSickLeaveHours);
		c.setSickLeaveHours(newSickLeaveHours);
	}

	/**
	 * Sets the vacation hours for a staff consultant.
	 * 
	 * @param c
	 * @param newVacationHours
	 */
	public void adjustVacationHours(StaffConsultant c, int newVacationHours) {
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.HumanResourceManager adjustVacationHours\n");
		int currentValue = c.getSickLeaveHours();
		System.out.printf("INFO: %% change from %d to %d\n", currentValue, newVacationHours);
		c.setVacationHours(newVacationHours);
	}

	/**
	 * Cancel a consultant's enrollment in dental, and fires a dental cancellation
	 * event
	 * 
	 * @param c
	 */
	public void cancelDental(Consultant c) {
		BenefitEvent benefitEvent = BenefitEvent.cancelDental(this, c, LocalDate.now());

	    BenefitListener[] benefitListeners = listenerList.getListeners(BenefitListener.class);
	    for (BenefitListener benefitListener : benefitListeners){
	        benefitListener.dentalCancellation(benefitEvent);
	    }
	}

	/**
	 * Cancel a consultant's enrollment in medical, and fires a medical cancellation
	 * event.
	 * 
	 * @param c
	 */
	public void cancelMedical(Consultant c) {
		BenefitEvent benefitEvent = BenefitEvent.cancelMedical(this, c, LocalDate.now());

	    BenefitListener[] benefitListeners = listenerList.getListeners(BenefitListener.class);
	    for (BenefitListener benefitListener : benefitListeners){
	        benefitListener.medicalCancellation(benefitEvent);
	    }
	}

	/**
	 * Enroll a consultant in dental, and fires a dental enrollment event.
	 * 
	 * @param c
	 */
	public void enrollDental(Consultant c) {
		BenefitEvent benefitEvent = BenefitEvent.enrollDental(this, c, LocalDate.now());

	    BenefitListener[] benefitListeners = listenerList.getListeners(BenefitListener.class);
	    for (BenefitListener benefitListener : benefitListeners){
	        benefitListener.dentalEnrollment(benefitEvent);
	    }
	}

	/**
	 * Enroll a consultant in medical, and fires a medical enrollment event.
	 * 
	 * @param c
	 */
	public void enrollMedical(Consultant c) {
		BenefitEvent benefitEvent = BenefitEvent.enrollMedical(this, c, LocalDate.now());

	    BenefitListener[] benefitListeners = listenerList.getListeners(BenefitListener.class);
	    for (BenefitListener benefitListener : benefitListeners){
	        benefitListener.medicalEnrollment(benefitEvent);
	    }
	}

	/**
	 * Removes a benefit listener.
	 * 
	 * @param l
	 */
	public void removeBenefitListener(BenefitListener l) {
		listenerList.remove(BenefitListener.class, l);
	}

	/**
	 * Removes a termination listener.
	 * 
	 * @param l
	 */
	public void removeTerminationListener(TerminationListener l) {
		listenerList.remove(TerminationListener.class, l);
	}
}
