package com.routing.skillservice.feature.user.boundary;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.feature.shape.boundary.ShapeMapper;
import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.user.boundary.UserMapper</b>
 * <p>
 *   map an {@link User} to an {@link UserDTO} and vice versa
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class UserMapper {

	@Inject
	protected ShapeMapper shapeMapper;

	@Inject
	protected ShapeDAO shapeDAO;

    /**
     * map {@link User} to {@link UserDTO}
     * @param user user to map
     * @return mapped user
     */
	public UserDTO toDTO(final User user) {

		if (null == user) {
			return null;
		}

		final UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setShapes(this.shapeDAO.getByUser(user.getId()).stream().map(Shape::dto).collect(Collectors.toSet()));

		return userDTO;
	}

    /**
     * map {@link RequestUserDTO} to {@link User}
     * @param requestUserDTO user to map
     * @return mapped user
     */
	public User toModel(final RequestUserDTO requestUserDTO) {

		if (null == requestUserDTO) {
			return null;
		}

		final User user = new User();
		user.setId(requestUserDTO.getId());
		user.setName(requestUserDTO.getName());
		
		return user;
	}

    /**
     * map {@link EditUserDTO} to {@link User}
     * @param editUserDTO user to map
     * @return mapped user
     */
	public User toModel(final EditUserDTO editUserDTO) {

		if (null == editUserDTO) {
			return null;
		}

		final User user = new User();
		user.setId(editUserDTO.getId());
		user.setName(editUserDTO.getName());

		return user;
	}

    /**
     * map set of {@link User} to {@link UserDTO}
     * @param userSet set of users to map
     * @return mapped set of users
     */
	public Set<UserDTO> toDTO(final Set<User> userSet) {

		if (null == userSet) {
			return null;
		}

		return userSet.stream().map(this::toDTO).collect(Collectors.toSet());
	}

}
