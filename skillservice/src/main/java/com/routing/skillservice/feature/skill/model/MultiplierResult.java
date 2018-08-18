package com.routing.skillservice.feature.skill.model;

/**
 * <b>com.routing.skillservice.feature.skill.model.MultiplierResult</b>
 * <p>
 *   entity for querying multipliers of integer skills
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class MultiplierResult {

	private String skillKey;
	private Double multiplier;

	public MultiplierResult(String skillKey, Double multiplier) {
		super();
		this.skillKey = skillKey;
		this.multiplier = multiplier;
	}

	public String getSkillKey() {
		return skillKey;
	}

	public void setSkillKey(String skillKey) {
		this.skillKey = skillKey;
	}

	public Double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(Double multiplier) {
		this.multiplier = multiplier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((multiplier == null) ? 0 : multiplier.hashCode());
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
		MultiplierResult other = (MultiplierResult) obj;
		if (multiplier == null) {
			if (other.multiplier != null)
				return false;
		} else if (!multiplier.equals(other.multiplier))
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
		builder.append("MultiplierResult [skillKey=");
		builder.append(skillKey);
		builder.append(", multiplier=");
		builder.append(multiplier);
		builder.append("]");
		return builder.toString();
	}

}
