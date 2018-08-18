package com.routing.skillservice.feature.shape.dao;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.shape.exception.ShapeNotFoundException;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;

/**
 * <b>com.routing.skillservice.feature.shape.dao.ShapeDAO</b>
 * <p>
 *   data access object for shapes that handles database access and querying
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class ShapeDAO {

	@PersistenceContext
	protected EntityManager em;

    /**
     * get all shapes of user filtered by given requirements
     * @param user the user
     * @param requirements requirements to filter shapes
     * @return all shapes of user that are mentioned in requirement parameter
     */
	public Set<Shape> getByUser(final String user, final Set<Shape> requirements) {

		return this.getByUser(user).stream().filter(shape -> {
			return this.containShape(requirements, shape.getSkill().getKey());
		}).collect(Collectors.toSet());
	}

    /**
     * get all shapes of user
     * @param user the user
     * @return all shapes of user
     */
	public Set<Shape> getByUser(final String user) {

		final TypedQuery<Shape> query = this.em.createQuery("Select s from Shape s where s.user = :parUser",
				Shape.class);
		query.setParameter("parUser", user);

		return new LinkedHashSet<>(query.getResultList());
	}

	private boolean containShape(final Set<Shape> requirements, final String skillKey) {
		return requirements.stream().map(req -> req.getSkill().getKey()).filter(key -> key.equals(skillKey)).findAny()
				.isPresent();
	}

    /**
     * persist a shape
     * @param shape shape to persist
     * @return persisted shape
     */
	public Shape persist(final Shape shape) {

		this.em.persist(shape);

		return shape;
	}

    /**
     * get a shape by id
     * @param id shape id
     * @return shape
     */
	public Shape get(final String id) {

		final TypedQuery<Shape> query = this.em.createQuery("Select s from Shape s where s.id = :parId", Shape.class);
		query.setParameter("parId", id);

		try {
			final Shape shape = query.getSingleResult();
			if (null == shape) {
				throw new NoResultException();
			}
			return shape;
		} catch (NoResultException e) {
			throw new ShapeNotFoundException(id);
		}

	}

    /**
     * get a shape by an {@link com.routing.skillservice.feature.user.repository.model.User} and a {@link Skill}
     * @param user the user
     * @param skill the skill
     * @return shape of user that matches given skill
     * @throws ShapeException if given skill type is unknown
     * @throws ShapeNotFoundException if user has no shape in the given skill
     */
	public Shape get(final String user, final Skill skill) {

		if (skill instanceof IntegerSkill) {
			return this.get(user, (IntegerSkill) skill);
		} else if (skill instanceof BooleanSkill) {
			return this.get(user, (BooleanSkill) skill);
		} else if (skill instanceof EnumSkill) {
			return this.get(user, (EnumSkill) skill);
		} else {
			throw ShapeException.UNKNOWN_TYPE;
		}

	}

    /**
     * get a shape by an {@link com.routing.skillservice.feature.user.repository.model.User} and a {@link BooleanSkill}
     * @param user the user
     * @param booleanSkill the skill
     * @return shape of user that matches given skill
     * @throws ShapeException if given skill type is unknown
     * @throws ShapeNotFoundException if user has no shape in the given skill
     */
	public Shape get(final String user, final BooleanSkill booleanSkill) {

		final TypedQuery<Shape> query = this.em.createQuery(
				"Select s from Shape s where s.user = :parUser and treat(s as BooleanShape).booleanSkill = :parSkill",
				Shape.class);
		query.setParameter("parUser", user);
		query.setParameter("parSkill", booleanSkill);

		try {
			final Shape shape = query.getSingleResult();
			if (null == shape) {
				throw new NoResultException();
			}
			return shape;
		} catch (NoResultException e) {
			throw new ShapeNotFoundException();
		}

	}

    /**
     * get a shape by an {@link com.routing.skillservice.feature.user.repository.model.User} and a {@link IntegerSkill}
     * @param user the user
     * @param integerSkill the skill
     * @return shape of user that matches given skill
     * @throws ShapeException if given skill type is unknown
     * @throws ShapeNotFoundException if user has no shape in the given skill
     */
	public Shape get(final String user, final IntegerSkill integerSkill) {

		final TypedQuery<Shape> query = this.em.createQuery(
				"Select s from Shape s where s.user = :parUser and treat(s as IntegerShape).integerSkill = :parSkill",
				Shape.class);
		query.setParameter("parUser", user);
		query.setParameter("parSkill", integerSkill);

		try {
			final Shape shape = query.getSingleResult();
			if (null == shape) {
				throw new NoResultException();
			}
			return shape;
		} catch (NoResultException e) {
			throw new ShapeNotFoundException();
		}

	}

    /**
     * get a shape by an {@link com.routing.skillservice.feature.user.repository.model.User} and a {@link EnumSkill}
     * @param user the user
     * @param enumSkill the skill
     * @return shape of user that matches given skill
     * @throws ShapeException if given skill type is unknown
     * @throws ShapeNotFoundException if user has no shape in the given skill
     */
	public Shape get(final String user, final EnumSkill enumSkill) {

		final TypedQuery<Shape> query = this.em.createQuery(
				"Select s from Shape s where s.user = :parUser and treat(s as EnumShape).enumSkill = :parSkill",
				Shape.class);
		query.setParameter("parUser", user);
		query.setParameter("parSkill", enumSkill);

		try {
			final Shape shape = query.getSingleResult();
			if (null == shape) {
				throw new NoResultException();
			}
			return shape;
		} catch (NoResultException e) {
			throw new ShapeNotFoundException();
		}

	}

    /**
     * check if given user has shape in skill
     * @param user user id
     * @param skill skill
     * @return true if user has shape in skill otherwise false
     */
	public boolean exists(final String user, final Skill skill) {

		try {
			this.get(user, skill);
			return true;
		} catch (ShapeNotFoundException e) {
			return false;
		}
	}

}
