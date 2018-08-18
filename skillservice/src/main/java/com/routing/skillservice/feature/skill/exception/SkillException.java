package com.routing.skillservice.feature.skill.exception;

import com.routing.skillservice.exception.SkillServiceException;

/**
 * <b>com.routing.skillservice.feature.skill.exception.SkillException</b>
 * <p>
 *   exception for all skill errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillException extends SkillServiceException {

	private static final long serialVersionUID = 1L;

	public static final SkillException REQUIRED = new SkillException(0, "skill is required");
	public static final SkillException LABEL_REQUIRED = new SkillException(0, "skill label is required");
	public static final SkillException KEY_REQUIRED = new SkillException(0, "skill key is required");
	public static final SkillException VALUE_REQUIRED = new SkillException(0, "enumskill value is required");

	public static final SkillException UNKNOWN_TYPE = new SkillException(0, "skill type is not registered");

	public SkillException(Integer reasonCode, String message) {
		super(reasonCode, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillException [base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
