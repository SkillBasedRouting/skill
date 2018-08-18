package com.routing.skillservice.feature.shape.exception;

/**
 * <b>com.routing.skillservice.feature.shape.exception.ShapeNotFoundException</b>
 * <p>
 *   exception that is thrown when shape does not exist or is unavailable
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ShapeNotFoundException extends ShapeException {

	private static final long serialVersionUID = 1L;

	private String shapeId;

	public ShapeNotFoundException() {
		super(0, "shape not found");
	}

	public ShapeNotFoundException(final String shapeId) {
		super(0, "shape " + shapeId + " not found");
		this.shapeId = shapeId;
	}

	public String getShapeId() {
		return shapeId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ShapeNotFoundException [shapeId=");
		builder.append(this.shapeId);
		builder.append(", base=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

}
