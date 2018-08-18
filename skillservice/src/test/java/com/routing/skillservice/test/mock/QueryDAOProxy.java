package com.routing.skillservice.test.mock;

import javax.persistence.EntityManager;

import com.routing.skillservice.feature.query.QueryDAO;

public class QueryDAOProxy extends QueryDAO {

	public QueryDAOProxy(final EntityManager em) {
		super.em = em;
	}
	
}
