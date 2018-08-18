package com.routing.skillservice.feature.user.repository.impl.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Test;

import com.routing.skillservice.feature.user.repository.exception.UserRepositoryException;
import com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserRepository;
import com.routing.skillservice.feature.user.repository.impl.jpa.test.builder.UserBuilder;
import com.routing.skillservice.feature.user.repository.impl.jpa.test.mock.JPAUserRepositoryProxy;
import com.routing.skillservice.feature.user.repository.model.User;

public class JPAUserRepositoryTest {

	private String existingId = UUID.randomUUID().toString();
	private JPAUserRepository userControl = new JPAUserRepositoryProxy(this.existingId);

	public UserBuilder defaulBuilder() {
		return UserBuilder.defaultBuilder();
	}

	// create

	@Test
	public void should_Throw_UserIdAlreadyExits_When_IdAlreadyExists_On_Create() {

		final UserBuilder builder = this.defaulBuilder().setId(this.existingId);
		final User user = builder.build();

		try {
			this.userControl.createUser(user);
			fail("can create user with existing id");
		} catch (UserRepositoryException e) {
			assertEquals(UserRepositoryException.ID_CONFLICT, e);
		}

	}

	@Test
	public void should_Throw_UserRequiredException_When_UserIsNull_On_Create() {

		try {
			this.userControl.createUser(null);
			fail("can create user with null");
		} catch (UserRepositoryException e) {
			assertEquals(UserRepositoryException.REQUIRED, e);
		}

	}

	@Test
	public void should_Throw_UserIdRequiredException_When_UserIdIsNull_On_Create() {

		final UserBuilder builder = this.defaulBuilder();
		builder.setId(null);
		final User user = builder.build();

		try {
			this.userControl.createUser(user);
			fail("can create user with null id");
		} catch (UserRepositoryException e) {
			assertEquals(UserRepositoryException.ID_REQUIRED, e);
		}

	}

	// update

	@Test
	public void should_UpdateUser_On_Update() {

		final UserBuilder builder = this.defaulBuilder();
		User user = builder.build();

		final String newId = UUID.randomUUID().toString();
		builder.setId(newId);

		user = this.userControl.updateUser(user.getId(), builder.build());

		assertEquals(newId, user.getId());
	}

	@Test
	public void should_Throw_IdRequiredException_When_IdIsNull_On_Update() {

		final UserBuilder builder = this.defaulBuilder();
		User user = builder.build();

		builder.setId(null);

		try {
			this.userControl.updateUser(user.getId(), builder.build());
			fail("can update user with null id");
		} catch (UserRepositoryException e) {
			assertEquals(UserRepositoryException.ID_REQUIRED, e);
		}

	}

	// patch

	@Test
	public void should_PatchUser_On_Update() {

		final UserBuilder builder = this.defaulBuilder();
		User user = builder.build();

		final String newId = UUID.randomUUID().toString();
		builder.setId(newId);

		user = this.userControl.patchUser(user.getId(), builder.build());

		assertEquals(newId, user.getId());
	}

	@Test
	public void should_KeepId_When_IdIsNull_On_Patch() {

		final UserBuilder builder = this.defaulBuilder();
		User user = builder.build();
		final String id = user.getId();

		builder.setId(null);

		user = this.userControl.patchUser(user.getId(), builder.build());

		assertEquals(id, user.getId());

	}

}
