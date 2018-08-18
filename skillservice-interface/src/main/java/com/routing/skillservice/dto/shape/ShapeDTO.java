package com.routing.skillservice.dto.shape;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * <b>com.routing.skillservice.dto.shape.ShapeDTO</b>
 * <p>
 *   shapes are the values an {@link com.routing.skillservice.dto.user.UserDTO} has in a specific {@link com.routing.skillservice.dto.skill.SkillDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = BooleanShapeDTO.TYPE, value = BooleanShapeDTO.class),
		@Type(name = IntegerShapeDTO.TYPE, value = IntegerShapeDTO.class),
		@Type(name = EnumShapeDTO.TYPE, value = EnumShapeDTO.class) })
public class ShapeDTO {

	protected String id;
	protected String skillKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkillKey() {
		return skillKey;
	}

	public void setSkillKey(String skillKey) {
		this.skillKey = skillKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((skillKey == null) ? 0 : skillKey.hashCode());
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
		ShapeDTO other = (ShapeDTO) obj;
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
		builder.append("ShapeDTO [id=");
		builder.append(id);
		builder.append(", skillKey=");
		builder.append(skillKey);
		builder.append("]");
		return builder.toString();
	}

}
