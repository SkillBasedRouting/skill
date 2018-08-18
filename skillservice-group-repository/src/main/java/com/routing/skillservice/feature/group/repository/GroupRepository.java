package com.routing.skillservice.feature.group.repository;

import java.util.Set;

import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.feature.group.repository.GroupRepository</b>
 * <p>
 *   interface with all {@link Group} use cases, can be implemented, annotated with cdi and added to classpath
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public interface GroupRepository {

    /**
     * create a group
     * @param group the group to create
     * @return created group
     */
	Group createGroup(final Group group);

    /**
     * update a group, all null values will be set
     * @param id id of group
     * @param newValues new values
     * @return updated group
     */
	Group updateGroup(final String id, final Group newValues);

    /**
     * partial update a group, all null values stay unchanged
     * @param id id of group
     * @param newValues new values
     * @return updated group
     */
	Group patchGroup(final String id, final Group newValues);

    /**
     * get a group
     * @param id id of group
     * @return group
     */
	Group getGroup(final String id);

    /**
     * get all groups
     * @return set of all groups
     */
	Set<Group> getGroups();

    /**
     * get all groups of member
     * @param member member id as its stored in group
     * @return all groups of member
     */
	Set<Group> ofMember(final String member);

    /**
     * remove a group by its id
     * @param id id of group
     */
	void removeGroup(final String id);

}
