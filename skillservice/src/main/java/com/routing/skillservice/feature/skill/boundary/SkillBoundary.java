package com.routing.skillservice.feature.skill.boundary;

import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.exception.ExceptionLogInterceptor;
import com.routing.skillservice.feature.skill.dao.SkillDAO;
import com.routing.skillservice.feature.skill.exception.SkillException;
import com.routing.skillservice.feature.skill.model.Skill;

/**
 * <b>com.routing.skillservice.feature.skill.boundary.SkillBoundary</b>
 * <p>
 *   boundary for all skill actions, starts new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ ExceptionLogInterceptor.class })
public class SkillBoundary {

	@Inject
	private SkillDAO skillDAO;

	@Inject
	private SkillMapper skillMapper;

    /**
     * create a new skill
     * @param requestSkillDTO skill to create
     * @return created skill
     * @throws SkillException if skill is null
     */
	public SkillDTO create(final RequestSkillDTO requestSkillDTO) {

        if (null == requestSkillDTO) {
            throw SkillException.REQUIRED;
        }

		Skill skill = this.skillMapper.toModel(requestSkillDTO);

        skill.validate();

		skill = this.skillDAO.persist(skill);

		return this.skillMapper.toDTO(skill);
	}

    /**
     * get a skill
     * @param key skill key
     * @return skill
     */
	public SkillDTO get(final String key) {

		final Skill skill = this.skillDAO.get(key);

		return this.skillMapper.toDTO(skill);
	}

    /**
     * get all skills
     * @return set of all skills
     */
	public Set<SkillDTO> getAll() {

		final Set<Skill> skills = this.skillDAO.getAll();

		return this.skillMapper.toDTO(skills);
	}

    /**
     * get multipliers of all given keys
     * @param keys set of skill keys
     * @return multipliers as map [key = skill key, value = multiplier]
     */
	public Map<String, Double> getMultipliers(final Set<String> keys) {
		return this.skillDAO.getMultipliers(keys);
	}

}
