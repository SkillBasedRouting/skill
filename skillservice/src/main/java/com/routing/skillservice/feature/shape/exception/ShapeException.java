package com.routing.skillservice.feature.shape.exception;

import com.routing.skillservice.exception.SkillServiceException;

/**
 * <b>com.routing.skillservice.feature.shape.exception.ShapeException</b>
 * <p>
 *   exception for all shape errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ShapeException extends SkillServiceException {

	private static final long serialVersionUID = 1L;

	public static final ShapeException REQUIRED = new ShapeException(0, "shape is required");
	public static final ShapeException QUERY_NOT_FOUND = new ShapeException(0, "no result for query");

	public static final ShapeException SKILL_REQUIRED = new ShapeException(0, "shape skill is required");
	public static final ShapeException VALUE_REQUIRED = new ShapeException(0, "shape value is required");
	public static final ShapeException VALUE_INVALID = new ShapeException(0, "shape value is not valid");
	public static final ShapeException USER_REQUIRED = new ShapeException(0, "shape user is required");

	public static final ShapeException UNKNOWN_TYPE = new ShapeException(0, "shape type is not registered");

	public ShapeException(Integer reasonCode, String message) {
		super(reasonCode, message);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShapeException [base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
