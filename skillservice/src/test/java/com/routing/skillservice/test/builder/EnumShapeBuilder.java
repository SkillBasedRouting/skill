package com.routing.skillservice.test.builder;

import java.util.Set;
import java.util.UUID;

import com.routing.skillservice.dto.shape.EnumShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestEnumShapeDTO;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.skill.model.EnumSkill;

public class EnumShapeBuilder extends ShapeBuilder<Set<String>> {

	public static EnumShapeBuilder defaultBuilder() {
		final EnumSkill enumSkill = EnumSkillBuilder.defaultBuilder().build();
		return new EnumShapeBuilder().setSkill(enumSkill).setValue(enumSkill.getValue())
				.setId(UUID.randomUUID().toString());
	}

	private EnumShape shape;

	public EnumShapeBuilder() {
		this.shape = new EnumShape();
	}

	public EnumShapeBuilder(EnumShape shape) {
		super();
		this.shape = shape;
	}

	@Override
	public EnumShapeBuilder setValue(final Set<String> value) {
		this.shape.setEnumValue(value);
		return this;
	}

	@Override
	public EnumShapeBuilder setId(final String id) {
		this.shape.setId(id);
		return this;
	}

	public EnumShapeBuilder setSkill(final EnumSkill skill) {
		this.shape.setEnumSkill(skill);
		return this;
	}

	@Override
	public EnumShapeBuilder setUser(final String user) {
		this.shape.setUser(user);
		return this;
	}

	@Override
	public EnumShape build() {

		final EnumShape shape = new EnumShape();
		shape.setEnumSkill(this.shape.getEnumSkill());
		shape.setEnumValue(this.shape.getEnumValue());
		shape.setId(this.shape.getId());
		shape.setUser(this.shape.getUser());

		return shape;
	}

	@Override
	public EnumShapeDTO buildDTO() {

		final EnumShapeDTO dto = new EnumShapeDTO();
		dto.setId(this.shape.getId());
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getSkill().getKey());
		}
		dto.setValue(this.shape.getEnumValue());

		return dto;
	}

	@Override
	public RequestEnumShapeDTO buildRequestDTO() {

		final RequestEnumShapeDTO dto = new RequestEnumShapeDTO();
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getSkill().getKey());
		}
		dto.setValue(this.shape.getEnumValue());
		dto.setUserId(this.shape.getUser());

		return dto;
	}

}
