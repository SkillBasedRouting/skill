package com.routing.skillservice.feature.group.repository.impl.jpa.test.mock;

import java.util.Set;

import com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupRepository;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;

public class JPAGroupRepositoryProxy extends JPAGroupRepository {

	public JPAGroupRepositoryProxy(final String existingId, final Set<JPAGroup> groups) {
		super.jpaGroupDAO = new JPAGroupDAOMock(existingId, groups);
	}

}
