package com.routing.skillservice.feature.skill.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <b>com.routing.skillservice.feature.skill.model.IntegerSkill</b>
 * <p>
 *   entity that represents a skill that has a numeric shape
 *   the multiplier can be set to e.g double this skill as its more important
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_IntegerSkill")
public class IntegerSkill extends Skill {

	private Double multiplier;

	public Double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntegerSkill [multiplier=");
		builder.append(multiplier);
		builder.append("]");
		return builder.toString();
	}

}
