package com.routing.skillservice.test.mock;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;

import com.routing.skillservice.feature.skill.dao.SkillDAO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;

public class SkillDAOMock extends SkillDAO {

	@Override
	public Skill persist(Skill skill) {
		return skill;
	}

	@Override
	public Skill get(String key) {
		return new BooleanSkillBuilder().setKey(key).build();
	}

	@Override
	public <T extends Skill> T get(String key, Class<T> clazz) {
		if (clazz == BooleanSkill.class) {
			return (T) new BooleanSkillBuilder().setKey(key).build();
		} else if (clazz == IntegerSkill.class) {
			return (T) new IntegerSkillBuilder().setKey(key).build();
		} else if (clazz == EnumSkill.class) {
			return (T) new EnumSkillBuilder().setKey(key).build();
		} else {
			throw new NotImplementedException();
		}
	}

	@Override
	public Set<Skill> getAll() {
		return new LinkedHashSet<>(0);
	}

}
