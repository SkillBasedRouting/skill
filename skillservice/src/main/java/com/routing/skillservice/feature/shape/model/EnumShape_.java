package com.routing.skillservice.feature.shape.model;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.routing.skillservice.feature.skill.model.EnumSkill;

/**
 * <b>com.routing.skillservice.feature.shape.model.EnumShape_</b>
 * <p>
 *   JPA meta model for {@link EnumShape}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(EnumShape.class)
public class EnumShape_ {

	public static transient SingularAttribute<EnumShape, EnumSkill> enumSkill;
	public static transient SetAttribute<EnumShape, String> enumValue;

}
