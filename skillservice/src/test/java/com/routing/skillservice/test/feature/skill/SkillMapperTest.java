package com.routing.skillservice.test.feature.skill;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.routing.skillservice.dto.skill.BooleanSkillDTO;
import com.routing.skillservice.dto.skill.EnumSkillDTO;
import com.routing.skillservice.dto.skill.IntegerSkillDTO;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestBooleanSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestEnumSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestIntegerSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.feature.skill.boundary.SkillMapper;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;

public class SkillMapperTest {

	private SkillMapper skillMapper = new SkillMapper();

	public BooleanSkillBuilder defaultBooleanBuilder() {
		return BooleanSkillBuilder.defaultBuilder();
	}

	public IntegerSkillBuilder defaultIntegerBuilder() {
		return IntegerSkillBuilder.defaultBuilder();
	}

	public EnumSkillBuilder defaultEnumBuilder() {
		return EnumSkillBuilder.defaultBuilder();
	}

	// Boolean / model -> dto

	@Test
	public void should_MapBooleanSkill_InGenericMethod_On_ToDTO() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final BooleanSkill skill = builder.build();

		final SkillDTO skillDTO = this.skillMapper.toDTO((Skill) skill);

		assertTrue(skillDTO instanceof BooleanSkillDTO);
	}

	@Test
	public void should_MapKey_In_BooleanSkill_On_ToDTO() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final BooleanSkill skill = builder.build();

		final BooleanSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getKey(), skillDTO.getKey());
	}

	@Test
	public void should_MapLabel_In_BooleanSkill_On_ToDTO() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final BooleanSkill skill = builder.build();

		final BooleanSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getLabel(), skillDTO.getLabel());
	}

	// Integer / model -> dto

	@Test
	public void should_MapIntegerSkill_InGenericMethod_On_ToDTO() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final IntegerSkill skill = builder.build();

		final SkillDTO skillDTO = this.skillMapper.toDTO((Skill) skill);

		assertTrue(skillDTO instanceof IntegerSkillDTO);
	}

	@Test
	public void should_MapKey_In_IntegerSkill_On_ToDTO() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final IntegerSkill skill = builder.build();

		final IntegerSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getKey(), skillDTO.getKey());
	}

	@Test
	public void should_MapLabel_In_IntegerSkill_On_ToDTO() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final IntegerSkill skill = builder.build();

		final IntegerSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getLabel(), skillDTO.getLabel());
	}

	// Enum / model -> dto

	@Test
	public void should_MapEnumSkill_InGenericMethod_On_ToDTO() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final EnumSkill skill = builder.build();

		final SkillDTO skillDTO = this.skillMapper.toDTO((Skill) skill);

		assertTrue(skillDTO instanceof EnumSkillDTO);
	}

	@Test
	public void should_MapKey_In_EnumSkill_On_ToDTO() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final EnumSkill skill = builder.build();

		final EnumSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getKey(), skillDTO.getKey());
	}

	@Test
	public void should_MapLabel_In_EnumSkill_On_ToDTO() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final EnumSkill skill = builder.build();

		final EnumSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getLabel(), skillDTO.getLabel());
	}

	@Test
	public void should_MapValues_In_EnumSkill_On_ToDTO() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		builder.addValue(UUID.randomUUID().toString());
		final EnumSkill skill = builder.build();

		final EnumSkillDTO skillDTO = this.skillMapper.toDTO(skill);

		assertEquals(skill.getValue(), skillDTO.getValues());
	}

	// Boolean / request -> model

	@Test
	public void should_MapBooleanSkill_InGenericMethod_On_ToModel() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final RequestBooleanSkillDTO skillDTO = builder.buildRequestDTO();

		final Skill skill = this.skillMapper.toModel((RequestSkillDTO) skillDTO);

		assertTrue(skill instanceof BooleanSkill);
	}

	@Test
	public void should_MapKey_In_BooleanSkill_On_ToModel() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final RequestBooleanSkillDTO skillDTO = builder.buildRequestDTO();

		final BooleanSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getKey(), skill.getKey());
	}

	@Test
	public void should_MapLabel_In_BooleanSkill_On_ToModel() {

		final BooleanSkillBuilder builder = this.defaultBooleanBuilder();
		final RequestBooleanSkillDTO skillDTO = builder.buildRequestDTO();

		final BooleanSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getLabel(), skill.getLabel());
	}

	// Integer / request -> model

	@Test
	public void should_MapIntegerSkill_InGenericMethod_On_ToModel() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final RequestIntegerSkillDTO skillDTO = builder.buildRequestDTO();

		final Skill skill = this.skillMapper.toModel((RequestSkillDTO) skillDTO);

		assertTrue(skill instanceof IntegerSkill);
	}

	@Test
	public void should_MapKey_In_IntegerSkill_On_ToModel() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final RequestIntegerSkillDTO skillDTO = builder.buildRequestDTO();

		final IntegerSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getKey(), skill.getKey());
	}

	@Test
	public void should_MapLabel_In_IntegerSkill_On_ToModel() {

		final IntegerSkillBuilder builder = this.defaultIntegerBuilder();
		final RequestIntegerSkillDTO skillDTO = builder.buildRequestDTO();

		final IntegerSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getLabel(), skill.getLabel());
	}

	// Enum / request -> model

	@Test
	public void should_MapEnumSkill_InGenericMethod_On_ToModel() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final RequestEnumSkillDTO skillDTO = builder.buildRequestDTO();

		final Skill skill = this.skillMapper.toModel((RequestSkillDTO) skillDTO);

		assertTrue(skill instanceof EnumSkill);
	}

	@Test
	public void should_MapKey_In_EnumSkill_On_ToModel() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final RequestEnumSkillDTO skillDTO = builder.buildRequestDTO();

		final EnumSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getKey(), skill.getKey());
	}

	@Test
	public void should_MapLabel_In_EnumSkill_On_ToModel() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		final RequestEnumSkillDTO skillDTO = builder.buildRequestDTO();

		final EnumSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getLabel(), skill.getLabel());
	}

	@Test
	public void should_MapValues_In_EnumSkill_On_ToModel() {

		final EnumSkillBuilder builder = this.defaultEnumBuilder();
		builder.addValue(UUID.randomUUID().toString());
		final RequestEnumSkillDTO skillDTO = builder.buildRequestDTO();

		final EnumSkill skill = this.skillMapper.toModel(skillDTO);

		assertEquals(skillDTO.getValues(), skill.getValue());
	}

}
