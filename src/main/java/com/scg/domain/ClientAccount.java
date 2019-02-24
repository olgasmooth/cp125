/**
 * 
 */
package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;

/**
 * @author olgas
 *
 */
public final class ClientAccount implements Account, Comparable<ClientAccount> {
	PersonalName contact; // person's name of contact
	String Name; // company name
	boolean isBillable;
	Address address;

	public Address getAddress() {
		return this.address;
	}

	public String getName() {

		return Name;
	}

	public boolean isBillable() {

		return isBillable;
	}

	public ClientAccount(String name, PersonalName contact) {
		this.Name = name;
		this.contact = contact;
		this.isBillable = true;

	}

	public ClientAccount(String name, PersonalName contact, Address address) {
		this(name, contact);
		this.address = address;
	}

	public PersonalName getContact() {
		return this.contact;
	}

	public void setContact(PersonalName contact) {
		this.contact = contact;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + (isBillable ? 1231 : 1237);
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
		ClientAccount other = (ClientAccount) obj;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (isBillable != other.isBillable)
			return false;
		return true;
	}

	public String toString() {
		return String.format("%s %s %s", contact.toString(), Name, isBillable);
	}

	@Override
	public int compareTo(ClientAccount o) {
		int diff = 0;
		if (this != o) {
			if ((diff = Name.compareTo(o.Name)) == 0)
				if ((diff = contact.compareTo(o.contact)) == 0)
					diff = address.compareTo(o.address);

		}
		return diff;
	}
}
