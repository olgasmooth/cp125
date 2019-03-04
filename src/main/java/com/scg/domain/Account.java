/**
 * 
 */
package com.scg.domain;

import java.io.Serializable;

/**
 * @author olgas
 *
 */

public interface Account extends Serializable{
		
	public String getName();
	public boolean isBillable();

}
