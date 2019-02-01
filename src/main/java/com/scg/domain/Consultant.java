/**
 * 
 */
package com.scg.domain;

import com.scg.util.PersonalName;;

/**
 * @author olga
 *
 */
public class Consultant {
   PersonalName name;
   
   public Consultant(PersonalName name) {
	   this.name = name;
}
   /**Getter for name property.
Returns:
value of name property.*/
   
   public PersonalName getName() {
	   return this.name;
   }
   
   //Returns the string representation of the consultant's name.
   public final String toString() {
	   return this.name.toString();
   }
   
}
