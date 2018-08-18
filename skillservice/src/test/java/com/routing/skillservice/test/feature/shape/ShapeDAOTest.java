package com.routing.skillservice.test.feature.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.exception.ShapeNotFoundException;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.skill.dao.SkillDAO;
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
import com.routing.skillservice.test.mock.ShapeDAOProxy;
import com.routing.skillservice.test.mock.SkillDAOProxy;

public class ShapeDAOTest extends ShapeTest {

	private static ShapeDAO shapeDAO;
	private static SkillDAO skillDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		final EntityManager em = emFactory.createEntityManager();
		shapeDAO = new ShapeDAOProxy(em);
		skillDAO = new SkillDAOProxy(em);
	}

	@Before
	public void startTransaction() {
		((ShapeDAOProxy) ShapeDAOTest.shapeDAO).em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		((ShapeDAOProxy) ShapeDAOTest.shapeDAO).em().getTransaction().commit();
	}

	@Override
	public BooleanShapeBuilder defaultBooleanShapeBuilder() {
		return super.defaultBooleanShapeBuilder().setSkill(this.booleanSkill());
	}

	@Override
	public IntegerShapeTestBuilder defaultIntegerShapeBuilder() {
		return super.defaultIntegerShapeBuilder().setSkill(this.integerSkill());
	}

	@Override
	public EnumShapeBuilder defaultEnumShapeBuilder() {
		return super.defaultEnumShapeBuilder().setSkill(this.enumSkill());
	}

	public EnumSkill enumSkill() {
		return (EnumSkill) skillDAO.persist(EnumSkillBuilder.defaultBuilder().build());
	}

	public BooleanSkill booleanSkill() {
		return (BooleanSkill) skillDAO.persist(BooleanSkillBuilder.defaultBuilder().build());
	}

	public IntegerSkill integerSkill() {
		return (IntegerSkill) skillDAO.persist(IntegerSkillBuilder.defaultBuilder().build());
	}

	public String user() {
		return UUID.randomUUID().toString();
	}

	// create

	@Test
	public void should_CreateBooleanShape_On_Create() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		assertTrue(shape instanceof BooleanShape);
		assertNotNull(shape.getInternId());
	}

	@Test
	public void should_CreateIntegerShape_On_Create() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		assertTrue(shape instanceof IntegerShape);
		assertNotNull(shape.getInternId());
	}

	@Test
	public void should_CreateEnumShape_On_Create() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		assertTrue(shape instanceof EnumShape);
		assertNotNull(shape.getInternId());
	}

	// GetById

	@Test
	public void should_Throw_ShapeNotFoundException_When_IdDoesNotExist_On_GetById() {

		try {
			shapeDAO.get(UUID.randomUUID().toString());
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetBooleanShape_On_GetById() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(shape.getId());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_GetIntegerShape_On_GetById() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(shape.getId());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_GetEnumShape_On_GetById() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(shape.getId());

		assertEquals(shape, getShape);
	}

	// GetBySkill

	@Test
	public void should_GetBooleanShape_On_GetBySkill() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getBooleanSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_BooleanSkill_On_GetBySkill() {

		final String user = user();
		final BooleanSkill skill = booleanSkill();

		try {
			shapeDAO.get(user, skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetBooleanShape_On_GetByGenericSkill() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_BooleanSkill_On_GetByGenericSkill() {

		final String user = user();
		final BooleanSkill skill = booleanSkill();

		try {
			shapeDAO.get(user, (Skill) skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_IntegerSkill_On_GetBySkill() {

		final String user = user();
		final IntegerSkill skill = integerSkill();

		try {
			shapeDAO.get(user, skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetIntegerShape_On_GetBySkill() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getIntegerSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_IntegerSkill_On_GetByGenericSkill() {

		final String user = user();
		final IntegerSkill skill = integerSkill();

		try {
			shapeDAO.get(user, (Skill) skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetIntegerShape_On_GetByGenericSkill() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_IntegerSkill_On_GetBySGenerickill() {

		final String user = user();
		final IntegerSkill skill = integerSkill();

		try {
			shapeDAO.get(user, (Skill) skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetEnumShape_On_GetBySkill() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getEnumSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_EnumSkill_On_GetBySkill() {

		final String user = user();
		final EnumSkill skill = enumSkill();

		try {
			shapeDAO.get(user, skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	@Test
	public void should_GetEnumShape_On_GetByGenericSkill() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final String user = user();
		builder.setUser(user);

		final Shape shape = shapeDAO.persist(builder.build());

		final Shape getShape = shapeDAO.get(user, builder.build().getSkill());

		assertEquals(shape, getShape);
	}

	@Test
	public void should_Throw_ShapeNotFoundException_With_EnumSkill_On_GetByGenericSkill() {

		final String user = user();
		final EnumSkill skill = enumSkill();

		try {
			shapeDAO.get(user, (Skill) skill);
			fail("can get non existing shape");
		} catch (ShapeNotFoundException e) {

		}

	}

	// exist

	@Test
	public void should_ReturnTrue_WithBooleanSkill_On_Exists() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();

		final String user = user();
		builder.setUser(user);

		shapeDAO.persist(builder.build());

		assertTrue(shapeDAO.exists(user, builder.build().getSkill()));
	}

	@Test
	public void should_ReturnTrue_WithIntegerSkill_On_Exists() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();

		final String user = user();
		builder.setUser(user);

		shapeDAO.persist(builder.build());

		assertTrue(shapeDAO.exists(user, builder.build().getSkill()));
	}

	@Test
	public void should_ReturnTrue_WithEnumSkill_On_Exists() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();

		final String user = user();
		builder.setUser(user);

		shapeDAO.persist(builder.build());

		assertTrue(shapeDAO.exists(user, builder.build().getSkill()));
	}

	@Test
	public void should_ReturnFalse_WithBooleanSkill_When_ShapeDoesNotExist_On_Exists() {

		final BooleanSkill skill = booleanSkill();
		final String user = user();

		assertFalse(shapeDAO.exists(user, skill));
	}

	@Test
	public void should_ReturnFalse_WithIntegerSkill_When_ShapeDoesNotExist_On_Exists() {

		final IntegerSkill skill = integerSkill();
		final String user = user();

		assertFalse(shapeDAO.exists(user, skill));
	}

	@Test
	public void should_ReturnFalse_WithEnumSkill_When_ShapeDoesNotExist_On_Exists() {

		final EnumSkill skill = enumSkill();
		final String user = user();

		assertFalse(shapeDAO.exists(user, skill));
	}

}
