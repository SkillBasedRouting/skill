package com.routing.skillservice.feature.group.repository.exception;

/**
 * <b>com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException</b>
 * <p>
 *   exception that is thrown if a group does not exist or is not found
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class GroupNotFoundException extends GroupRepositoryException {

	private static final long serialVersionUID = 1L;

	private String groupId;

	public GroupNotFoundException(final String groupId) {
		super(0, "group " + groupId + " not found");
		this.groupId = groupId;
	}

	public String getGroupId() {
		return groupId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroupNotFoundException [base=");
		builder.append(super.toString());
		builder.append(", groupId=");
		builder.append(groupId);
		builder.append("]");
		return builder.toString();
	}

}
