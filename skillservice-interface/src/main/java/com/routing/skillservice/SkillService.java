package com.routing.skillservice;

import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.dto.user.UserShapeDTO;

/**
 * <b>com.routing.skillservice.SkillService</b>
 * <p>
 *   interface that represent all http methods of skill api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Path("/api/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface SkillService {

	@GET
	@Path("shapes/{id}")
	public ShapeDTO getShape(@PathParam("id") final String id);

	@POST
	@Path("/shapes/")
	public ShapeDTO createShape(final RequestShapeDTO shapeDTO);

	@GET
	@Path("/skills/{key}")
	public SkillDTO getSkill(@PathParam("key") final String key);

	@POST
	@Path("/skills/")
	public SkillDTO createSkill(final RequestSkillDTO requestSkillDTO);

	@GET
	@Path("/skills")
	public Set<SkillDTO> getSkills();

	@GET
	@Path("/users/{id}")
	public UserDTO getUser(@PathParam("id") final String id);

	@POST
	@Path("/users/")
	public UserDTO createUser(final RequestUserDTO requestUserDTO);

	@PUT
	@Path("/users/{id}")
	public UserDTO updateUser(@PathParam("id") final String id, final EditUserDTO editUserDTO);

	@PATCH
	@Path("/users/{id}")
	public UserDTO patchUser(@PathParam("id") final String id, final EditUserDTO editUserDTO);

	@DELETE
	@Path("/users/{id}")
	public void removeUser(@PathParam("id") final String id);

	@GET
	@Path("/users")
	public Set<UserDTO> getUsers();

	@POST
	@Path("/shapes/query")
	public Set<UserShapeDTO> query(final QueryRequestDTO queryRequestDTO);

	@GET
	@Path("/users/{id}/groups")
	public Set<GroupDTO> findGroupsOfUser(@PathParam("id") final String id);

	@GET
	@Path("/groups/{key}")
	public GroupDTO getGroup(@PathParam("key") final String key);

	@POST
	@Path("/groups")
	public GroupDTO createGroup(final RequestGroupDTO requestGroupDTO);

	@PUT
	@Path("/groups/{key}")
	public GroupDTO updateGroup(@PathParam("key") final String key, final EditGroupDTO buildEditDTO);

	@PATCH
	@Path("/groups/{key}")
	public GroupDTO patchGroup(@PathParam("key") final String key, final EditGroupDTO buildEditDTO);

	@DELETE
	@Path("/groups/{key}")
	public void removeGroup(@PathParam("key") final String key);

	@POST
	@Path("/skills/multipliers")
	public Map<String, Double> getMultipliers(final Set<String> keys);

}
