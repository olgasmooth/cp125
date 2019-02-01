/**
 * 
 */
package com.scg.util;

import java.util.Objects;

/**
 * A simple mailing address. Does no validity checking for things like valid
 * states or postal codes. Instances of this class are immutable
 * 
 * @author olgas
 *
 */
public class Address {
	private String streetNumber; // the street number.
	private String city; // the city.
	private StateCode state; // the state.
	private String postalCode; // the postal code.

	// Construct an Address.
	Address(String streetNumber, String city, StateCode state, String postalCode) {
		this.streetNumber = streetNumber;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
	}

	// Compares two Address object for value equality.
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		Address obj2 = (Address) obj;
		return (this.streetNumber.equals(obj2.streetNumber) && this.city.equals(obj2.city)
				&& this.state.equals(obj2.state) && this.postalCode.equals(obj2.postalCode));
	}

	// Gets the city for this address.
	public String getCity() {
		return city;

	}

	// Gets the postal code for this address.
	public String getPostalCode() {
		return postalCode;
	}

	// Get the state for this address.
	public StateCode getState() {
		return state;
	}

	// Get the street number number for this address.
	public String getStreetNumber() {
		return streetNumber;
	}

	// Prints this address in the form:
	public int hashCode() {
		return Objects.hash(state, streetNumber, postalCode);
	}

	// Prints this address in the form:
	// street number
	// city, state postal code

	public String toString() {
		return String.format("%s %s %s %s", this.streetNumber, this.city, this.state.toString(), this.postalCode);
	}
}
