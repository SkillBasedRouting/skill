package com.routing.skillservice.test.builder;

import java.util.UUID;

import com.routing.skillservice.dto.skill.IntegerSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestIntegerSkillDTO;
import com.routing.skillservice.feature.skill.model.IntegerSkill;

public class IntegerSkillBuilder extends SkillBuilder {

	public static IntegerSkillBuilder defaultBuilder() {
		return new IntegerSkillBuilder().setKey(UUID.randomUUID().toString()).setLabel("skill");
	}

	private IntegerSkill integerSkill;

	public IntegerSkillBuilder() {
		this(new IntegerSkill());
	}

	public IntegerSkillBuilder(final IntegerSkill integerSkill) {
		this.integerSkill = integerSkill;
	}

	@Override
	public IntegerSkillBuilder setKey(String key) {
		this.integerSkill.setKey(key);
		return this;
	}

	@Override
	public IntegerSkillBuilder setLabel(String id) {
		this.integerSkill.setLabel(id);
		return this;
	}
	
	public IntegerSkillBuilder setMultiplier(Double multiplier) {
		this.integerSkill.setMultiplier(multiplier);
		return this;
	}

	@Override
	public IntegerSkill build() {
		return this.integerSkill;
	}

	@Override
	public IntegerSkillDTO buildDTO() {

		final IntegerSkillDTO dto = new IntegerSkillDTO();
		dto.setKey(this.integerSkill.getKey());
		dto.setLabel(this.integerSkill.getLabel());
		dto.setMultiplier(this.integerSkill.getMultiplier());

		return dto;
	}

	@Override
	public RequestIntegerSkillDTO buildRequestDTO() {

		final RequestIntegerSkillDTO dto = new RequestIntegerSkillDTO();
		dto.setKey(this.integerSkill.getKey());
		dto.setLabel(this.integerSkill.getLabel());
		dto.setMultiplier(this.integerSkill.getMultiplier());

		return dto;
	}

}
