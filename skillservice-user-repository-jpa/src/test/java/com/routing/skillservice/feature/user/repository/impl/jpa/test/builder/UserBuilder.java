package com.routing.skillservice.feature.user.repository.impl.jpa.test.builder;

import java.util.UUID;

import com.routing.skillservice.feature.user.repository.model.User;

public class UserBuilder {

	public static UserBuilder defaultBuilder() {
		return new UserBuilder().setId(UUID.randomUUID().toString());
	}

	private User user;

	public UserBuilder() {
		this(new User());
	}

	public UserBuilder(final User user) {
		super();
		this.user = user;
	}

	public UserBuilder setId(final String userId) {
		this.user.setId(userId);
		return this;
	}

	public UserBuilder setName(final String name) {
		this.user.setName(name);
		return this;
	}

	public User build() {
		final User user = new User();
		user.setId(this.user.getId());
		user.setName(this.user.getName());
		return user;
	}

}
