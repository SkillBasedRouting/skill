package com.routing.skillservice.feature.group.repository.impl.jpa.test.builder;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;
import com.routing.skillservice.feature.group.repository.model.Group;

public class JPAGroupBuilder {

	public static JPAGroupBuilder defaultBuilder() {
		return new JPAGroupBuilder().setId(UUID.randomUUID().toString()).setName("group")
				.setMembers(new LinkedHashSet<>());
	}

	private JPAGroup group;

	public JPAGroupBuilder() {
		this(new JPAGroup());
	}

	public JPAGroupBuilder(final JPAGroup group) {
		super();
		this.group = group;
	}

	public JPAGroupBuilder setId(final String id) {
		this.group.setId(id);
		return this;
	}

	public JPAGroupBuilder setName(final String name) {
		this.group.setName(name);
		return this;
	}

	public JPAGroupBuilder setMembers(final Set<String> members) {
		this.group.setMembers(members);
		return this;
	}

	public JPAGroupBuilder addMember(final String user) {
		if (null == this.group.getMembers()) {
			this.group.setMembers(new LinkedHashSet<String>());
		}
		this.group.getMembers().add(user);
		return this;
	}

	public JPAGroup build() {
		final JPAGroup group = new JPAGroup();
		group.setId(this.group.getId());
		group.setName(this.group.getName());
		if (null != this.group.getMembers()) {
			group.setMembers(new LinkedHashSet<>());
			group.getMembers().addAll(this.group.getMembers());
		}
		return group;
	}

	public Group buildGroup() {
		final Group group = new Group();
		group.setId(this.group.getId());
		group.setName(this.group.getName());
		if (null != this.group.getMembers()) {
			group.setMembers(new LinkedHashSet<>());
			group.getMembers().addAll(this.group.getMembers());
		}
		return group;
	}

}
