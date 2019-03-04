/**
 * 
 */
package com.scg.domain;

import java.io.Serializable;

/**
 * @author olgas
 *
 */
public enum Skill implements Serializable{
	PROJECT_MANAGER("Project Manager", 150), 
	SOFTWARE_ENGINEER("Software Engineer", 150), 
	SOFTWARE_TESTER("Software Tester", 100),
	SYSTEM_ARCHITECT("System Architect", 200), 
	UNKNOWN_SKILL("Unknown Skill", 150);

	private int Rate;

	private final String skillName;

	private Skill(String skillName, int rate) {
		this.skillName = skillName;
		this.Rate = rate;
	}

	// Getter for rate property
	public int getRate() {
		return this.Rate;
	}

	// returns skill name into string format
	@Override
	public String toString() {
		return this.skillName;
	}

}
