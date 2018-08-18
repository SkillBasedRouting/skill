package com.routing.skillservice.dto.skill.request;

import com.routing.skillservice.dto.skill.EnumSkillDTO;

import java.util.Set;

/**
 * <b>com.routing.skillservice.dto.skill.request.RequestEnumSkillDTO</b>
 * <p>
 *   entity that is used to create a {@link EnumSkillDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequestEnumSkillDTO extends RequestSkillDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/skill/enum/request";

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
		builder.append("RequestEnumSkillDTO []");
		return builder.toString();
	}

}
