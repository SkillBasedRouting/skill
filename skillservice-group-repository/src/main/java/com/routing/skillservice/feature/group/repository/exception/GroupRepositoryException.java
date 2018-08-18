package com.routing.skillservice.feature.group.repository.exception;

import javax.ejb.ApplicationException;

import com.routing.skillservice.exception.SkillServiceException;

/**
 * <b>com.routing.skillservice.feature.group.repository.exception.GroupRepositoryException</b>
 * <p>
 *   exception for all group specific errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationException(rollback = true)
public class GroupRepositoryException extends SkillServiceException {

	private static final long serialVersionUID = 1L;

	public static final GroupRepositoryException REQUIRED = new GroupRepositoryException(0, "group is required");
	public static final GroupRepositoryException ID_REQUIRED = new GroupRepositoryException(0, "group id is required");
	public static final GroupRepositoryException ID_CONFLICT = new GroupRepositoryException(0,
			"group id is already taken");
	public static final GroupRepositoryException MEMBERS_REQUIRED = new GroupRepositoryException(0,
			"group members are required");

	public GroupRepositoryException(Integer reasonCode, String message) {
		super(reasonCode, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupRepositoryException [super=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
