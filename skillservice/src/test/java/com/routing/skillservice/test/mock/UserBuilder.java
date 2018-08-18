package com.routing.skillservice.test.mock;

import java.util.UUID;

import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.feature.user.repository.model.User;

public class UserBuilder {

	public static UserBuilder defaultBuilder() {
		return new UserBuilder().setId(UUID.randomUUID().toString());
	}

	private User user;

	public UserBuilder() {
		this(new User());
	}

	public UserBuilder(final User user) {
		super();
		this.user = user;
	}

	public UserBuilder setId(final String userId) {
		this.user.setId(userId);
		return this;
	}

	public UserBuilder setName(final String name) {
		this.user.setName(name);
		return this;
	}

	public User build() {
		final User user = new User();
		user.setId(this.user.getId());
		user.setName(this.user.getName());
		return user;
	}

	public UserDTO buildDTO() {
		final UserDTO userDTO = new UserDTO();
		userDTO.setId(this.user.getId());
		userDTO.setName(this.user.getName());
		return userDTO;
	}

	public RequestUserDTO buildRequestDTO() {
		final RequestUserDTO userDTO = new RequestUserDTO();
		userDTO.setId(this.user.getId());
		userDTO.setName(this.user.getName());
		return userDTO;
	}

	public EditUserDTO buildEditDTO() {
		final EditUserDTO userDTO = new EditUserDTO();
		userDTO.setId(this.user.getId());
		userDTO.setName(this.user.getName());
		return userDTO;
	}

}
