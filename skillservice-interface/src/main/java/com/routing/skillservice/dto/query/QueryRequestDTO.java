package com.routing.skillservice.dto.query;

import java.util.Set;

import com.routing.skillservice.dto.shape.request.RequestShapeDTO;

/**
 * <b>com.routing.skillservice.dto.query.QueryRequestDTO</b>
 * <p>
 *   request for querying users by its group memberships and/or shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class QueryRequestDTO {

	private Set<RequestShapeDTO> shapes;
	private Set<String> groups;

	public Set<RequestShapeDTO> getShapes() {
		return shapes;
	}

	public void setShapes(Set<RequestShapeDTO> shapes) {
		this.shapes = shapes;
	}

	public Set<String> getGroups() {
		return groups;
	}

	public void setGroups(Set<String> groups) {
		this.groups = groups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
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
		QueryRequestDTO other = (QueryRequestDTO) obj;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
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
		builder.append("QueryRequestDTO [shapes=");
		builder.append(shapes);
		builder.append(", groups=");
		builder.append(groups);
		builder.append("]");
		return builder.toString();
	}

}
