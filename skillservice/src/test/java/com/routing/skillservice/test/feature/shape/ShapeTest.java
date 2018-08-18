package com.routing.skillservice.test.feature.shape;

import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.EnumShapeBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;

public class ShapeTest {

	public BooleanShapeBuilder defaultBooleanShapeBuilder() {
		return BooleanShapeBuilder.defaultBuilder();
	}

	public IntegerShapeTestBuilder defaultIntegerShapeBuilder() {
		return IntegerShapeTestBuilder.defaultBuilder();
	}

	public EnumShapeBuilder defaultEnumShapeBuilder() {
		return EnumShapeBuilder.defaultBuilder();
	}

}
