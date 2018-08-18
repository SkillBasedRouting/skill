package com.routing.skillservice.test.mock;

import javax.persistence.EntityManager;

import com.routing.skillservice.feature.skill.dao.SkillDAO;

public class SkillDAOProxy extends SkillDAO {

	public SkillDAOProxy(final EntityManager em) {
		super.em = em;
	}

	public EntityManager em() {
		return super.em;
	}

}
