package com.routing.skillservice.client;

import java.util.Map;
import java.util.Set;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.routing.skillservice.SkillService;
import com.routing.skillservice.client.auth.AuthRequestFilter;
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
 * <b>com.routing.skillservice.client.SkillServiceClient</b>
 * <p>
 *   api client for skillservice
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class SkillServiceClient {

	private SkillService service;

    /**
     * base constructor with url, no auth will be used
     * @param url url of endpoint, e.g. http://localhost/skillservice, '/api/v1' will be added during invocation
     */
	public SkillServiceClient(final String url) {
		this(url, null);
	}

    /**
     * constructor with url and jwt, jwt will be transfered as 'Authorization' header
     * keep in mind that the client will not validate this jwt and it may get invalid after a while
     * @param url url of endpoint, e.g. http://localhost/skillservice, '/api/v1' will be added during invocation
     * @param jwt valid jwt
     */
	public SkillServiceClient(final String url, final String jwt) {

		final ResteasyClient client = new ResteasyClientBuilder().build();
		if (null != jwt) {
			client.register(new AuthRequestFilter(jwt));
		}
		final ResteasyWebTarget target = client.target(url);
		this.service = target.proxy(SkillService.class);
	}

    /**
     * get a shape
     * @param id id of shape
     * @return shape
     */
	public ShapeDTO getShape(final String id) {
		return this.service.getShape(id);
	}

    /**
     * create a shape
     * @param shapeDTO shape to create
     * @return created shape
     */
	public ShapeDTO createShape(final RequestShapeDTO shapeDTO) {
		return this.service.createShape(shapeDTO);
	}

    /**
     * get a skill
     * @param key key of skill
     * @return skill
     */
	public SkillDTO getSkill(final String key) {
		return this.service.getSkill(key);
	}

    /**
     * create a skill
     * @param requestSkillDTO skill to create
     * @return created skill
     */
	public SkillDTO createSkill(final RequestSkillDTO requestSkillDTO) {
		return this.service.createSkill(requestSkillDTO);
	}

    /**
     * get all skills
     * @return set of all skills
     */
	public Set<SkillDTO> getSkills() {
		return this.service.getSkills();
	}

    /**
     * get an user
     * @param id id of user
     * @return user
     */
	public UserDTO getUser(final String id) {
		return this.service.getUser(id);
	}

    /**
     * create an user
     * @param requestUserDTO user to create
     * @return created user
     */
	public UserDTO createUser(final RequestUserDTO requestUserDTO) {
		return this.service.createUser(requestUserDTO);
	}

    /**
     * update an user, all null values will be set if possible, otherwise an exception will be raised
     * @param id id of user
     * @param editUserDTO new values
     * @return updated user
     */
	public UserDTO updateUser(final String id, final EditUserDTO editUserDTO) {
		return this.service.updateUser(id, editUserDTO);
	}

    /**
     * partial update an user, all null values will be ignored
     * @param id id of user
     * @param editUserDTO new values
     * @return updated user
     */
	public UserDTO patchUser(final String id, final EditUserDTO editUserDTO) {
		return this.service.patchUser(id, editUserDTO);
	}

    /**
     * remove an user
     * @param id id of user
     */
	public void removeUser(final String id) {
		this.service.removeUser(id);
	}

    /**
     * get all users
     * @return set of all users
     */
	public Set<UserDTO> getUsers() {
		return this.service.getUsers();
	}

    /**
     * find all groups of an user
     * @param id id of user
     * @return all groups of user
     */
	public Set<GroupDTO> findGroupsOfUser(final String id) {
		return this.service.findGroupsOfUser(id);
	}

    /**
     * query all users by group memberships and/or shapes
     * @param queryRequestDTO query request
     * @return all found users with its shapes that can be higher than requested
     */
	public Set<UserShapeDTO> query(final QueryRequestDTO queryRequestDTO) {
		return this.service.query(queryRequestDTO);
	}

    /**
     * get a group
     * @param key key of group
     * @return group
     */
	public GroupDTO getGroup(final String key) {
		return this.service.getGroup(key);
	}

    /**
     * create a group
     * @param requestGroupDTO group to create
     * @return created group
     */
	public GroupDTO createGroup(final RequestGroupDTO requestGroupDTO) {
		return this.service.createGroup(requestGroupDTO);
	}

    /**
     * update a group, all null values will be set otherwise an exception will be raised
     * @param key key of group
     * @param buildEditDTO new values
     * @return updated group
     */
	public GroupDTO updateGroup(final String key, final EditGroupDTO buildEditDTO) {
		return this.service.updateGroup(key, buildEditDTO);
	}

    /**
     * partial update a group, all null values will be ignored
     * @param key key of group
     * @param buildEditDTO new values
     * @return updated group
     */
	public GroupDTO patchGroup(final String key, final EditGroupDTO buildEditDTO) {
		return this.service.patchGroup(key, buildEditDTO);
	}

    /**
     * remove a group
     * @param id id of group
     */
	public void removeGroup(final String id) {
		this.service.removeGroup(id);
	}

    /**
     * get all multipliers of given group keys
     * @param keys keys of group
     * @return multipliers as map [key = group key, value = multiplier]
     */
	public Map<String, Double> getMultipliers(final Set<String> keys) {
		return this.service.getMultipliers(keys);
	}

}
