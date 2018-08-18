package com.routing.skillservice.feature.user.repository.exception;

/**
 * <b>com.routing.skillservice.feature.user.repository.exception.UserNotFoundException</b>
 * <p>
 *   exception that is thrown if user is not found or does not exist
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserNotFoundException extends UserRepositoryException {

	private static final long serialVersionUID = 1L;

	private String userId;

	public UserNotFoundException(final String userId) {
		super(0, "user " + userId + " not found");
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserNotFoundException [base=");
		builder.append(super.toString());
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}

}
