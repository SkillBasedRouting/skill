package com.routing.skillservice.feature.user.repository.impl.jpa.test.builder;

import java.util.UUID;

import com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser;

public class JPAUserBuilder {

	public static JPAUserBuilder defaultBuilder() {
		return new JPAUserBuilder().setId(UUID.randomUUID().toString());
	}

	private JPAUser user;

	public JPAUserBuilder() {
		this(new JPAUser());
	}

	public JPAUserBuilder(final JPAUser user) {
		super();
		this.user = user;
	}

	public JPAUserBuilder setId(final String userId) {
		this.user.setId(userId);
		return this;
	}

	public JPAUserBuilder setName(final String name) {
		this.user.setName(name);
		return this;
	}

	public JPAUser build() {
		final JPAUser user = new JPAUser();
		user.setInternId(this.user.getInternId());
		user.setId(this.user.getId());
		user.setName(this.user.getName());
		return user;
	}

}
