/**
 * 
 */
package com.scg.domain;

/**
 * @author olgas
 *
 */
public enum Skill {
	PROJECT_MANAGER("PROJECT MANAGER"), 
	SOFTWARE_ENGINEER("SOFTWARE ENGINEER"), 
	SOFTWARE_TESTER("SOFTWARE TESTER"),
	SYSTEM_ARCHITECT("SYSTEM_ARCHITECT"), 
	UNKNOWN_SKILL("UNKNOWN_SKILLS");

	private int Rate = 50;

	private final String skillName;

	private Skill(String skillName) {
		this.skillName = skillName;
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
