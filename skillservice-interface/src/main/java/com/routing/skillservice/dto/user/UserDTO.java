package com.routing.skillservice.dto.user;

import java.util.Set;

import com.routing.skillservice.dto.shape.ShapeDTO;

/**
 * <b>com.routing.skillservice.dto.user.UserDTO</b>
 * <p>
 *   entity that represent an user
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class UserDTO {

	private String id;
	private String name;
	private Set<ShapeDTO> shapes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((shapes == null) ? 0 : shapes.hashCode());
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
		UserDTO other = (UserDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (shapes == null) {
			if (other.shapes != null)
				return false;
		} else if (!shapes.equals(other.shapes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shapes=");
		builder.append(shapes);
		builder.append("]");
		return builder.toString();
	}

}
