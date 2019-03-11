/**
 * 
 */
package com.scg.beans;

import java.awt.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventListener;

/**
 * @author olgas
 *
 */
public class Eeoc implements TerminationListener, EventListener {
	int forcedTerminationCount;
	int voluntaryTerminationCount;
	/**
	 * constructor
	 */
	public Eeoc() {
		forcedTerminationCount = 0;
		voluntaryTerminationCount = 0;
	}

	@Override
	public void forcedTermination(TerminationEvent event) {
		forcedTerminationCount++;
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.Eeoc forcedTermination\n");
		System.out.printf("INFO: %s was fired\n", event.getConsultant().getName().toString());
	}
	/**
	 * Gets the forced termination count.
	 * @return
	 */
	public int	forcedTerminationCount() {
		return forcedTerminationCount;	
	}
	
	@Override
	public void voluntaryTermination(TerminationEvent event) {
		voluntaryTerminationCount++;
		System.out.print(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss").format(LocalDateTime.now()) +  " com.scg.beans.Eeoc voluntaryTermination\n");
		System.out.printf("INFO: %s quit\n", event.getConsultant().getName().toString());
	}
	
	/**
	 * Gets the voluntary termination count.
	 * @return
	 */
	public int	voluntaryTerminationCount() {
		return voluntaryTerminationCount;
		
	}
	

}
