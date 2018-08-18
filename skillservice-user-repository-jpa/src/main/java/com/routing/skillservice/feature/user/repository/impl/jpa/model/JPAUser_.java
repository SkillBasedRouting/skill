package com.routing.skillservice.feature.user.repository.impl.jpa.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * <b>com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser_</b>
 * <p>
 *   jpa meta model of {@link JPAUser}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@StaticMetamodel(JPAUser.class)
public class JPAUser_ {

	public static transient SingularAttribute<JPAUser, Integer> internId;
	public static transient SingularAttribute<JPAUser, String> id;
	public static transient SingularAttribute<JPAUser, String> name;

}
