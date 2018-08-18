package com.routing.skillservice.dto.skill;

import java.util.Set;

/**
 * <b>com.routing.skillservice.dto.skill.EnumSkillDTO</b>
 * <p>
 *   a {@link SkillDTO} that shape can be represented with specific values
 *   e.g. north, south, west, east
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class EnumSkillDTO extends SkillDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/skill/enum";

	private Set<String> values;

	public Set<String> getValues() {
		return values;
	}

	public void setValues(Set<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnumSkillDTO [values=");
		builder.append(values);
		builder.append("]");
		return builder.toString();
	}

}
