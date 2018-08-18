package com.routing.skillservice.feature.user.repository.impl.jpa.test.mock;

import javax.persistence.EntityManager;

import com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserDAO;

public class JPAUserDAOProxy extends JPAUserDAO {

	public JPAUserDAOProxy(final EntityManager em) {
		super.em = em;
	}

	public EntityManager em() {
		return super.em;
	}

}
