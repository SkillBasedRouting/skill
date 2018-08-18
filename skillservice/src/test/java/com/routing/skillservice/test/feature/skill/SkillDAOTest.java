package com.routing.skillservice.test.feature.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.skillservice.feature.skill.exception.SkillNotFoundException;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;
import com.routing.skillservice.test.mock.SkillDAOProxy;

public class SkillDAOTest {

	private static SkillDAOProxy skillDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		SkillDAOTest.skillDAO = new SkillDAOProxy(emFactory.createEntityManager());
	}

	@Before
	public void startTransaction() {
		SkillDAOTest.skillDAO.em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		SkillDAOTest.skillDAO.em().getTransaction().commit();
	}

	private BooleanSkillBuilder getDefaultBooleanBuilder() {
		return BooleanSkillBuilder.defaultBuilder();
	}

	private IntegerSkillBuilder getDefaultIntegerBuilder() {
		return IntegerSkillBuilder.defaultBuilder();
	}

	private EnumSkillBuilder getDefaultEnumBuilder() {
		return EnumSkillBuilder.defaultBuilder();
	}

	@Test
	public void should_CreateBooleanSkill_On_Create() {

		final BooleanSkillBuilder builder = this.getDefaultBooleanBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey());

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_CreateIntegerSkill_On_Create() {

		final IntegerSkillBuilder builder = this.getDefaultIntegerBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey());

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_CreateEnumSkill_On_Create() {

		final EnumSkillBuilder builder = this.getDefaultEnumBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey());

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_GetIntegerSkill_On_TypedGet() {

		final IntegerSkillBuilder builder = this.getDefaultIntegerBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey(), IntegerSkill.class);

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_GetEnumSkill_On_TypedGet() {

		final EnumSkillBuilder builder = this.getDefaultEnumBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey(), EnumSkill.class);

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_GetBooleanSkill_On_TypedGet() {

		final BooleanSkillBuilder builder = this.getDefaultBooleanBuilder();

		final Skill skill = skillDAO.persist(builder.build());

		final Skill getSkill = skillDAO.get(skill.getKey(), BooleanSkill.class);

		assertEquals(skill, getSkill);
	}

	@Test
	public void should_Throw_SkillNotFoundException_When_SkillDoesNotExists_On_Get() {

		try {
			skillDAO.get(UUID.randomUUID().toString());
			fail("can get non existing skill");
		} catch (SkillNotFoundException e) {

		}
	}

	@Test
	public void should_Throw_SkillNotFoundException_When_SkillIDInTypeDoesNotExists_On_TypedGet() {

		final BooleanSkillBuilder builder = this.getDefaultBooleanBuilder();
		final Skill skill = skillDAO.persist(builder.build());

		try {
			skillDAO.get(skill.getKey(), IntegerSkill.class);
			fail("can get skill with wrong type on typedget");
		} catch (SkillNotFoundException e) {

		}

	}

	@Test
	public void should_GetAll_On_GetAll() {

		final Set<Skill> skills = new LinkedHashSet<Skill>(3);
		skills.add(skillDAO.persist(this.getDefaultBooleanBuilder().build()));
		skills.add(skillDAO.persist(this.getDefaultIntegerBuilder().build()));
		skills.add(skillDAO.persist(this.getDefaultEnumBuilder().build()));

		final Set<Skill> getAll = skillDAO.getAll();

		skills.forEach((skill) -> {
			assertTrue(getAll.contains(skill));
		});
	}

	// getMultipliers

	@Test
	public void should_ReturnMultipliers_On_GetMultipliers() {

		final Double multiplier = 3.5D;
		final IntegerSkill skill = (IntegerSkill) skillDAO
				.persist(this.getDefaultIntegerBuilder().setMultiplier(multiplier).build());

		final Map<String, Double> multipliers = skillDAO
				.getMultipliers(Arrays.asList(skill.getKey()).stream().collect(Collectors.toSet()));

		assertEquals(1, multipliers.size());
		assertEquals(multiplier, multipliers.get(skill.getKey()));
	}

	@Test
	public void should_ReturnNoMultipliers_When_KeyDoesNotExist_On_GetMultipliers() {

		final String nonExistingKey = UUID.randomUUID().toString();

		final Map<String, Double> multipliers = skillDAO
				.getMultipliers(Arrays.asList(nonExistingKey).stream().collect(Collectors.toSet()));

		assertEquals(0, multipliers.size());
	}
	
	@Test
	public void should_ReturnNoMultipliers_When_SkillDoesNotHaveMultiplier_On_GetMultipliers() {

		final IntegerSkill skill = (IntegerSkill) skillDAO
				.persist(this.getDefaultIntegerBuilder().build());

		final Map<String, Double> multipliers = skillDAO
				.getMultipliers(Arrays.asList(skill.getKey()).stream().collect(Collectors.toSet()));

		assertEquals(0, multipliers.size());
	}


}