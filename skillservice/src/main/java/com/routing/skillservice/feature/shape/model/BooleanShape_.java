package com.routing.skillservice.feature.shape.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.routing.skillservice.feature.skill.model.BooleanSkill;

/**
 * <b>com.routing.skillservice.feature.shape.model.BooleanShape_</b>
 * <p>
 *   JPA meta model for {@link BooleanShape}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(BooleanShape.class)
public class BooleanShape_ {

	public static transient SingularAttribute<BooleanShape, BooleanSkill> booleanSkill;
	public static transient SingularAttribute<BooleanShape, Boolean> booleanValue;

}
