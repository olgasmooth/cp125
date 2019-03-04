/**
 * 
 */
package com.scg.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author olgas
 *
 */
public final class PersonalName implements Serializable {
	private String lastName;
	private String firstName;
	private String middleName;

	public PersonalName() {

	}

	public PersonalName(String lastName, String firstName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = "";
	}

	public PersonalName(String lastName, String firstName, String middleName) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", this.lastName, this.firstName, this.middleName);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(lastName, firstName, middleName);
	}
	
	@Override
	public boolean equals(Object other)  {
		if(other == null || other.getClass()!= this.getClass()) 
            return false; 
		
		PersonalName obj2 = (PersonalName) other;
		return (this.firstName.equals(obj2.firstName) && this.lastName.equals(obj2.lastName)
				&& this.middleName.equals(obj2.middleName));
	}

	public int compareTo(PersonalName name) {
		int diff = 0;
		if(this!= name) {
			if((diff = this.lastName.compareTo(name.getLastName())) == 0)	{
		    diff = this.firstName.compareTo(name.getFirstName());
			}
		}
		return diff;
	}
}
