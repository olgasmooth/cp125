/**
 * 
 */
package com.scg.beans;

import java.io.Serializable;
import java.util.EventObject;

import com.scg.domain.Consultant;


/**
 * @author olgas
 *
 */
public class TerminationEvent extends EventObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8080843304259825644L;
	Consultant consultant;// the consultant being terminated
	private boolean voluntary; // was the termination voluntary


	public TerminationEvent(Object source, Consultant consultant, boolean voluntary) {
		super(source);
		this.voluntary = voluntary;
		this.consultant = consultant;
	}

	/**
	 * Gets the consultant that was terminated.
	 * 
	 * @return
	 */
	public Consultant getConsultant() {
		return consultant;
	}

	/**
	 * Gets the voluntary termination status.
	 * 
	 * @return
	 */
	public boolean isVoluntary() {
		return this.voluntary;
	}

}
