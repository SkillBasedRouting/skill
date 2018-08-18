package com.routing.skillservice.dto.shape.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.user.UserDTO;

/**
 * <b>com.routing.skillservice.dto.shape.request.RequestShapeDTO</b>
 * <p>
 *   base entity which subclasses are used to create a {@link com.routing.skillservice.dto.shape.ShapeDTO}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(name = RequestBooleanShapeDTO.TYPE, value = RequestBooleanShapeDTO.class),
		@Type(name = RequestIntegerShapeDTO.TYPE, value = RequestIntegerShapeDTO.class),
		@Type(name = RequestEnumShapeDTO.TYPE, value = RequestEnumShapeDTO.class) })
public class RequestShapeDTO {

	protected String userId;
	protected String skillKey;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		RequestShapeDTO other = (RequestShapeDTO) obj;
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
		builder.append("RequestShapeDTO [userId=");
		builder.append(userId);
		builder.append(", skillKey=");
		builder.append(skillKey);
		builder.append("]");
		return builder.toString();
	}

}
