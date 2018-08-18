package com.routing.skillservice.feature.user.query;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.shape.model.Shape_;

/**
 * <b>com.routing.skillservice.feature.user.query.UserMatchQuery</b>
 * <p>
 *   Query for finding users by its shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserMatchQuery {

	private Set<Shape> requirements;

	public UserMatchQuery(final Set<Shape> requirements) {
		this.requirements = requirements;
	}

	public TypedQuery<String> query(final EntityManager em) {

		final CriteriaBuilder builder = em.getCriteriaBuilder();
		final CriteriaQuery<String> cQuery = builder.createQuery(String.class);
		final Root<Shape> root = cQuery.from(Shape.class);

		final Set<Predicate> shapePredicates = requirements.stream()
				.map(shape -> new ShapeSubQuery(builder, cQuery, shape).subQuery())
				.map(subQuery -> root.get(Shape_.user).in(subQuery))
				.collect(Collectors.toSet());

		cQuery.where(builder.and(shapePredicates.toArray(new Predicate[shapePredicates.size()])));

		cQuery.select(root.get(Shape_.user));

		return em.createQuery(cQuery);
	}

}
