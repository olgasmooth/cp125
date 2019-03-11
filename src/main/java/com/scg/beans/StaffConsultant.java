/**
 * 
 */
package com.scg.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

import com.scg.domain.Consultant;
import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
public final class StaffConsultant extends Consultant implements Serializable {
	private static final long serialVersionUID = -2996712429935693437L;
	private static String PAY_RATE_PROPERTY_NAME = "payRate"; // Pay rate property name.

	private static String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours"; // Pay rate property name.

	private static String VACATION_HOURS_PROPERTY_NAME = "vacationHours"; // Vacation hours property name.

	int payRate;
	int sickLeaveHours;
	int vacationHours;

	private PropertyChangeSupport pcs;

	private VetoableChangeSupport vcs;

	/**
	 * Creates a new instance of StaffConsultant
	 * 
	 * @param name
	 * @param rate
	 * @param sickLeave
	 * @param vacation
	 */
	public StaffConsultant(PersonalName name, int rate, int sickLeave, int vacation) {
		super(name);
		this.payRate = rate;
		this.sickLeaveHours = sickLeave;
		this.vacationHours = vacation;
		this.pcs = new PropertyChangeSupport(this);
		this.vcs = new VetoableChangeSupport(this);
	}

	/**
	 * @return the payRate
	 */
	public int getPayRate() {
		return payRate;
	}

	/**
	 * @param payRate the payRate to set
	 */
	public void setPayRate(int newPayRate) {
		
		try {
			vcs.fireVetoableChange(StaffConsultant.PAY_RATE_PROPERTY_NAME, this.payRate, newPayRate);
			pcs.firePropertyChange(StaffConsultant.PAY_RATE_PROPERTY_NAME, this.payRate, newPayRate);
			this.payRate = newPayRate;
		} catch (PropertyVetoException e) {
			System.out.printf("INFO: Denied pay adjustment for %s\n", this.getName().toString());
		}
	}

	/**
	 * @return the sickLeaveHours
	 */
	public int getSickLeaveHours() {
		return sickLeaveHours;
	}

	/**
	 * @param sickLeaveHours the sickLeaveHours to set
	 */
	public void setSickLeaveHours(int newSickLeaveHours) {
		pcs.firePropertyChange(StaffConsultant.SICK_LEAVE_HOURS_PROPERTY_NAME, this.sickLeaveHours, newSickLeaveHours);
		this.sickLeaveHours = newSickLeaveHours;
	}

	/**
	 * @return the vacationHours
	 */
	public int getVacationHours() {
		return vacationHours;
	}

	/**
	 * @param newVacationHours the vacationHours to set
	 */
	public void setVacationHours(int newVacationHours) {
		pcs.firePropertyChange(StaffConsultant.VACATION_HOURS_PROPERTY_NAME, this.vacationHours, newVacationHours);
		this.vacationHours = newVacationHours;
	}

	/**
	 * Adds a payRate property change listener.
	 * 
	 * @param l
	 */
	public void addPayRateListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
	}

	/**
	 * Adds a general property change listener.
	 * 
	 * @param l
	 */
	public void addPropertyChangeListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(l);
	}

	/**
	 * Adds a sickLeaveHours property change listener.
	 * 
	 * @param l
	 */
	public void addSickLeaveHoursListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
	}

	/**
	 * Adds a vacationHours property change listener.
	 * 
	 * @param l
	 */
	public void addVacationHoursListener(PropertyChangeListener l) {
		pcs.addPropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
	}

	/**
	 * Adds a vetoable change listener, only applicable to payRate changes.
	 * 
	 * @param l
	 */
	public void addVetoableChangeListener(VetoableChangeListener l) {
		vcs.addVetoableChangeListener(PAY_RATE_PROPERTY_NAME, l);
	}

	/**
	 * Removes a payRate property change listener
	 * 
	 * @param l
	 */
	public void removePayRateListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
	}

	/**
	 * Remove a general property change listener.
	 * 
	 * @param l
	 */
	public void removePropertyChangeListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(l);
	}

	/**
	 * Removes a sickLeaveHours property change listener.
	 * 
	 * @param l
	 */
	public void removeSickLeaveHoursListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
	}

	/**
	 * Removes a vacationHours property change listener.
	 * 
	 * @param l
	 */
	public void removeVacationHoursListener(PropertyChangeListener l) {
		pcs.removePropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
	}

	/**
	 * Removes a vetoable change listener.
	 * 
	 * @param l
	 */
	public void removeVetoableChangeListener(VetoableChangeListener l) {
vcs.removeVetoableChangeListener(PAY_RATE_PROPERTY_NAME, l);
	}
}
