package com.routing.skillservice.feature.skill.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.routing.skillservice.feature.skill.exception.SkillException;

/**
 * <b>com.routing.skillservice.feature.skill.model.Skill</b>
 * <p>
 *   base entity for a skill
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_Skill")
@Inheritance(strategy = InheritanceType.JOINED)
public class Skill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer internId;

	@Column(name = "_key", unique = true)
	private String key;

	@Column(name = "label")
	private String label;

    /**
     * validate this skill
     * @throws SkillException if validation fails
     */
	public void validate() {
		
		if (null == this.key) {
			throw SkillException.KEY_REQUIRED;
		}
		
		if (null == this.label) {
			throw SkillException.LABEL_REQUIRED;
		}
		
	}

	public Integer getInternId() {
		return internId;
	}

	public void setInternId(Integer internId) {
		this.internId = internId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((internId == null) ? 0 : internId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
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
		Skill other = (Skill) obj;
		if (internId == null) {
			if (other.internId != null)
				return false;
		} else if (!internId.equals(other.internId))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Skill [internId=");
		builder.append(internId);
		builder.append(", key=");
		builder.append(key);
		builder.append(", label=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}

}
