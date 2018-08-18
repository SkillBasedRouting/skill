package com.routing.skillservice.test.builder;

import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.feature.skill.model.Skill;

public abstract class SkillBuilder {

	public abstract SkillBuilder setKey(final String key);

	public abstract SkillBuilder setLabel(final String label);

	public abstract Skill build();
	public abstract SkillDTO buildDTO();
	public abstract RequestSkillDTO buildRequestDTO();

}
