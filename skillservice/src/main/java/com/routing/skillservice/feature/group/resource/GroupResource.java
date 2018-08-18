package com.routing.skillservice.feature.group.resource;

import com.routing.skillservice.PATCH;
import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.feature.group.boundary.GroupBoundary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.jboss.resteasy.spi.HttpResponse;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * <b>com.routing.skillservice.feature.group.resource.GroupResource</b>
 * <p>
 * JAX-RS endpoint for all group methods
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = {"Group"})
public class GroupResource {

    @Inject
    private GroupBoundary groupBoundary;

    @Context
    private HttpResponse httpResponse;

    @POST
    @Path("/")
    @ApiOperation("create a group")
    public GroupDTO create(@ApiParam("the group to create") final RequestGroupDTO groupDTO) {

        final GroupDTO createdGroup = this.groupBoundary.create(groupDTO);

        this.httpResponse.setStatus(Response.Status.CREATED.getStatusCode());

        return createdGroup;
    }

    @PUT
    @Path("/{key}")
    @ApiOperation("update a group")
    public GroupDTO update(@ApiParam("the key of the group") @PathParam("key") final String key,
                           @ApiParam("new values") final EditGroupDTO groupDTO) {
        return this.groupBoundary.update(key, groupDTO);
    }

    @PATCH
    @Path("/{key}")
    @ApiOperation("patch a group")
    public GroupDTO patch(@ApiParam("the key of the group") @PathParam("key") final String key,
                          @ApiParam("new values") final EditGroupDTO groupDTO) {
        return this.groupBoundary.patch(key, groupDTO);
    }

    @DELETE
    @Path("/{key}")
    @ApiOperation("delete a group")
    public void remove(@ApiParam("the key of the group") @PathParam("key") final String key) {
        this.groupBoundary.remove(key);
    }

    @GET
    @Path("/{key}")
    @ApiOperation("get a group")
    public GroupDTO get(@ApiParam("the key of the group") @PathParam("key") final String key) {
        return this.groupBoundary.get(key);
    }

    @GET
    @Path("/")
    @ApiOperation("get all groups")
    public Set<GroupDTO> getAll() {
        return this.groupBoundary.getAll();
    }

}
