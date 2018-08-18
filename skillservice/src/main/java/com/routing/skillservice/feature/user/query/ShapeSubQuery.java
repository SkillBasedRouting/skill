package com.routing.skillservice.feature.user.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.shape.model.Shape_;

/**
 * <b>com.routing.skillservice.feature.user.query.ShapeSubQuery</b>
 * <p>
 *   subquery for finding users by its shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ShapeSubQuery {

	private Subquery<String> subQuery;

	public ShapeSubQuery(final CriteriaBuilder builder, final CriteriaQuery<String> query, final Shape shape) {
		this.subQuery = query.subquery(String.class);
		final Root<Shape> subRoot = this.subQuery.from(Shape.class);

		Predicate shapePre = shape.assertion(builder, subRoot);

		this.subQuery.select(subRoot.get(Shape_.user)).where(shapePre);
	}

	public Subquery<String> subQuery() {
		return this.subQuery;
	}

}
