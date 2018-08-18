package com.routing.skillservice.feature.shape.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.dto.user.UserShapeDTO;
import com.routing.skillservice.feature.query.QueryBoundary;
import com.routing.skillservice.feature.shape.boundary.ShapeBoundary;
import com.routing.skillservice.feature.shape.exception.ShapeException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <b>com.routing.skillservice.feature.group.resource.ShapeResource</b>
 * <p>
 *   JAX-RS endpoint for all shape methods
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Path("/shapes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = { "Shape" })
public class ShapeResource {

	@Inject
	private ShapeBoundary shapeBoundary;

	@Inject
	private QueryBoundary queryBoundary;

	@GET
	@Path("/{id}")
	@ApiOperation("get a shape")
	public ShapeDTO get(@ApiParam("the id of the shape") @PathParam("id") final String id) {
		return this.shapeBoundary.get(id);
	}

	@POST
	@Path("/")
	@ApiOperation("create a shape")
	public ShapeDTO create(
			@ApiParam("the shape to create, see objects extending RequestShapeDTO for possible types") final RequestShapeDTO shapeDTO) {

		if (null == shapeDTO) {
			throw ShapeException.REQUIRED;
		}

		return this.shapeBoundary.create(shapeDTO.getUserId(), shapeDTO);
	}

	@POST
	@Path("/query")
	@ApiOperation("match users with shapes")
	public Set<UserShapeDTO> query(
			@ApiParam("set of shapes and groups that should apply to all users in response") final QueryRequestDTO queryRequestDTO) {
		return this.queryBoundary.query(queryRequestDTO);
	}

}
