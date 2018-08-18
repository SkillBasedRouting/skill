package com.routing.skillservice.feature.skill.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * <b>com.routing.skillservice.feature.skill.model.BooleanSkill</b>
 * <p>
 *   entity that represents a skill that can be either set (= true) or not (= false)
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_BooleanSkill")
@Inheritance(strategy = InheritanceType.JOINED)
public class BooleanSkill extends Skill {

}
