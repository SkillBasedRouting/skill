package com.routing.skillservice.feature.group.repository.impl.jpa.test.mock;

import javax.persistence.EntityManager;

import com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupDAO;

public class JPAGroupDAOProxy extends JPAGroupDAO {

	public JPAGroupDAOProxy(final EntityManager em) {
		super.em = em;
	}
	
	public EntityManager em() {
		return super.em;
	}

}
