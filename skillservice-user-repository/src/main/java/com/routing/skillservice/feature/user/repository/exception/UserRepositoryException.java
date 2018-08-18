package com.routing.skillservice.feature.user.repository.exception;

import javax.ejb.ApplicationException;

import com.routing.skillservice.exception.SkillServiceException;

/**
 * <b>com.routing.skillservice.feature.user.repository.exception.UserRepositoryException</b>
 * <p>
 *   base exception for all {@link com.routing.skillservice.feature.user.repository.model.User} exceptions
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationException(inherited = true, rollback = true)
public class UserRepositoryException extends SkillServiceException {

	private static final long serialVersionUID = 1L;

	public static final UserRepositoryException REQUIRED = new UserRepositoryException(0, "user is required");
	public static final UserRepositoryException ID_REQUIRED = new UserRepositoryException(0, "user id is required");
	public static final UserRepositoryException ID_CONFLICT = new UserRepositoryException(0,
			"user id is already taken");

	public UserRepositoryException(Integer reasonCode, String message) {
		super(reasonCode, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRepositoryException [super=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
