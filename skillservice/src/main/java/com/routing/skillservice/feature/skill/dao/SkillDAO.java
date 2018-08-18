package com.routing.skillservice.feature.skill.dao;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.routing.skillservice.feature.skill.exception.SkillNotFoundException;
import com.routing.skillservice.feature.skill.model.MultiplierResult;
import com.routing.skillservice.feature.skill.model.Skill;

/**
 * <b>com.routing.skillservice.feature.skill.dao.SkillDAO</b>
 * <p>
 *   data access object for all skill database actions and queries
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class SkillDAO {

	@PersistenceContext
	protected EntityManager em;

    /**
     * persist a skill
     * @param skill skill to persist
     * @return persisted skill
     */
	public Skill persist(final Skill skill) {

		this.em.persist(skill);

		return skill;
	}

    /**
     * get a skill
     * @param key skill key
     * @return sill
     * @throws SkillNotFoundException if skill does not exist
     */
	public Skill get(final String key) {

		final TypedQuery<Skill> query = this.em.createQuery("Select u from Skill u where u.key = :parKey", Skill.class);
		query.setParameter("parKey", key);

		try {
			final Skill skill = query.getSingleResult();
			if (null == skill) {
				throw new NoResultException();
			}
			return skill;
		} catch (NoResultException e) {
			throw new SkillNotFoundException(key);
		}

	}

    /**
     * get a skill 'typesafe'
     * @param key skill key
     * @param clazz skill class
     * @param <T> derived class of {@link Skill}
     * @return skill
     * @throws SkillNotFoundException if skill does not exist or has different type
     */
	public <T extends Skill> T get(final String key, final Class<T> clazz) {

		final TypedQuery<T> query = this.em.createQuery("Select s from " + clazz.getName() + " s where s.key = :parKey",
				clazz);
		query.setParameter("parKey", key);

		try {
			final T skill = query.getSingleResult();
			if (null == skill) {
				throw new NoResultException();
			}
			return skill;
		} catch (NoResultException e) {
			throw new SkillNotFoundException(key);
		}

	}

    /**
     * get all skills
     * @return set of all skills
     */
	public Set<Skill> getAll() {

		final TypedQuery<Skill> query = this.em.createQuery("Select s from Skill s", Skill.class);

		return new LinkedHashSet<>(query.getResultList());
	}

    /**
     * query skill multipliers
     * @param keys set of skill keys to query
     * @return map of mulitpliers [key = skill key, value = multiplier]
     */
	public Map<String, Double> getMultipliers(final Set<String> keys) {

		final TypedQuery<MultiplierResult> query = this.em.createQuery(
				"Select new com.routing.skillservice.feature.skill.model.MultiplierResult(s.key, s.multiplier) from IntegerSkill s where s.key in :parKeys",
				MultiplierResult.class);
		query.setParameter("parKeys", keys);

		return query.getResultList().stream().filter(result -> null != result.getMultiplier())
				.collect(Collectors.toMap(MultiplierResult::getSkillKey, MultiplierResult::getMultiplier));
	}

}
