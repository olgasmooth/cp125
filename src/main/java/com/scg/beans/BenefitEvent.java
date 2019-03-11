/**
 * 
 */
package com.scg.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.Optional;

import com.scg.domain.Consultant;

/**
 * Event used to notify listeners of a Consultant's enrollment or cancellation
 * of medical or dental benefits.
 * 
 * @author olgas
 *
 */
public class BenefitEvent extends EventObject implements Serializable {

	public BenefitEvent(Object source, Consultant consultant, LocalDate effectiveDate, Boolean enrolled) {
		super(source);
		this.consultant = consultant;
		this.effectiveDate = effectiveDate;
		this.enrolled = enrolled;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7182588999821513052L;
	Consultant consultant;
	LocalDate effectiveDate;
	Boolean enrolled = false;

	/**
	 * static BenefitEvent cancelDental(Object source, Consultant consultant,
	 * java.time.LocalDate effectiveDate)
	 * 
	 * @param source
	 * @param consultant
	 * @param effectiveDate
	 * @return
	 */
	public static BenefitEvent cancelDental(Object source, Consultant consultant, LocalDate effectiveDate) {
		return new BenefitEvent(source, consultant, effectiveDate, false);
	}

	/**
	 * Creates a medical cancellation event.
	 * 
	 * @param source
	 * @param consultant
	 * @param effectiveDate
	 * @return
	 */
	static BenefitEvent cancelMedical(Object source, Consultant consultant, LocalDate effectiveDate) {
		return new BenefitEvent(source, consultant, effectiveDate, false);

	}

	/**
	 * Creates a dental enrollment event.
	 * 
	 * @param source
	 * @param consultant
	 * @param effectiveDate
	 * @return
	 */
	public static BenefitEvent enrollDental(Object source, Consultant consultant, LocalDate effectiveDate) {
		return new BenefitEvent(source, consultant, effectiveDate, true);
	}

	/**
	 * Creates a medical enrollment event.
	 * 
	 * @param source
	 * @param consultant
	 * @param effectiveDate
	 * @return
	 */
	public static BenefitEvent enrollMedical(Object source, Consultant consultant, LocalDate effectiveDate) {
		return new BenefitEvent(source, consultant, effectiveDate, true);
	}

	/**
	 * Gets the consultant that was terminated.
	 * 
	 * @return
	 */
	public Consultant getConsultant() {
		return this.consultant;
	}

	/**
	 * Gets the effective date.
	 * 
	 * @return
	 */
	LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	/**
	 * Gets the medical enrollment status.
	 * 
	 * @return
	 */
	public Optional<Boolean> medicalStatus() {
		return Optional.of(enrolled);
	}
	
	/**
	 * Gets the dental enrollment status.
	 * 
	 * @return
	 */
	public Optional<Boolean> dentalStatus() {
		return Optional.of(enrolled);
	}

}
