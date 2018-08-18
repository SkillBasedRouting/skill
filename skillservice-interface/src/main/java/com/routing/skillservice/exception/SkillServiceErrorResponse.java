package com.routing.skillservice.exception;

/**
 * <b>com.routing.skillservice.exception.SkillServiceErrorResponse</b>
 * <p>
 *   structure an exception is exposed over the api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillServiceErrorResponse {

	private Integer reasonCode;
	private String message;

	public Integer getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(Integer reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SkillServiceError [reasonCode=");
		builder.append(reasonCode);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}

}
