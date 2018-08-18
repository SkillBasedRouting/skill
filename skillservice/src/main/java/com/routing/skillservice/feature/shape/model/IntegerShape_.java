package com.routing.skillservice.feature.shape.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.routing.skillservice.feature.skill.model.IntegerSkill;

/**
 * <b>com.routing.skillservice.feature.shape.model.IntegerShape_</b>
 * <p>
 *   JPA meta model for {@link IntegerShape}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(IntegerShape.class)
public class IntegerShape_ {

	public static transient SingularAttribute<IntegerShape, IntegerSkill> integerSkill;
	public static transient SingularAttribute<IntegerShape, Integer> integerValue;

}
