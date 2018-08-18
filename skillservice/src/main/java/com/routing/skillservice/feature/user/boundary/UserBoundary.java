package com.routing.skillservice.feature.user.boundary;

import java.util.Set;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.exception.ExceptionLogInterceptor;
import com.routing.skillservice.feature.user.repository.UserRepository;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.user.boundary.UserBoundary</b>
 * <p>
 *   boundary for all user actions, starts new transaction
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ ExceptionLogInterceptor.class })
public class UserBoundary {

	@Inject
	private UserRepository userRepository;

	@Inject
	private UserMapper userMapper;

    /**
     * create an user
     * @param requestUserDTO user to create
     * @return created user
     */
	public UserDTO create(final RequestUserDTO requestUserDTO) {

		User user = this.userMapper.toModel(requestUserDTO);

		user = this.userRepository.createUser(user);

		return this.userMapper.toDTO(user);
	}

    /**
     * get an user
     * @param id user id
     * @return user
     */
	public UserDTO get(final String id) {

		final User user = this.userRepository.getUser(id);

		return this.userMapper.toDTO(user);
	}

    /**
     * get all users
     * @return set of all users
     */
	public Set<UserDTO> getAll() {

		final Set<User> userSet = this.userRepository.getUsers();

		return this.userMapper.toDTO(userSet);
	}

    /**
     * remove an user
     * @param id user id
     */
	public void remove(final String id) {
		this.userRepository.removeUser(id);
	}

    /**
     * partial update an user, all null values will be ignored
     * @param id user id
     * @param editUserDTO new values
     * @return updated user
     */
	public UserDTO patch(final String id, final EditUserDTO editUserDTO) {

		final User newValues = this.userMapper.toModel(editUserDTO);
		final User user = this.userRepository.patchUser(id, newValues);

		return this.userMapper.toDTO(user);
	}

    /**
     * update an user, all null values will be set
     * @param id user id
     * @param editUserDTO new values
     * @return updated user
     */
	public UserDTO update(final String id, final EditUserDTO editUserDTO) {

		final User newValues = this.userMapper.toModel(editUserDTO);
		final User user = this.userRepository.updateUser(id, newValues);

		return this.userMapper.toDTO(user);
	}

}
