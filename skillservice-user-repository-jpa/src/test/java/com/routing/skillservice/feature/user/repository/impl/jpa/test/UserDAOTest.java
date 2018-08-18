package com.routing.skillservice.feature.user.repository.impl.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.routing.skillservice.feature.user.repository.exception.UserNotFoundException;
import com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser;
import com.routing.skillservice.feature.user.repository.impl.jpa.test.builder.JPAUserBuilder;
import com.routing.skillservice.feature.user.repository.impl.jpa.test.mock.JPAUserDAOProxy;

public class UserDAOTest {

	private static JPAUserDAOProxy userDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		final EntityManager em = emFactory.createEntityManager();
		UserDAOTest.userDAO = new JPAUserDAOProxy(em);
	}

	@Before
	public void startTransaction() {
		UserDAOTest.userDAO.em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		UserDAOTest.userDAO.em().getTransaction().commit();
	}

	private JPAUserBuilder getDefaultBuilder() {
		return JPAUserBuilder.defaultBuilder();
	}

	@Test
	public void should_CreateUser_On_Create() {

		JPAUser user = this.getDefaultBuilder().build();
		user = userDAO.persist(user);

		final JPAUser createdUser = userDAO.get(user.getId());

		assertEquals(user, createdUser);
	}

	@Test
	public void should_Throw_UserNotFoundException_When_UserDoesNotExist_On_Get() {

		try {
			userDAO.get(UUID.randomUUID().toString());
			fail("can get non existing user");
		} catch (UserNotFoundException e) {

		}

	}

	@Test
	public void should_GetAllUsers_On_GetAll() {

		final Set<JPAUser> users = new LinkedHashSet<JPAUser>(3);
		users.add(userDAO.persist(this.getDefaultBuilder().build()));
		users.add(userDAO.persist(this.getDefaultBuilder().build()));
		users.add(userDAO.persist(this.getDefaultBuilder().build()));

		final Set<JPAUser> getAll = userDAO.getAll();

		users.forEach((user) -> {
			assertTrue(getAll.contains(user));
		});
	}

	@Test
	public void should_RemoveUser_On_Remove() {

		final JPAUser user = userDAO.persist(this.getDefaultBuilder().build());

		userDAO.remove(user);

		try {
			userDAO.get(user.getId());
			fail("can get removed user");
		} catch (UserNotFoundException e) {

		}

	}

	@Test
	public void should_ReturnTrue_WhenUserExists_On_Exists() {

		final JPAUser user = userDAO.persist(this.getDefaultBuilder().build());

		assertTrue(userDAO.exists(user.getId()));
	}

	@Test
	public void should_ReturnFalse_WhenUserDoesNotExist_On_Exists() {
		assertFalse(userDAO.exists(UUID.randomUUID().toString()));
	}

}
