package com.routing.skillservice.test.builder;

import java.util.UUID;

import com.routing.skillservice.dto.shape.BooleanShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestBooleanShapeDTO;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.skill.model.BooleanSkill;

public class BooleanShapeBuilder extends ShapeBuilder<Boolean> {

	public static BooleanShapeBuilder defaultBuilder() {
		return new BooleanShapeBuilder().setSkill(BooleanSkillBuilder.defaultBuilder().build()).setValue(true)
				.setId(UUID.randomUUID().toString());
	}

	private BooleanShape shape;

	public BooleanShapeBuilder() {
		this.shape = new BooleanShape();
	}

	public BooleanShapeBuilder(BooleanShape shape) {
		super();
		this.shape = shape;
	}

	@Override
	public BooleanShapeBuilder setValue(final Boolean value) {
		this.shape.setBooleanValue(value);
		return this;
	}

	@Override
	public BooleanShapeBuilder setId(final String id) {
		this.shape.setId(id);
		return this;
	}

	public BooleanShapeBuilder setSkill(final BooleanSkill skill) {
		this.shape.setBooleanSkill(skill);
		return this;
	}

	@Override
	public BooleanShapeBuilder setUser(final String user) {
		this.shape.setUser(user);
		return this;
	}

	@Override
	public BooleanShape build() {

		final BooleanShape shape = new BooleanShape();
		shape.setBooleanSkill(this.shape.getBooleanSkill());
		shape.setBooleanValue(this.shape.getBooleanValue());
		shape.setId(this.shape.getId());
		shape.setUser(this.shape.getUser());

		return shape;
	}

	@Override
	public BooleanShapeDTO buildDTO() {

		final BooleanShapeDTO dto = new BooleanShapeDTO();
		dto.setId(this.shape.getId());
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getSkill().getKey());
		}
		dto.setValue(this.shape.getBooleanValue());

		return dto;
	}

	@Override
	public RequestBooleanShapeDTO buildRequestDTO() {

		final RequestBooleanShapeDTO dto = new RequestBooleanShapeDTO();
		if (null != this.shape.getSkill()) {
			dto.setSkillKey(this.shape.getBooleanSkill().getKey());
		}
		dto.setValue(this.shape.getBooleanValue());
		dto.setUserId(this.shape.getUser());

		return dto;
	}

}
