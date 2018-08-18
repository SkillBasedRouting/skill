package com.routing.skillservice.dto.shape.request;

import com.routing.skillservice.dto.shape.EnumShapeDTO;

import java.util.Set;

/**
 * <b>com.routing.skillservice.dto.shape.request.RequestEnumShapeDTO</b>
 * <p>
 *   entity to create a {@link EnumShapeDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequestEnumShapeDTO extends RequestShapeDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/shape/enum/request";

	private Set<String> value;

	public Set<String> getValue() {
		return value;
	}

	public void setValue(Set<String> value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((skillKey == null) ? 0 : skillKey.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RequestEnumShapeDTO other = (RequestEnumShapeDTO) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (skillKey == null) {
			if (other.skillKey != null)
				return false;
		} else if (!skillKey.equals(other.skillKey))
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
		builder.append("RequestEnumShapeDTO [super=");
		builder.append(super.toString());
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
