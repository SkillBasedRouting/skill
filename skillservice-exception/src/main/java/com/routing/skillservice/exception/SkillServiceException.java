package com.routing.skillservice.exception;

/**
 * <b>com.routing.skillservice.exception.SkillServiceException</b>
 * <p>
 *   exception for all service specific errors that can occur
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static final SkillServiceException GENERIC_ERROR = new SkillServiceException(0, "unexpected error");

	private Integer reasonCode;

	public SkillServiceException(final Integer reasonCode, final String message) {
		super(message);
		this.reasonCode = reasonCode;
	}

	public Integer getReasonCode() {
		return reasonCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillServiceException [reasonCode=");
		builder.append(reasonCode);
		builder.append(", message=");
		builder.append(super.getMessage());
		builder.append("]");
		return builder.toString();
	}

}
