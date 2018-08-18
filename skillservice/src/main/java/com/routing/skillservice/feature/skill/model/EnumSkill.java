package com.routing.skillservice.feature.skill.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import com.routing.skillservice.feature.skill.exception.SkillException;

/**
 * <b>com.routing.skillservice.feature.skill.model.EnumSkill</b>
 * <p>
 *   entity that represents a skill that can have multiple values
 *   example: north, south, west, east
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_EnumSkill")
public class EnumSkill extends Skill {

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> value;

    /**
     * {@inheritDoc}
     */
	@Override
	public void validate() {
		super.validate();
		
		if (null == value || value.size() == 0) {
			throw SkillException.VALUE_REQUIRED;
		}
	}

	public Set<String> getValue() {
		return this.value;
	}

	public void setValue(Set<String> value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		EnumSkill other = (EnumSkill) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EnumSkill [super=");
		builder.append(super.toString());
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
