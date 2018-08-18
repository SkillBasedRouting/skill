package com.routing.skillservice.dto.shape.request;

import com.routing.skillservice.dto.shape.IntegerShapeDTO;

/**
 * <b>com.routing.skillservice.dto.shape.request.RequestIntegerShapeDTO</b>
 * <p>
 *   entity to create a {@link IntegerShapeDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class RequestIntegerShapeDTO extends RequestShapeDTO {
	
	public static final String TYPE = "http://routing.com/skillservice/v1/types/shape/integer/request";

	private Integer value;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((skillKey == null) ? 0 : skillKey.hashCode());
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
		RequestIntegerShapeDTO other = (RequestIntegerShapeDTO) obj;
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
		builder.append("RequestIntegerShapeDTO [base=");
		builder.append(super.toString());
		builder.append("value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
