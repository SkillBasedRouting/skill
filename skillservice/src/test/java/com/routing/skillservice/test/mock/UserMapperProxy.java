package com.routing.skillservice.test.mock;

import java.util.Set;

import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.user.boundary.UserMapper;

public class UserMapperProxy extends UserMapper {

	public UserMapperProxy(final Set<Shape> shapes) {
		super.shapeMapper = new ShapeMapperProxy();
		super.shapeDAO = new ShapeDAOMock(shapes);
	}

}
