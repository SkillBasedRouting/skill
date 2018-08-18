package com.routing.skillservice.feature.group.boundary;

import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.exception.ExceptionLogInterceptor;
import com.routing.skillservice.feature.group.repository.GroupRepository;
import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.feature.group.boundary.GroupBoundary</b>
 * <p>
 *   boundary for all group actions, start new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ ExceptionLogInterceptor.class })
public class GroupBoundary {

	@Inject
	private GroupRepository groupRepository;

	@Inject
	private GroupMapper groupMapper;

	/**
	 * Create a group
	 * @param requestGroupDTO the group to create
	 * @return created group
	 */
	public GroupDTO create(final RequestGroupDTO requestGroupDTO) {

		Group group = this.groupMapper.toModel(requestGroupDTO);

		group = this.groupRepository.createGroup(group);

		return this.groupMapper.toDTO(group);
	}

	/**
	 * Update a group
	 * @param id group id
	 * @param editGroupDTO new values to update
	 * @return updated group
	 * @throws {@link com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException} if group does not exist
	 */
	public GroupDTO update(final String id, final EditGroupDTO editGroupDTO) {

		final Group newValues = this.groupMapper.toModel(editGroupDTO);

		final Group group = this.groupRepository.updateGroup(id, newValues);

		return this.groupMapper.toDTO(group);
	}

	/**
	 * Patch a group, all null values stay unchanged
	 * @param id group id
	 * @param editGroupDTO new values to update
	 * @return updated group
	 * @throws {@link com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException} if group does not exist
	 */
	public GroupDTO patch(final String id, final EditGroupDTO editGroupDTO) {

		final Group newValues = this.groupMapper.toModel(editGroupDTO);

		final Group group = this.groupRepository.patchGroup(id, newValues);

		return this.groupMapper.toDTO(group);
	}

	/**
	 * Get a group
	 * @param id group id
	 * @return group
	 * @throws {@link com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException} if group does not exist
	 */
	public GroupDTO get(final String id) {

		final Group group = this.groupRepository.getGroup(id);

		return this.groupMapper.toDTO(group);
	}

	/**
	 * Get all groups
	 * @return set of all groups
	 */
	public Set<GroupDTO> getAll() {

		final Set<Group> groups = this.groupRepository.getGroups();

		return this.groupMapper.toDTO(groups);
	}

	/**
	 * Find all groups by a member
	 * @param member id of member as its stored in the group
	 * @return all groups of member
	 */
	public Set<GroupDTO> findByMember(final String member) {

		final Set<Group> groups = this.groupRepository.ofMember(member);

		return this.groupMapper.toDTO(groups);
	}

	/**
	 * Remove a group
	 * @param id group id
	 */
	public void remove(final String id) {
		this.groupRepository.removeGroup(id);
	}

}