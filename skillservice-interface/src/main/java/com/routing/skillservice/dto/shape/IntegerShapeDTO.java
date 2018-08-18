package com.routing.skillservice.dto.shape;

import com.routing.skillservice.dto.skill.IntegerSkillDTO;

/**
 * <b>com.routing.skillservice.dto.shape.IntegerShapeDTO</b>
 * <p>
 *   shape an {@link com.routing.skillservice.dto.user.UserDTO} has in an {@link IntegerSkillDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class IntegerShapeDTO extends ShapeDTO {

	public static final String TYPE = "http://routing.com/skillservice/v1/types/shape/integer";
	
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
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((skillKey == null) ? 0 : skillKey.hashCode());
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
		IntegerShapeDTO other = (IntegerShapeDTO) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (skillKey == null) {
			if (other.skillKey != null)
				return false;
		} else if (!skillKey.equals(other.skillKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntegerShapeDTO [value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
