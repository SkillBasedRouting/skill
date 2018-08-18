package com.routing.skillservice.test.feature.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.skillservice.feature.query.QueryDAO;
import com.routing.skillservice.feature.query.QueryRequest;
import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.skill.dao.SkillDAO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumShapeBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;
import com.routing.skillservice.test.mock.QueryDAOProxy;
import com.routing.skillservice.test.mock.ShapeDAOProxy;
import com.routing.skillservice.test.mock.SkillDAOProxy;

public class UserMatchDAOTest {

	static SkillDAO skillDAO;
	static QueryDAO queryDAO;
	static ShapeDAO shapeDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		final EntityManager em = emFactory.createEntityManager();
		UserMatchDAOTest.skillDAO = new SkillDAOProxy(em);
		UserMatchDAOTest.queryDAO = new QueryDAOProxy(em);
		UserMatchDAOTest.shapeDAO = new ShapeDAOProxy(em);
	}

	@Before
	public void startTransaction() {
		((SkillDAOProxy) UserMatchDAOTest.skillDAO).em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		((SkillDAOProxy) UserMatchDAOTest.skillDAO).em().getTransaction().commit();
	}

	public String user() {
		return UUID.randomUUID().toString();
	}

	public BooleanSkill booleanSkill() {
		return (BooleanSkill) skillDAO.persist(BooleanSkillBuilder.defaultBuilder().build());
	}

	public BooleanShape addBooleanSkill(final String user, final Boolean value) {
		final BooleanSkill skill = booleanSkill();
		BooleanShapeBuilder builder = BooleanShapeBuilder.defaultBuilder();
		builder.setSkill(skill);
		builder.setValue(value);
		builder.setUser(user);

		final BooleanShape shape = builder.build();
		shapeDAO.persist(shape);

		return shape;
	}

	public IntegerSkill integerSkill() {
		return (IntegerSkill) skillDAO.persist(IntegerSkillBuilder.defaultBuilder().build());
	}

	public IntegerShape addIntegerSkill(final String user, final Integer value) {
		final IntegerSkill skill = integerSkill();
		IntegerShapeTestBuilder builder = IntegerShapeTestBuilder.defaultBuilder();
		builder.setSkill(skill);
		builder.setValue(value);
		builder.setUser(user);

		final IntegerShape shape = builder.build();
		shapeDAO.persist(shape);

		return shape;
	}

	public EnumSkill enumSkill() {
		return (EnumSkill) skillDAO.persist(EnumSkillBuilder.defaultBuilder().build());
	}

	public EnumShape addEnumSkill(final String user, final Set<String> value) {
		final EnumSkill skill = enumSkill();
		EnumShapeBuilder builder = EnumShapeBuilder.defaultBuilder();
		builder.setSkill(skill);
		builder.setValue(value);
		builder.setUser(user);

		final EnumShape shape = builder.build();
		shapeDAO.persist(shape);

		return shape;
	}

	@Test
	public void should_ReturnUser_ThatMatchesRequirements_With_SingleBooleanRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final BooleanShape shape = this.addBooleanSkill(user, true);

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(shape);
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(1, userShapes.size());
		assertTrue(userShapes.stream().filter(u -> u.equals(user)).findAny().isPresent());
	}

	@Test
	public void should_ReturnUser_ThatMatchesRequirements_With_SingleIntegerRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final IntegerShape shape = this.addIntegerSkill(user, 73);

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(shape);
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(1, userShapes.size());
		assertTrue(userShapes.stream().filter(u -> u.equals(user)).findAny().isPresent());
	}

	@Test
	public void should_ReturnUser_ThatMatchesRequirements_With_SingleEnumRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final EnumShape shape = this.addEnumSkill(user, Arrays.asList("bla").stream().collect(Collectors.toSet()));

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(shape);
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(1, userShapes.size());
		assertTrue(userShapes.stream().filter(u -> u.equals(user)).findAny().isPresent());
	}

	@Test
	public void should_ReturnUser_ThatMatchesRequirements_With_MultipleRequirements_On_Matches() {

		final String user = user();
		user();
		user();
		final BooleanShape booleanShape = this.addBooleanSkill(user, true);
		final IntegerShape integerShape = this.addIntegerSkill(user, 50);
		final EnumShape enumShape = this.addEnumSkill(user, Arrays.asList("bla").stream().collect(Collectors.toSet()));

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(booleanShape);
		request.getShapes().add(integerShape);
		request.getShapes().add(enumShape);
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(1, userShapes.size());
		assertTrue(userShapes.stream().filter(u -> u.equals(user)).findAny().isPresent());
	}

	@Test
	public void should_NotReturnUser_AsNotMatchesRequirements_With_SingleBooleanRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final BooleanShape shape = this.addBooleanSkill(user, true);
		final BooleanShapeBuilder builder = new BooleanShapeBuilder();
		builder.setSkill(shape.getBooleanSkill());
		builder.setValue(false);

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(builder.build());
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(0, userShapes.size());
	}

	@Test
	public void should_NotReturnUser_AsNotMatchesRequirements_With_SingleIntegerRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final IntegerShape shape = this.addIntegerSkill(user, 50);
		final IntegerShapeTestBuilder builder = new IntegerShapeTestBuilder();
		builder.setSkill(shape.getIntegerSkill());
		builder.setValue(60);

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(builder.build());
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(0, userShapes.size());
	}

	@Test
	public void should_NotReturnUser_AsNotMatchesRequirements_With_SingleEnumRequirement_On_Matches() {

		final String user = user();
		user();
		user();
		final EnumShape shape = this.addEnumSkill(user, Arrays.asList("bla").stream().collect(Collectors.toSet()));
		final EnumShapeBuilder builder = new EnumShapeBuilder();
		builder.setSkill(shape.getEnumSkill());
		builder.setValue(Arrays.asList("blub").stream().collect(Collectors.toSet()));

		final QueryRequest request = new QueryRequest();
		request.setShapes(new LinkedHashSet<>(1));
		request.getShapes().add(builder.build());
		final Set<String> userShapes = queryDAO.matchShapes(request.getShapes());

		assertEquals(0, userShapes.size());
	}

}
