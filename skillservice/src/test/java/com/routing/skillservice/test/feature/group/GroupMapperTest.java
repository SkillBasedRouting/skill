package com.routing.skillservice.test.feature.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.Test;

import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.feature.group.boundary.GroupMapper;
import com.routing.skillservice.feature.group.repository.model.Group;
import com.routing.skillservice.test.builder.GroupBuilder;

public class GroupMapperTest {

	private GroupMapper groupMapper = new GroupMapper();

	public GroupBuilder defaulBuilder() {
		return GroupBuilder.defaultBuilder();
	}

	// toDTO

	@Test
	public void should_MapId_On_toDTO() {

		final GroupBuilder builder = this.defaulBuilder();
		final Group group = builder.build();

		final GroupDTO dto = this.groupMapper.toDTO(group);

		assertEquals(group.getId(), dto.getId());
	}

	@Test
	public void should_MapName_On_toDTO() {

		final GroupBuilder builder = this.defaulBuilder();
		final Group group = builder.build();

		final GroupDTO dto = this.groupMapper.toDTO(group);

		assertEquals(group.getName(), dto.getName());
	}

	@Test
	public void should_MapMembers_On_toDTO() {

		final GroupBuilder builder = this.defaulBuilder();
		final String user = UUID.randomUUID().toString();
		builder.addMember(user);
		final Group group = builder.build();

		final GroupDTO dto = this.groupMapper.toDTO(group);

		assertEquals(1, dto.getMembers().size());
		assertEquals(user, dto.getMembers().iterator().next());
	}

	@Test
	public void should_MapGroups_On_toDTO() {

		final Set<Group> groups = new LinkedHashSet<>();
		groups.add(this.defaulBuilder().build());
		groups.add(this.defaulBuilder().build());

		final Set<GroupDTO> dtos = this.groupMapper.toDTO(groups);

		assertEquals(groups.size(), dtos.size());
	}

	// toModel

	@Test
	public void should_MapId_On_RequestToModel() {

		final GroupBuilder builder = this.defaulBuilder();
		final RequestGroupDTO dto = builder.buildRequestDTO();

		final Group group = this.groupMapper.toModel(dto);

		assertEquals(dto.getId(), group.getId());
	}

	@Test
	public void should_MapName_On_RequestToModel() {

		final GroupBuilder builder = this.defaulBuilder();
		final RequestGroupDTO dto = builder.buildRequestDTO();

		final Group group = this.groupMapper.toModel(dto);

		assertEquals(dto.getName(), group.getName());
	}

	@Test
	public void should_MapMembers_On_RequestToModel() {

		final GroupBuilder builder = this.defaulBuilder();
		final String user = UUID.randomUUID().toString();
		builder.addMember(user);
		final RequestGroupDTO dto = builder.buildRequestDTO();

		final Group group = this.groupMapper.toModel(dto);

		final Set<String> users = builder.build().getMembers();
		assertEquals(users.size(), group.getMembers().size());
		users.forEach(u -> {
			assertTrue(group.getMembers().stream().collect(Collectors.toSet()).contains(u));
		});
	}

	@Test
	public void should_MapName_On_EditToModel() {

		final GroupBuilder builder = this.defaulBuilder();
		final EditGroupDTO dto = builder.buildEditDTO();

		final Group group = this.groupMapper.toModel(dto);

		assertEquals(dto.getName(), group.getName());
	}

	@Test
	public void should_MapMembers_On_EditToModel() {

		final GroupBuilder builder = this.defaulBuilder();
		final String user = UUID.randomUUID().toString();
		builder.addMember(user);
		final EditGroupDTO dto = builder.buildEditDTO();

		final Group group = this.groupMapper.toModel(dto);

		final Set<String> users = builder.build().getMembers();
		assertEquals(users.size(), group.getMembers().size());
		users.forEach(u -> {
			assertTrue(group.getMembers().stream().collect(Collectors.toSet()).contains(u));
		});
	}

}
