package com.routing.skillservice.feature.shape.model;

import java.util.Set;
import java.util.stream.Collectors;

import com.routing.skillservice.dto.user.UserShapeDTO;

/**
 * <b>com.routing.skillservice.feature.shape.model.UserShape</b>
 * <p>
 *   Entity that holds an user and all its shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserShape {

	private String user;
	private Set<Shape> shapes;

	public UserShape() {

	}

	public UserShape(final String user, final Set<Shape> shapes) {
		this.user = user;
		this.shapes = shapes;
	}

	public UserShapeDTO dto() {

		final UserShapeDTO dto = new UserShapeDTO();
		dto.setUserId(this.user);
		if (null != this.shapes) {
			dto.setShapes(this.shapes.stream().map(Shape::dto).collect(Collectors.toSet()));
		}

		return dto;
	}

	public Set<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(Set<Shape> shapes) {
		this.shapes = shapes;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((shapes == null) ? 0 : shapes.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UserShape other = (UserShape) obj;
		if (shapes == null) {
			if (other.shapes != null)
				return false;
		} else if (!shapes.equals(other.shapes))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserShape [user=");
		builder.append(user);
		builder.append(", shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}

}
