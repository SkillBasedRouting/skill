package com.routing.skillservice.test.mock;

import java.util.Set;

import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.EnumShapeBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;
import com.routing.skillservice.test.builder.ShapeBuilder;

public class ShapeDAOMock extends ShapeDAO {

	private Set<Shape> shapes;

	public ShapeDAOMock(final Set<Shape> shapes) {
		this.shapes = shapes;
	}

	@Override
	public Shape get(String id) {
		return BooleanShapeBuilder.defaultBuilder().setId(id).build();
	}

	@Override
	public boolean exists(String user, Skill skill) {
		return false;
	}

	@Override
	public BooleanShape get(String user, BooleanSkill booleanSkill) {
		return BooleanShapeBuilder.defaultBuilder().setUser(user).build();
	}

	@Override
	public IntegerShape get(String user, IntegerSkill integerSkill) {
		return IntegerShapeTestBuilder.defaultBuilder().setUser(user).build();
	}

	@Override
	public EnumShape get(String user, EnumSkill enumSkill) {
		return EnumShapeBuilder.defaultBuilder().setUser(user).build();
	}

	@Override
	public Shape get(String user, Skill skill) {
		ShapeBuilder<?> shapeBuilder;
		if (skill instanceof BooleanSkill) {
			shapeBuilder = BooleanShapeBuilder.defaultBuilder().setSkill((BooleanSkill) skill);
		} else if (skill instanceof IntegerSkill) {
			shapeBuilder = IntegerShapeTestBuilder.defaultBuilder().setSkill((IntegerSkill) skill);
		} else if (skill instanceof EnumSkill) {
			shapeBuilder = EnumShapeBuilder.defaultBuilder().setSkill((EnumSkill) skill);
		} else {
			throw new IllegalArgumentException("unknown skill");
		}
		shapeBuilder.setUser(user);
		return shapeBuilder.build();
	}

	@Override
	public Set<Shape> getByUser(String user) {
		return this.shapes;
	}

	@Override
	public Set<Shape> getByUser(String user, Set<Shape> requirements) {
		return this.shapes;
	}

	@Override
	public Shape persist(Shape shape) {
		return shape;
	}

}
