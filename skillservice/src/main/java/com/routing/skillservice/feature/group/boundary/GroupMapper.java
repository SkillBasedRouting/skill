package com.routing.skillservice.feature.group.boundary;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import com.routing.skillservice.dto.group.EditGroupDTO;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.feature.group.boundary.GroupMapper</b>
 * <p>
 *   Mapper for transfering {@link Group} to {@link GroupDTO} and vice versa
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class GroupMapper {

    /**
     * map {@link Group} to {@link GroupDTO}
     * @param group group to map
     * @return mapped group
     */
	public GroupDTO toDTO(final Group group) {

		if (null == group) {
			return null;
		}

		final GroupDTO groupDTO = new GroupDTO();
		groupDTO.setId(group.getId());
		groupDTO.setName(group.getName());
		groupDTO.setMembers(group.getMembers());

		return groupDTO;
	}

    /**
     * map {@link RequestGroupDTO} to {@link Group}
     * @param groupDTO group to map
     * @return mapped group
     */
	public Group toModel(final RequestGroupDTO groupDTO) {

		if (null == groupDTO) {
			return null;
		}

		final Group group = new Group();
		group.setId(groupDTO.getId());
		group.setName(groupDTO.getName());
		group.setMembers(groupDTO.getMembers());

		return group;
	}

    /**
     * map {@link EditGroupDTO} to {@link Group}
     * @param groupDTO group to map
     * @return mapped group
     */
	public Group toModel(final EditGroupDTO groupDTO) {

		if (null == groupDTO) {
			return null;
		}

		final Group group = new Group();
		group.setId(groupDTO.getId());
		group.setName(groupDTO.getName());
		group.setMembers(groupDTO.getMembers());

		return group;
	}

    /**
     * map set of {@link Group} to set of {@link GroupDTO}
     * @param groups groups to mapp
     * @return mapped groups
     */
	public Set<GroupDTO> toDTO(final Set<Group> groups) {

		if (null == groups) {
			return null;
		}

		return groups.stream().map(this::toDTO).collect(Collectors.toSet());
	}

}
