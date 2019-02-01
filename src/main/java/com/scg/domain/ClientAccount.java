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
public final class ClientAccount implements Account {
	PersonalName contact; // person's name of contact
	String Name; // company name
	boolean isBillable;
	Address address;

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
	
	public Address getAddress() {
		return this.address;
	}

	public void setContact(PersonalName contact) {
		this.contact = contact;
	}

	public String toString() {
		return String.format("%s %s %s", contact.toString(), Name, isBillable);
	}
}
