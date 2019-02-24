/**
 * 
 */
package com.scg.domain;

import com.scg.util.PersonalName;

/**
 * @author olga
 *
 */
public class Consultant implements Comparable<Consultant> {
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consultant other = (Consultant) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	com.scg.util.PersonalName name;

	public Consultant(PersonalName name) {
		this.name = name;
	}

	/**
	 * Getter for name property. Returns: value of name property.
	 */

	public PersonalName getName() {
		return this.name;
	}

	// Returns the string representation of the consultant's name.
	public final String toString() {
		return this.name.toString();
	}

	public int compareTo(Consultant consultant) {

		return name.compareTo(consultant.getName());

	}

}
