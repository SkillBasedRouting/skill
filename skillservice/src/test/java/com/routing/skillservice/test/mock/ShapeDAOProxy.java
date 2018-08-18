package com.routing.skillservice.test.mock;

import javax.persistence.EntityManager;

import com.routing.skillservice.feature.shape.dao.ShapeDAO;

public class ShapeDAOProxy extends ShapeDAO {

	public ShapeDAOProxy(final EntityManager em) {
		this.em = em;
	}

	public EntityManager em() {
		return this.em;
	}

}
