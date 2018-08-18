package com.routing.skillservice.feature.shape.boundary;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestBooleanShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestEnumShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestIntegerShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.shape.model.BooleanShape;
import com.routing.skillservice.feature.shape.model.EnumShape;
import com.routing.skillservice.feature.shape.model.IntegerShape;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.skill.dao.SkillDAO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;

/**
 * <b>com.routing.skillservice.feature.shape.boundary.ShapeMapper</b>
 * <p>
 *   mapper for transforming {@link Shape} to {@link ShapeDTO} and vice verca
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class ShapeMapper {

	@Inject
	protected SkillDAO skillDAO;

    /**
     * map {@link RequestShapeDTO} to {@link Shape}
     * handles all derived classes of {@link RequestShapeDTO} and converts them to their corresponding {@link Shape} instances
     * @param requestShapeDTO shape to map
     * @return mapped shape
     */
	public Shape toModel(final RequestShapeDTO requestShapeDTO) {

		if (null == requestShapeDTO) {
			return null;
		}

		if (requestShapeDTO instanceof RequestIntegerShapeDTO) {
			return this.toModel((RequestIntegerShapeDTO) requestShapeDTO);
		} else if (requestShapeDTO instanceof RequestBooleanShapeDTO) {
			return this.toModel((RequestBooleanShapeDTO) requestShapeDTO);
		} else if (requestShapeDTO instanceof RequestEnumShapeDTO) {
			return this.toModel((RequestEnumShapeDTO) requestShapeDTO);
		} else {
			throw ShapeException.UNKNOWN_TYPE;
		}

	}

    /**
     * map {@link RequestIntegerShapeDTO} to {@link IntegerShape}
     * @param shapeDTO
     * @return
     */
	public IntegerShape toModel(final RequestIntegerShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final IntegerShape shape = new IntegerShape();
		shape.setIntegerValue(shapeDTO.getValue());
		if (null != shapeDTO.getSkillKey()) {
			shape.setIntegerSkill(this.skillDAO.get(shapeDTO.getSkillKey(), IntegerSkill.class));
		}

		return shape;
	}

    /**
     * map {@link RequestBooleanShapeDTO} to {@link BooleanShape}
     * @param shapeDTO shape to map
     * @return mapped shape
     */
	public BooleanShape toModel(final RequestBooleanShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final BooleanShape shape = new BooleanShape();
		shape.setBooleanValue(shapeDTO.getValue());
		if (null != shapeDTO.getSkillKey()) {
			shape.setBooleanSkill(this.skillDAO.get(shapeDTO.getSkillKey(), BooleanSkill.class));
		}

		return shape;
	}

    /**
     * map {@link RequestEnumShapeDTO} to {@link EnumShape}
     * @param shapeDTO shape to map
     * @return mapped shape
     */
	public EnumShape toModel(final RequestEnumShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			return null;
		}

		final EnumShape shape = new EnumShape();
		shape.setEnumValue(shapeDTO.getValue());
		if (null != shapeDTO.getSkillKey()) {
			shape.setEnumSkill(this.skillDAO.get(shapeDTO.getSkillKey(), EnumSkill.class));
		}

		return shape;
	}

    /**
     * map set of {@link RequestShapeDTO} to set of {@link Shape}
     * handles all derived classes of {@link RequestShapeDTO} and converts them to their corresponding {@link Shape} instances
     * @param requestShapeDTOSet shapes to map
     * @return mapped shapes
     */
	public Set<Shape> toModel(final Set<RequestShapeDTO> requestShapeDTOSet) {

		if (null == requestShapeDTOSet) {
			return null;
		}

		final Set<Shape> shapeSet = new LinkedHashSet<>(requestShapeDTOSet.size());
		requestShapeDTOSet.forEach((requestShapeDTO) -> {
			shapeSet.add(this.toModel(requestShapeDTO));
		});
		return shapeSet;
	}

}
