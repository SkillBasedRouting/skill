package com.routing.skillservice.dto.user;

import java.util.Set;

import com.routing.skillservice.dto.shape.ShapeDTO;

/**
 * <b>com.routing.skillservice.dto.user.UserShapeDTO</b>
 * <p>
 *   represents all shapes an {@link UserDTO} has
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserShapeDTO {

	private String userId;
	private Set<ShapeDTO> shapes;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Set<ShapeDTO> getShapes() {
		return shapes;
	}

	public void setShapes(Set<ShapeDTO> shapes) {
		this.shapes = shapes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shapes == null) ? 0 : shapes.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserShapeDTO other = (UserShapeDTO) obj;
		if (shapes == null) {
			if (other.shapes != null)
				return false;
		} else if (!shapes.equals(other.shapes))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserShapeDTO [userId=");
		builder.append(userId);
		builder.append(", shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}

}
