package com.routing.skillservice.test.builder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.feature.group.repository.model.Group;

public class GroupBuilder {

	public static GroupBuilder defaultBuilder() {
		return new GroupBuilder().setId(UUID.randomUUID().toString()).setName("group")
				.setMembers(new LinkedHashSet<>());
	}

	private Group group;

	public GroupBuilder() {
		this(new Group());
	}

	public GroupBuilder(final Group group) {
		super();
		this.group = group;
	}

	public GroupBuilder setId(final String id) {
		this.group.setId(id);
		return this;
	}

	public GroupBuilder setName(final String name) {
		this.group.setName(name);
		return this;
	}

	public GroupBuilder setMembers(final Set<String> members) {
		this.group.setMembers(members);
		return this;
	}

	public GroupBuilder addMember(final String user) {
		if (null == this.group.getMembers()) {
			this.group.setMembers(new LinkedHashSet<String>());
		}
		this.group.getMembers().add(user);
		return this;
	}

	public Group build() {
		final Group group = new Group();
		group.setId(this.group.getId());
		group.setName(this.group.getName());
		if (null != this.group.getMembers()) {
			group.setMembers(new LinkedHashSet<>());
			group.getMembers().addAll(this.group.getMembers());
		}
		return group;
	}

	public RequestGroupDTO buildRequestDTO() {

		final RequestGroupDTO groupDTO = new RequestGroupDTO();
		groupDTO.setId(this.group.getId());
		groupDTO.setName(this.group.getName());
		if (null != this.group.getMembers()) {
			groupDTO.setMembers(new LinkedHashSet<>());
			groupDTO.getMembers().addAll(this.group.getMembers());
		}

		return groupDTO;
	}

	public EditGroupDTO buildEditDTO() {

		final EditGroupDTO groupDTO = new EditGroupDTO();
		groupDTO.setId(this.group.getId());
		groupDTO.setName(this.group.getName());
		if (null != this.group.getMembers()) {
			groupDTO.setMembers(new LinkedHashSet<>());
			groupDTO.getMembers().addAll(this.group.getMembers());
		}

		return groupDTO;
	}

}
