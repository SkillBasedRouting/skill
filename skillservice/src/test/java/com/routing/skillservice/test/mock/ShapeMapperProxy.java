package com.routing.skillservice.test.mock;

import com.routing.skillservice.feature.shape.boundary.ShapeMapper;

public class ShapeMapperProxy extends ShapeMapper {

	public ShapeMapperProxy() {
		super.skillDAO = new SkillDAOMock();
	}

}
