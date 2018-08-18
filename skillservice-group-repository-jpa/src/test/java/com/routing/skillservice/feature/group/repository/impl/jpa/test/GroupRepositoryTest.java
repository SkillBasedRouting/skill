package com.routing.skillservice.feature.group.repository.impl.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import com.routing.skillservice.feature.group.repository.GroupRepository;
import com.routing.skillservice.feature.group.repository.exception.GroupRepositoryException;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;
import com.routing.skillservice.feature.group.repository.impl.jpa.test.builder.JPAGroupBuilder;
import com.routing.skillservice.feature.group.repository.impl.jpa.test.mock.JPAGroupRepositoryProxy;
import com.routing.skillservice.feature.group.repository.model.Group;

public class GroupRepositoryTest {

	private String existingId = "existingKeyThatContainsAnyLetters";
	private Set<JPAGroup> groups = new HashSet<>();
	private GroupRepository groupRepository = new JPAGroupRepositoryProxy(existingId, groups);

	public JPAGroupBuilder defaulBuilder() {
		return JPAGroupBuilder.defaultBuilder();
	}

	// beforePersist

	@Test
	public void should_CheckExistingId_On_Create() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setId(UUID.randomUUID().toString());
		final Group group = builder.buildGroup();

		this.groupRepository.createGroup(group);

		assertNotNull(group.getId());
	}

	@Test
	public void should_Throw_KeyConflictException_When_KeyAlreadyExists_On_BeforePersist() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setId(this.existingId);
		final Group group = builder.buildGroup();

		try {
			this.groupRepository.createGroup(group);
			fail("can create group with existing key");
		} catch (GroupRepositoryException e) {
			assertEquals(e, GroupRepositoryException.ID_CONFLICT);
		}

	}

	@Test
	public void should_Throw_GroupRequiredException_When_GroupIsNull_On_BeforePersist() {

		try {
			this.groupRepository.createGroup(null);
			fail();
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.REQUIRED, e);
		}

	}

	// update

	@Test
	public void should_UpdateName_On_Update() {

		final String newName = "newName";
		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setName(newName);
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.updateGroup(newValues.getId(), newValues);

		assertEquals(newName, group.getName());
	}

	@Test
	public void should_UpdateMembers_On_Update() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.addMember(UUID.randomUUID().toString());
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.updateGroup(newValues.getId(), newValues);

		assertEquals(builder.build().getMembers(), group.getMembers());
	}

	@Test
	public void should_UpdateNullName_On_Update() {

		final String newName = null;
		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setName(newName);
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.updateGroup(newValues.getId(), newValues);

		assertEquals(newName, group.getName());
	}

	@Test
	public void should_Throw_GroupMembersRequired_When_MembersAreNull_On_Update() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setMembers(null);
		final Group newValues = builder.buildGroup();

		try {
			this.groupRepository.updateGroup(newValues.getId(), newValues);
			fail("can update group with null members");
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.MEMBERS_REQUIRED, e);
		}

	}

	@Test
	public void should_Throw_GroupRequiredException_When_GroupIsNull_On_Update() {

		try {
			this.groupRepository.updateGroup(null, this.defaulBuilder().buildGroup());
			fail();
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.ID_REQUIRED, e);
		}

	}

	@Test
	public void should_Throw_GroupRequiredException_When_NewValuesIsNull_On_Update() {

		try {
			this.groupRepository.updateGroup(this.defaulBuilder().build().getId(), null);
			fail();
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.REQUIRED, e);
		}

	}

	// patch

	@Test
	public void should_UpdateName_On_Patch() {

		final String newName = "newName";
		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setName(newName);
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.patchGroup(newValues.getId(), newValues);

		assertEquals(newName, group.getName());
	}

	@Test
	public void should_UpdateMembers_On_Patch() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.addMember(UUID.randomUUID().toString());
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.patchGroup(newValues.getId(), newValues);

		assertEquals(builder.build().getMembers(), group.getMembers());
	}

	@Test
	public void should_KeepName_When_NameIsNull_On_Patch() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setName(null);
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.patchGroup(newValues.getId(), newValues);

		assertNotNull(group.getName());
	}

	@Test
	public void should_KeepMembers_When_MembersAreNull_On_Patch() {

		final JPAGroupBuilder builder = this.defaulBuilder();
		builder.setMembers(null);
		final Group newValues = builder.buildGroup();

		final Group group = this.groupRepository.patchGroup(newValues.getId(), newValues);

		assertNotNull(group.getMembers());
	}

	@Test
	public void should_Throw_GroupRequiredException_When_GroupIsNull_On_Patch() {

		try {
			this.groupRepository.patchGroup(null, this.defaulBuilder().buildGroup());
			fail();
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.ID_REQUIRED, e);
		}

	}

	@Test
	public void should_Throw_GroupRequiredException_When_NewValuesIsNull_On_Patch() {

		try {
			this.groupRepository.patchGroup(this.defaulBuilder().build().getId(), null);
			fail();
		} catch (GroupRepositoryException e) {
			assertEquals(GroupRepositoryException.REQUIRED, e);
		}

	}

}
