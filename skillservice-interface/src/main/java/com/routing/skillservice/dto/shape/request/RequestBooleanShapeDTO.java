package com.routing.skillservice.dto.shape.request;

import com.routing.skillservice.dto.shape.BooleanShapeDTO;

/**
 * <b>com.routing.skillservice.dto.shape.request.RequestBooleanShapeDTO</b>
 * <p>
 *   entity used to create a {@link BooleanShapeDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequestBooleanShapeDTO extends RequestShapeDTO {
	
	public static final String TYPE = "http://routing.com/skillservice/v1/types/shape/boolean/request";

	private Boolean value;

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
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
		RequestBooleanShapeDTO other = (RequestBooleanShapeDTO) obj;
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
		builder.append("RequestBooleanShapeDTO [base=");
		builder.append(super.toString());
		builder.append("value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
