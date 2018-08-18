package com.routing.skillservice.feature.skill.exception;

/**
 * <b>com.routing.skillservice.feature.skill.exception.SkillNotFoundException</b>
 * <p>
 *   exception that is thrown if skill is not found or does not exist
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillNotFoundException extends SkillException {

	private static final long serialVersionUID = 1L;

	private String key;

	public SkillNotFoundException(final String key) {
		super(0, "skill " + key + " not found");
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillNotFoundException [key=");
		builder.append(key);
		builder.append(", base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
