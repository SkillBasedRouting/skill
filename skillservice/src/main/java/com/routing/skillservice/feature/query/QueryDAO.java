package com.routing.skillservice.feature.query;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.user.query.UserMatchQuery;

/**
 * <b>com.routing.skillservice.feature.query.QueryDAO</b>
 * <p>
 *   data access object for querying users by its shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class QueryDAO {

	@PersistenceContext
	protected EntityManager em;

	public Set<String> matchShapes(final Set<Shape> requirements) {

		final TypedQuery<String> query = new UserMatchQuery(requirements).query(this.em);

		return new HashSet<>(query.getResultList());
	}

}
