package com.routing.skillservice.feature.user.repository;

import java.util.Set;

import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.user.repository.UserRepository</b>
 * <p>
 *   interface with all {@link User} use cases, can be implemented, annotated with cdi and added to classpath
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public interface UserRepository {

    /**
     * create an user
     * @param user user to create
     * @return created user
     */
	User createUser(final User user);

    /**
     * update an user, all null values will be set if possible, otherwise an exception is raised
     * @param id id of user
     * @param newValues new values
     * @return updated user
     */
	User updateUser(final String id, final User newValues);

    /**
     * partial update an user, all null values will be ignored
     * @param id id of user
     * @param newValues new values
     * @return updated user
     */
	User patchUser(final String id, final User newValues);

    /**
     * get an user
     * @param id id of user
     * @return user
     */
	User getUser(final String id);

    /**
     * get all users
     * @return set of all users
     */
	Set<User> getUsers();

    /**
     * remove an user
     * @param id id of user
     */
	void removeUser(final String id);

}
