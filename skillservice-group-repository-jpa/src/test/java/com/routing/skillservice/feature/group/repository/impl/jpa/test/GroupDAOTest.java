package com.routing.skillservice.feature.group.repository.impl.jpa.test;

import static org.junit.Assert.assertEquals;
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

import com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException;
import com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupDAO;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;
import com.routing.skillservice.feature.group.repository.impl.jpa.test.builder.JPAGroupBuilder;
import com.routing.skillservice.feature.group.repository.impl.jpa.test.mock.JPAGroupDAOProxy;

public class GroupDAOTest {

	private static JPAGroupDAO groupDAO;

	@BeforeClass
	public static void init() {
		final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("ExampleDS");
		final EntityManager em = emFactory.createEntityManager();
		GroupDAOTest.groupDAO = new JPAGroupDAOProxy(em);
	}

	@Before
	public void startTransaction() {
		((JPAGroupDAOProxy) GroupDAOTest.groupDAO).em().getTransaction().begin();
	}

	@After
	public void endTransaction() {
		((JPAGroupDAOProxy) GroupDAOTest.groupDAO).em().getTransaction().commit();
	}

	private JPAGroupBuilder getDefaultBuilder() {
		return JPAGroupBuilder.defaultBuilder();
	}

	@Test
	public void should_CreateGroup_On_Create() {

		JPAGroup group = this.getDefaultBuilder().build();
		group = groupDAO.persist(group);

		final JPAGroup createdGroup = groupDAO.get(group.getId());

		assertEquals(group, createdGroup);
	}

	@Test
	public void should_ReturnAllGroups_On_GetAll() {

		final Set<JPAGroup> groups = new LinkedHashSet<>(3);
		groups.add(groupDAO.persist(this.getDefaultBuilder().build()));
		groups.add(groupDAO.persist(this.getDefaultBuilder().build()));
		groups.add(groupDAO.persist(this.getDefaultBuilder().build()));

		final Set<JPAGroup> getAll = groupDAO.getAll();

		groups.forEach((group) -> {
			assertTrue(getAll.contains(group));
		});
	}

	@Test
	public void should_Throw_GroupNotFoundException_When_GroupDoesNotExist_On_Create() {

		try {
			groupDAO.get(UUID.randomUUID().toString());
			fail("can get unexisting group");
		} catch (GroupNotFoundException e) {

		}

	}

	@Test
	public void should_RemoveGroup_On_Remove() {

		final JPAGroup group = groupDAO.persist(this.getDefaultBuilder().build());

		groupDAO.remove(group);

		try {
			groupDAO.get(group.getId());
			fail("can get deleted group");
		} catch (GroupNotFoundException e) {

		}

	}

	@Test
	public void should_CreateGroupWithMember_On_Create() {

		final JPAGroupBuilder builder = this.getDefaultBuilder();
		final String user = UUID.randomUUID().toString();
		builder.addMember(user);

		final JPAGroup group = groupDAO.persist(builder.build());

		assertEquals(user, group.getMembers().iterator().next());
	}

	@Test
	public void should_ReturnGroups_ForUser_On_OfUser() {

		final JPAGroupBuilder builder = this.getDefaultBuilder();
		final String user = UUID.randomUUID().toString();
		builder.addMember(user);

		final JPAGroup group = groupDAO.persist(builder.build());

		final Set<JPAGroup> groups = groupDAO.ofUser(user);

		assertEquals(1, groups.size());
		assertEquals(group, groups.iterator().next());
	}

}
