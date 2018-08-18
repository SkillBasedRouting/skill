package com.routing.skillservice.dto.skill;

/**
 * <b>com.routing.skillservice.dto.skill.IntegerSkillDTO</b>
 * <p>
 *   a {@link SkillDTO} that shape can be represented with integer numbers
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class IntegerSkillDTO extends SkillDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/skill/integer";

	private Double multiplier;

	public Double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntegerSkillDTO [multiplier=");
		builder.append(multiplier);
		builder.append("]");
		return builder.toString();
	}

}
