package com.routing.skillservice.feature.shape.boundary;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.exception.ExceptionLogInterceptor;
import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.shape.model.Shape;

import java.util.UUID;

/**
 * <b>com.routing.skillservice.feature.shape.boundary.ShapeBoundary</b>
 * <p>
 *   boundary for all shape actions, start new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ ExceptionLogInterceptor.class })
public class ShapeBoundary {

	@Inject
	private ShapeDAO shapeDAO;

	@Inject
	private ShapeMapper shapeMapper;

	public ShapeDTO create(final String user, final RequestShapeDTO requestShapeDTO) {

		if (null == user) {
			throw ShapeException.USER_REQUIRED;
		} else if (null == requestShapeDTO) {
			throw ShapeException.REQUIRED;
		}

		Shape shape = this.shapeMapper.toModel(requestShapeDTO);

        shape.setId(UUID.randomUUID().toString());
        shape.setUser(user);

        shape.validate();

		shape = this.shapeDAO.persist(shape);

		return shape.dto();
	}

	public ShapeDTO get(final String shapeId) {

		final Shape shape = this.shapeDAO.get(shapeId);

		return shape.dto();
	}

}
