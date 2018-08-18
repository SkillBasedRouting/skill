package com.routing.skillservice.dto.skill.request;

import com.routing.skillservice.dto.skill.IntegerSkillDTO;

/**
 * <b>com.routing.skillservice.dto.skill.request.RequestIntegerSkillDTO</b>
 * <p>
 *   entity that is used to create a {@link IntegerSkillDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequestIntegerSkillDTO extends RequestSkillDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/skill/integer/request";

	private Double multiplier;

	public Double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}

}
