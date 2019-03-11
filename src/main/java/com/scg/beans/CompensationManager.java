/**
 * 
 */
package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;

/**
 * Approves or rejects compensation changes. Listens for PropertyChangeEvents on
 * the payRate property, any pay rate increase in excess of will be vetoed. The
 * rejection (veto) or acceptance of each pay rate change will be logged as will
 * any successful pay rate change.
 * 
 * @author olgas
 *
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener, EventListener {

	/**
	 * Constructor.
	 */
	public CompensationManager(){
		
	}
	
	@Override
	public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException {
		String propertyName = event.getPropertyName();
		if(propertyName != "payRate") {
			return;
		}
		int newRate = (int)event.getNewValue();
		int rate = (int)event.getOldValue();
		double diff = (double)(newRate - rate)/rate;
		StaffConsultant c = (StaffConsultant)event.getSource();
		if(diff > 0.05) {
			System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.CompensationManager vetoableChange\n");
			System.out.printf("INFO: REJECTED pay rate change, from %d to %d for %s\n", rate, newRate, c.getName().toString());
			throw new PropertyVetoException(propertyName, event);
		}
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.CompensationManager vetoableChange\n");
		System.out.printf("INFO: APPROVED pay rate change, from %d to %d for %s\n", rate, newRate, c.getName().toString());
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		StaffConsultant c = (StaffConsultant)event.getSource();
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.CompensationManager propertyChange\n");
		System.out.printf("INFO: Pay rate change, from %d to %d for %s\n", (int)event.getOldValue(), (int)event.getNewValue(), c.getName().toString());
	}

}
