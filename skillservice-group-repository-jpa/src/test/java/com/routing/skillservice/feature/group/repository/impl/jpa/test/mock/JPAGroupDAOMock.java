package com.routing.skillservice.feature.group.repository.impl.jpa.test.mock;

import java.util.LinkedHashSet;
import java.util.Set;

import com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupDAO;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;
import com.routing.skillservice.feature.group.repository.impl.jpa.test.builder.JPAGroupBuilder;

public class JPAGroupDAOMock extends JPAGroupDAO {

	private String existingId;

	private Set<JPAGroup> groups;

	public JPAGroupDAOMock() {
		super();
		this.groups = new LinkedHashSet<>(0);
	}

	public JPAGroupDAOMock(final String existingId) {
		super();
		this.existingId = existingId;
	}

	public JPAGroupDAOMock(final String existingId, final Set<JPAGroup> groups) {
		super();
		this.existingId = existingId;
		this.groups = groups;
	}

	@Override
	public JPAGroup persist(JPAGroup group) {
		return group;
	}

	@Override
	public JPAGroup get(String id) {
		return JPAGroupBuilder.defaultBuilder().setId(id).build();
	}

	@Override
	public boolean exists(final String id) {
		return this.existingId.equals(id);
	}

	@Override
	public Set<JPAGroup> getAll() {
		return new LinkedHashSet<>(0);
	}

	@Override
	public void remove(JPAGroup group) {
		return;
	}

	@Override
	public Set<JPAGroup> ofUser(String userId) {
		return this.groups;
	}

}
