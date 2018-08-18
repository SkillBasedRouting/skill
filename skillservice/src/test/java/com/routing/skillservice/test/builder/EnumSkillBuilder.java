package com.routing.skillservice.test.builder;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.routing.skillservice.dto.skill.EnumSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestEnumSkillDTO;
import com.routing.skillservice.feature.skill.model.EnumSkill;

public class EnumSkillBuilder extends SkillBuilder {

	public static EnumSkillBuilder defaultBuilder() {
		final Set<String> value = Arrays.asList("bla", "bla2", "bla3").stream().collect(Collectors.toSet());
		return new EnumSkillBuilder().setKey(UUID.randomUUID().toString()).setLabel("skill").setValue(value);
	}

	private EnumSkill enumSkill;

	public EnumSkillBuilder() {
		this(new EnumSkill());
	}

	public EnumSkillBuilder(final EnumSkill integerSkill) {
		this.enumSkill = integerSkill;
	}

	@Override
	public EnumSkillBuilder setKey(String key) {
		this.enumSkill.setKey(key);
		return this;
	}

	@Override
	public EnumSkillBuilder setLabel(String id) {
		this.enumSkill.setLabel(id);
		return this;
	}

	public EnumSkillBuilder setValue(final Set<String> value) {
		this.enumSkill.setValue(value);
		return this;
	}

	public EnumSkillBuilder addValue(final String value) {
		if (null != this.enumSkill.getValue()) {
			this.enumSkill.setValue(new LinkedHashSet<>());
		}
		this.enumSkill.getValue().add(value);
		return this;
	}

	@Override
	public EnumSkill build() {
		return this.enumSkill;
	}

	@Override
	public EnumSkillDTO buildDTO() {

		final EnumSkillDTO dto = new EnumSkillDTO();
		dto.setKey(this.enumSkill.getKey());
		dto.setLabel(this.enumSkill.getLabel());
		dto.setValues(this.enumSkill.getValue());

		return dto;
	}

	@Override
	public RequestEnumSkillDTO buildRequestDTO() {

		final RequestEnumSkillDTO dto = new RequestEnumSkillDTO();
		dto.setKey(this.enumSkill.getKey());
		dto.setLabel(this.enumSkill.getLabel());
		dto.setValues(this.enumSkill.getValue());

		return dto;
	}

}
