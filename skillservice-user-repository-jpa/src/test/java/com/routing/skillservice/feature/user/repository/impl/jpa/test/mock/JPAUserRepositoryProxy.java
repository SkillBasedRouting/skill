package com.routing.skillservice.feature.user.repository.impl.jpa.test.mock;

import com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserRepository;

public class JPAUserRepositoryProxy extends JPAUserRepository {

	public JPAUserRepositoryProxy(final String existingId) {
		super.userDAO = new JPAUserDAOMock(existingId);
	}

}
