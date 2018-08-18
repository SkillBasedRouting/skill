package com.routing.skillservice.feature.user.resource;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.util.HttpResponseCodes;

import com.routing.skillservice.PATCH;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.feature.group.boundary.GroupBoundary;
import com.routing.skillservice.feature.user.boundary.UserBoundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <b>com.routing.skillservice.feature.group.resource.UserResource</b>
 * <p>
 *   JAX-RS endpoint for all user endpoints
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = { "User" })
public class UserResource {

	@Inject
	private UserBoundary userBoundary;

	@Inject
	private GroupBoundary groupBoundary;

	@Context
	private HttpResponse httpResonse;

	@GET
	@Path("/")
	@ApiOperation("get all users")
	public Set<UserDTO> getAll() {
		return this.userBoundary.getAll();
	}

	@GET
	@Path("/{id}")
	@ApiOperation("get a user")
	public UserDTO get(@ApiParam("the id of the user") @PathParam("id") final String id) {
		return this.userBoundary.get(id);
	}

	@GET
	@Path("/{id}/groups")
	@ApiOperation("get groups of a user")
	public Set<GroupDTO> getGroups(@ApiParam("the id of the user") @PathParam("id") final String id) {
		return this.groupBoundary.findByMember(id);
	}

	@POST
	@Path("/")
	@ApiOperation("create a user")
	public UserDTO create(@ApiParam("the user to create") final RequestUserDTO requestUserDTO) {

		final UserDTO createdUser = this.userBoundary.create(requestUserDTO);

		this.httpResonse.setStatus(HttpResponseCodes.SC_CREATED);

		return createdUser;
	}

	@PUT
	@Path("/{id}")
	@ApiOperation("update a user")
	public UserDTO update(@ApiParam("the id of the user") @PathParam("id") final String id,
			@ApiParam("new values") final EditUserDTO editUserDTO) {
		return this.userBoundary.update(id, editUserDTO);
	}

	@PATCH
	@Path("/{id}")
	@ApiOperation("patch a user")
	public UserDTO patch(@ApiParam("the id of the user") @PathParam("id") final String id,
			@ApiParam("new values") final EditUserDTO editUserDTO) {
		return this.userBoundary.patch(id, editUserDTO);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation("remove a user")
	public void remove(@ApiParam("the id of the user") @PathParam("id") final String id) {
		this.userBoundary.remove(id);
	}

}
