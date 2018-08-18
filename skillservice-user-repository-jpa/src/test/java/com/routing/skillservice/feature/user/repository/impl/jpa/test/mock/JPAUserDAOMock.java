package com.routing.skillservice.feature.user.repository.impl.jpa.test.mock;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserDAO;
import com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser;
import com.routing.skillservice.feature.user.repository.impl.jpa.test.builder.JPAUserBuilder;

public class JPAUserDAOMock extends JPAUserDAO {

	private String exitingId;

	public JPAUserDAOMock(final String existingId) {
		this.exitingId = existingId;
	}

	public JPAUserDAOMock() {
		this.exitingId = UUID.randomUUID().toString();
	}

	@Override
	public JPAUser persist(JPAUser user) {
		return user;
	}

	@Override
	public JPAUser get(String id) {
		return new JPAUserBuilder().setId(id).build();
	}

	@Override
	public boolean exists(String id) {
		return id.equals(this.exitingId);
	}

	@Override
	public Set<JPAUser> getAll() {
		return new LinkedHashSet<>(0);
	}

	@Override
	public void remove(JPAUser user) {
		return;
	}

}
