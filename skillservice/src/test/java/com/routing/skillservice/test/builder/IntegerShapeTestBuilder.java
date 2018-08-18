package com.routing.skillservice.test.builder;

import java.util.UUID;

import com.routing.skillservice.dto.shape.IntegerShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestIntegerShapeDTO;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.skill.model.IntegerSkill;

public class IntegerShapeTestBuilder extends ShapeBuilder<Integer> {

	public static IntegerShapeTestBuilder defaultBuilder() {
		return new IntegerShapeTestBuilder().setSkill(IntegerSkillBuilder.defaultBuilder().build()).setValue(10)
				.setId(UUID.randomUUID().toString());
	}

	private IntegerShape shape;

	public IntegerShapeTestBuilder() {
		this.shape = new IntegerShape();
	}

	public IntegerShapeTestBuilder(IntegerShape shape) {
		super();
		this.shape = shape;
	}

	@Override
	public IntegerShapeTestBuilder setValue(final Integer value) {
		this.shape.setIntegerValue(value);
		return this;
	}

	@Override
	public IntegerShapeTestBuilder setId(final String id) {
		this.shape.setId(id);
		return this;
	}

	public IntegerShapeTestBuilder setSkill(final IntegerSkill skill) {
		this.shape.setIntegerSkill(skill);
		return this;
	}

	@Override
	public IntegerShapeTestBuilder setUser(final String user) {
		this.shape.setUser(user);
		return this;
	}

	@Override
	public IntegerShape build() {

		final IntegerShape shape = new IntegerShape();
		shape.setIntegerSkill(this.shape.getIntegerSkill());
		shape.setIntegerValue(this.shape.getIntegerValue());
		shape.setId(this.shape.getId());
		shape.setUser(this.shape.getUser());

		return shape;
	}

	@Override
	public IntegerShapeDTO buildDTO() {

		final IntegerShapeDTO dto = new IntegerShapeDTO();
		dto.setId(this.shape.getId());
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getSkill().getKey());
		}
		dto.setValue(this.shape.getIntegerValue());

		return dto;
	}

	@Override
	public RequestIntegerShapeDTO buildRequestDTO() {

		final RequestIntegerShapeDTO dto = new RequestIntegerShapeDTO();
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getSkill().getKey());
		}
		dto.setValue(this.shape.getIntegerValue());
		dto.setUserId(this.shape.getUser());

		return dto;
	}

}
