package com.routing.skillservice.feature.user.repository.impl.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.skillservice.feature.user.repository.UserRepository;
import com.routing.skillservice.feature.user.repository.exception.UserRepositoryException;
import com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserRepository</b>
 * <p>
 *   jpa implementation of {@link UserRepository}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class JPAUserRepository implements UserRepository {

	@Inject
	protected JPAUserDAO userDAO;

    /**
     * {@inheritDoc}
     */
	@Override
	public User getUser(final String id) {

		final JPAUser jpaUser = this.userDAO.get(id);

		return jpaUser.user();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<User> getUsers() {

		final Set<JPAUser> jpaUsers = this.userDAO.getAll();

		return jpaUsers.stream().map(JPAUser::user).collect(Collectors.toSet());
	}

	/**
	 * create an user
	 * @param user user to create
	 * @return created user
	 * @throws UserRepositoryException if user is null
	 * @throws UserRepositoryException if id of user is not set
	 * @throws UserRepositoryException if id of user is already taken
	 */
	@Override
	public User createUser(final User user) {

		if (null == user) {
			throw UserRepositoryException.REQUIRED;
		}

		JPAUser jpaUser = new JPAUser(user);

		if (null == jpaUser.getId()) {
			throw UserRepositoryException.ID_REQUIRED;
		} else if (this.userDAO.exists(jpaUser.getId())) {
			throw UserRepositoryException.ID_CONFLICT;
		}

		jpaUser = this.userDAO.persist(jpaUser);

		return jpaUser.user();
	}

	/**
	 * update an user, all null values will be set if possible, otherwise an exception is be raised
	 * @param id id of user
	 * @param newValues new values
	 * @return updated user
	 * @throws UserRepositoryException if user is invalid
	 * @throws com.routing.skillservice.feature.user.repository.exception.UserNotFoundException if user id is not found
	 */
	@Override
	public User updateUser(final String id, final User newValues) {

		final JPAUser newJPAValues = new JPAUser(newValues);
		final JPAUser jpaUser = this.userDAO.get(id);

		jpaUser.update(newJPAValues);

		return jpaUser.user();
	}

	/**
	 * partial update an user, all null values will be ignored
	 * @param id id of user
	 * @param newValues new values
	 * @return updated user
	 * @throws com.routing.skillservice.feature.user.repository.exception.UserNotFoundException if user is not found
	 */
	@Override
	public User patchUser(final String id, final User newValues) {

		final JPAUser newJPAValues = new JPAUser(newValues);
		final JPAUser jpaUser = this.userDAO.get(id);

		jpaUser.patch(newJPAValues);

		return jpaUser.user();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeUser(final String id) {
		this.userDAO.remove(id);
	}

}
