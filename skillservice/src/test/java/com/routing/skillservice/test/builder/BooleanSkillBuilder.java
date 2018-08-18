package com.routing.skillservice.test.builder;

import java.util.UUID;

import com.routing.skillservice.dto.skill.BooleanSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestBooleanSkillDTO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;

public class BooleanSkillBuilder extends SkillBuilder {

	public static BooleanSkillBuilder defaultBuilder() {
		return new BooleanSkillBuilder().setKey(UUID.randomUUID().toString()).setLabel("skill");
	}

	private BooleanSkill booleanSkill;

	public BooleanSkillBuilder() {
		this(new BooleanSkill());
	}

	public BooleanSkillBuilder(final BooleanSkill integerSkill) {
		this.booleanSkill = integerSkill;
	}

	@Override
	public BooleanSkillBuilder setKey(String key) {
		this.booleanSkill.setKey(key);
		return this;
	}

	@Override
	public BooleanSkillBuilder setLabel(String id) {
		this.booleanSkill.setLabel(id);
		return this;
	}

	@Override
	public BooleanSkill build() {
		return this.booleanSkill;
	}

	@Override
	public BooleanSkillDTO buildDTO() {

		final BooleanSkillDTO dto = new BooleanSkillDTO();
		dto.setKey(this.booleanSkill.getKey());
		dto.setLabel(this.booleanSkill.getLabel());

		return dto;
	}

	@Override
	public RequestBooleanSkillDTO buildRequestDTO() {

		final RequestBooleanSkillDTO dto = new RequestBooleanSkillDTO();
		dto.setKey(this.booleanSkill.getKey());
		dto.setLabel(this.booleanSkill.getLabel());

		return dto;
	}

}
