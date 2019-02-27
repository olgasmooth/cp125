/**
 * 
 */
package com.scg.domain;

/**
 * @author olgas
 *
 */
public enum Skill {
	PROJECT_MANAGER("PROJECT MANAGER", 150), 
	SOFTWARE_ENGINEER("SOFTWARE ENGINEER", 150), 
	SOFTWARE_TESTER("SOFTWARE TESTER", 100),
	SYSTEM_ARCHITECT("SYSTEM ARCHITECT", 200), 
	UNKNOWN_SKILL("UNKNOWN_SKILLS", 150);

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
