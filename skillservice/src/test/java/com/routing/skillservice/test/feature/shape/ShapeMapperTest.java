package com.routing.skillservice.test.feature.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import com.routing.skillservice.dto.shape.BooleanShapeDTO;
import com.routing.skillservice.dto.shape.EnumShapeDTO;
import com.routing.skillservice.dto.shape.IntegerShapeDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.feature.shape.boundary.ShapeMapper;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumShapeBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;
import com.routing.skillservice.test.mock.ShapeMapperProxy;

public class ShapeMapperTest extends ShapeTest {

	private ShapeMapper shapeMapper = new ShapeMapperProxy();

	// BooleanShape / model -> dto

	@Test
	public void should_MapBooleanShape_On_ToDtoGeneric() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final ShapeDTO shape = ((Shape) builder.build()).dto();

		assertTrue(shape instanceof BooleanShapeDTO);
	}

	@Test
	public void should_MapId_In_BooleanShape_On_ToDto() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		final String id = UUID.randomUUID().toString();
		builder.setId(id);

		final ShapeDTO dto = builder.build().dto();

		assertEquals(dto.getId(), id);
	}

	@Test
	public void should_MapValue_In_BooleanShape_On_ToDto() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		final Boolean value = false;
		builder.setValue(value);

		final BooleanShapeDTO dto = builder.build().dto();

		assertEquals(dto.getValue(), value);
	}

	@Test
	public void should_MapSkill_In_BooleanShape_On_ToDto() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		final BooleanSkill skill = BooleanSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final BooleanShapeDTO dto = builder.build().dto();

		assertEquals(dto.getSkillKey(), skill.getKey());
	}

	// IntegerShape / model -> dto

	@Test
	public void should_MapIntegerShape_On_ToDtoGeneric() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final ShapeDTO shape = ((Shape) builder.build()).dto();

		assertTrue(shape instanceof IntegerShapeDTO);
	}

	@Test
	public void should_MapId_In_IntegerShape_On_ToDto() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		final String id = UUID.randomUUID().toString();
		builder.setId(id);

		final ShapeDTO dto = builder.build().dto();

		assertEquals(dto.getId(), id);
	}

	@Test
	public void should_MapValue_In_IntegerShape_On_ToDto() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		final Integer value = 33;
		builder.setValue(value);

		final IntegerShapeDTO dto = builder.build().dto();

		assertEquals(dto.getValue(), value);
	}

	@Test
	public void should_MapSkill_In_IntegerShape_On_ToDto() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		final IntegerSkill skill = IntegerSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final IntegerShapeDTO dto = builder.build().dto();

		assertEquals(dto.getSkillKey(), skill.getKey());
	}

	// EnumShape / model -> dto

	@Test
	public void should_MapEnumShape_On_ToDtoGeneric() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final ShapeDTO shape = ((Shape) builder.build()).dto();

		assertTrue(shape instanceof EnumShapeDTO);
	}

	@Test
	public void should_MapId_In_EnumShape_On_ToDto() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		final String id = UUID.randomUUID().toString();
		builder.setId(id);

		final ShapeDTO dto = builder.build().dto();

		assertEquals(dto.getId(), id);
	}

	@Test
	public void should_MapValue_In_EnumShape_On_ToDto() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		final Set<String> value = new LinkedHashSet<>(2);
		value.add(UUID.randomUUID().toString());
		value.add(UUID.randomUUID().toString());
		builder.setValue(value);

		final EnumShapeDTO dto = builder.build().dto();

		assertEquals(dto.getValue(), value);
	}

	@Test
	public void should_MapSkill_In_EnumShape_On_ToDto() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		final EnumSkill skill = EnumSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final EnumShapeDTO dto = builder.build().dto();

		assertEquals(dto.getSkillKey(), skill.getKey());
	}

	// List / model -> dto

	@Test
	public void should_MapSet_On_ToDto() {

		final Set<Shape> shapes = new HashSet<>(3);
		shapes.add(this.defaultBooleanShapeBuilder().build());
		shapes.add(this.defaultIntegerShapeBuilder().build());
		shapes.add(this.defaultEnumShapeBuilder().build());

		final Set<ShapeDTO> shapesDTO = shapes.stream().map(Shape::dto).collect(Collectors.toSet());

		shapesDTO.forEach(shapeDTO -> {
			assertTrue(shapes.stream().map(Shape::getSkill).map(Skill::getKey).collect(Collectors.toSet())
					.contains(shapeDTO.getSkillKey()));
		});
	}

	// BooleanShape / request -> model

	@Test
	public void should_MapBooleanShape_On_ToModelGeneric() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final Shape shape = this.shapeMapper.toModel((RequestShapeDTO) builder.buildRequestDTO());

		assertTrue(shape instanceof BooleanShape);
	}

	@Test
	public void should_MapValue_In_BooleanShape_On_ToModel() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		final Boolean value = true;
		builder.setValue(value);

		final BooleanShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(value, shape.getBooleanValue());
	}

	@Test
	public void should_MapSkill_In_BooleanShape_On_ToModel() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		final BooleanSkill skill = BooleanSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final BooleanShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(skill.getKey(), shape.getBooleanSkill().getKey());
	}

	// IntegerShape / request -> model

	@Test
	public void should_MapIntegerShape_On_ToModelGeneric() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final Shape shape = this.shapeMapper.toModel((RequestShapeDTO) builder.buildRequestDTO());

		assertTrue(shape instanceof IntegerShape);
	}

	@Test
	public void should_MapValue_In_IntegerShape_On_ToModel() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		final Integer value = 19;
		builder.setValue(value);

		final IntegerShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(value, shape.getIntegerValue());
	}

	@Test
	public void should_MapSkill_In_IntegerShape_On_ToModel() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		final IntegerSkill skill = IntegerSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final IntegerShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(skill.getKey(), shape.getIntegerSkill().getKey());
	}

	// EnumShape / request -> model

	@Test
	public void should_MapEnumShape_On_ToModelGeneric() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final Shape shape = this.shapeMapper.toModel((RequestShapeDTO) builder.buildRequestDTO());

		assertTrue(shape instanceof EnumShape);
	}

	@Test
	public void should_MapValue_In_EnumShape_On_ToModel() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		final Set<String> value = new LinkedHashSet<>(2);
		value.add(UUID.randomUUID().toString());
		value.add(UUID.randomUUID().toString());
		builder.setValue(value);

		final EnumShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(value, shape.getEnumValue());
	}

	@Test
	public void should_MapSkill_In_EnumShape_On_ToModel() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		final EnumSkill skill = EnumSkillBuilder.defaultBuilder().build();
		builder.setSkill(skill);

		final EnumShape shape = this.shapeMapper.toModel(builder.buildRequestDTO());

		assertEquals(skill.getKey(), shape.getEnumSkill().getKey());
	}

	// List / request -> model

	@Test
	public void should_MapList_On_ToModel() {

		final Set<RequestShapeDTO> reqShapes = new HashSet<>(3);
		reqShapes.add(this.defaultBooleanShapeBuilder().buildRequestDTO());
		reqShapes.add(this.defaultIntegerShapeBuilder().buildRequestDTO());
		reqShapes.add(this.defaultEnumShapeBuilder().buildRequestDTO());

		final Set<Shape> shapes = this.shapeMapper.toModel(reqShapes);

		shapes.forEach(shape -> {
			assertTrue(reqShapes.stream().map(RequestShapeDTO::getSkillKey).collect(Collectors.toSet())
					.contains(shape.getSkill().getKey()));
		});
	}

}
