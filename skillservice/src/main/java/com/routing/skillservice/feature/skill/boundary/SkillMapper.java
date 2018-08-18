package com.routing.skillservice.feature.skill.boundary;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import com.routing.skillservice.dto.skill.BooleanSkillDTO;
import com.routing.skillservice.dto.skill.EnumSkillDTO;
import com.routing.skillservice.dto.skill.IntegerSkillDTO;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestBooleanSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestEnumSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestIntegerSkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.feature.skill.exception.SkillException;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;

/**
 * <b>com.routing.skillservice.feature.skill.boundary.SkillMapper</b>
 * <p>
 *   mapper for transforming {@link Skill} to {@link SkillDTO} and vice versa
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class SkillMapper {

    /**
     * map {@link Skill} to {@link SkillDTO}
     * can handle all derived classes of {@link Skill}
     * @param skill skill to map
     * @return mapped skill
     */
	public SkillDTO toDTO(final Skill skill) {

		if (null == skill) {
			return null;
		}

		if (skill instanceof IntegerSkill) {
			return this.toDTO((IntegerSkill) skill);
		} else if (skill instanceof BooleanSkill) {
			return this.toDTO((BooleanSkill) skill);
		} else if (skill instanceof EnumSkill) {
			return this.toDTO((EnumSkill) skill);
		} else {
			throw SkillException.UNKNOWN_TYPE;
		}

	}

    /**
     * map {@link IntegerSkill} to {@link IntegerSkillDTO}
     * @param skill skill to map
     * @return mapped skill
     */
	public IntegerSkillDTO toDTO(final IntegerSkill skill) {

		if (null == skill) {
			return null;
		}

		final IntegerSkillDTO skillDTO = new IntegerSkillDTO();
		skillDTO.setMultiplier(skill.getMultiplier());
		this.copyValues(skillDTO, skill);

		return skillDTO;
	}

    /**
     * map {@link BooleanSkill} to {@link BooleanSkillDTO}
     * @param skill skill to map
     * @return mapped skill
     */
	public BooleanSkillDTO toDTO(final BooleanSkill skill) {

		if (null == skill) {
			return null;
		}

		final BooleanSkillDTO skillDTO = new BooleanSkillDTO();
		this.copyValues(skillDTO, skill);

		return skillDTO;
	}

    /**
     * map {@link EnumSkill} to {@link EnumSkillDTO}
     * @param skill skill to map
     * @return mapped skill
     */
	public EnumSkillDTO toDTO(final EnumSkill skill) {

		if (null == skill) {
			return null;
		}

		final EnumSkillDTO skillDTO = new EnumSkillDTO();
		this.copyValues(skillDTO, skill);
		skillDTO.setValues(skill.getValue());

		return skillDTO;
	}

	private void copyValues(final SkillDTO to, final Skill from) {

		if (null == to || null == from) {
			return;
		}

		to.setKey(from.getKey());
		to.setLabel(from.getLabel());
	}

    /**
     * map {@link RequestSkillDTO} to {@link Skill}
     * can handle all derived classes of {@link RequestSkillDTO}
     * @param skillDTO skill to map
     * @return mapped skill
     */
	public Skill toModel(final RequestSkillDTO skillDTO) {

		if (null == skillDTO) {
			return null;
		}

		if (skillDTO instanceof RequestIntegerSkillDTO) {
			return this.toModel((RequestIntegerSkillDTO) skillDTO);
		} else if (skillDTO instanceof RequestBooleanSkillDTO) {
			return this.toModel((RequestBooleanSkillDTO) skillDTO);
		} else if (skillDTO instanceof RequestEnumSkillDTO) {
			return this.toModel((RequestEnumSkillDTO) skillDTO);
		} else {
			throw SkillException.UNKNOWN_TYPE;
		}

	}

    /**
     * map {@link RequestBooleanSkillDTO} to {@link BooleanSkill}
     * @param skillDTO skill to map
     * @return mapped skill
     */
	public BooleanSkill toModel(final RequestBooleanSkillDTO skillDTO) {

		if (null == skillDTO) {
			return null;
		}

		final BooleanSkill skill = new BooleanSkill();
		this.copyValues(skill, skillDTO);

		return skill;
	}

    /**
     * map {@link RequestIntegerSkillDTO} to {@link IntegerSkill}
     * @param skillDTO skill to map
     * @return mapped skill
     */
	public IntegerSkill toModel(final RequestIntegerSkillDTO skillDTO) {

		if (null == skillDTO) {
			return null;
		}

		final IntegerSkill skill = new IntegerSkill();
		skill.setMultiplier(skillDTO.getMultiplier());
		this.copyValues(skill, skillDTO);

		return skill;
	}

    /**
     * map {@link RequestEnumSkillDTO} to {@link EnumSkill}
     * @param skillDTO skill to map
     * @return mapped skill
     */
	public EnumSkill toModel(final RequestEnumSkillDTO skillDTO) {

		if (null == skillDTO) {
			return null;
		}

		final EnumSkill skill = new EnumSkill();
		this.copyValues(skill, skillDTO);
		skill.setValue(skillDTO.getValues());

		return skill;
	}

	private void copyValues(final Skill to, final RequestSkillDTO from) {

		if (null == to || null == from) {
			return;
		}

		to.setKey(from.getKey());
		to.setLabel(from.getLabel());
	}

    /**
     * map set of {@link Skill} to {@link SkillDTO}
     * @param skills set of skills to map
     * @return mapped set of skills
     */
	public Set<SkillDTO> toDTO(final Set<Skill> skills) {

		if (null == skills) {
			return null;
		}

		final Set<SkillDTO> skillsDTO = new LinkedHashSet<>(skills.size());
		skills.forEach((skill) -> {
			skillsDTO.add(this.toDTO(skill));
		});

		return skillsDTO;
	}

}
