package com.routing.skillservice.feature.shape.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * <b>com.routing.skillservice.feature.shape.model.Shape_</b>
 * <p>
 *   JPA meta model for {@link Shape}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(Shape.class)
public class Shape_ {

	public static transient SingularAttribute<Shape, Integer> internId;
	public static transient SingularAttribute<Shape, String> id;
	public static transient SingularAttribute<Shape, String> user;

}
