/**
 * 
 */
package com.scg.beans;

import java.util.EventListener;

/**
 * Interface for accepting notification of consultant changes in medical and
 * dental enrollment.
 * 
 * @author olgas
 *
 */
public interface BenefitListener extends EventListener {

	/**
	 * Invoked when a consultant is cancels dental.
	 * 
	 * @param event
	 */
	public void dentalCancellation(BenefitEvent event);

	/**
	 * Invoked when a consultant is enrolls in dental.
	 * 
	 * @param event
	 */
	public void dentalEnrollment(BenefitEvent event);

	/**
	 * Invoked when a consultant is cancels medical.
	 * 
	 * @param event
	 */
	public void medicalCancellation(BenefitEvent event);

	/**
	 * Invoked when a consultant is enrolls in medical.
	 * 
	 * @param event
	 */
	public void medicalEnrollment(BenefitEvent event);

}
