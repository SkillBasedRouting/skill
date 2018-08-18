package com.routing.skillservice.test.feature.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.routing.skillservice.dto.user.EditUserDTO;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.feature.shape.model.Shape;
import com.routing.skillservice.feature.user.boundary.UserMapper;
import com.routing.skillservice.feature.user.repository.model.User;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;
import com.routing.skillservice.test.mock.UserBuilder;
import com.routing.skillservice.test.mock.UserMapperProxy;

public class UserMapperTest {

	private Set<Shape> shapes = new HashSet<>(2);
	private UserMapper mapper = new UserMapperProxy(shapes);

	public UserMapperTest() {
		this.shapes.add(BooleanShapeBuilder.defaultBuilder().build());
		this.shapes.add(IntegerShapeTestBuilder.defaultBuilder().build());
	}

	public UserBuilder defaultBuilder() {
		return UserBuilder.defaultBuilder();
	}

	// toDTO

	@Test
	public void should_MapId_On_MapToDTO() {

		final UserBuilder builder = this.defaultBuilder();
		final User user = builder.build();

		final UserDTO dto = this.mapper.toDTO(user);

		assertEquals(user.getId(), dto.getId());
	}

	@Test
	public void should_ReturnNull_When_DTOIsNull_On_MapToDTO() {

		assertEquals(null, this.mapper.toDTO((User) null));

	}

	@Test
	public void should_MapShapes_On_MapToDTO() {

		final UserBuilder builder = this.defaultBuilder();
		final User user = builder.build();

		final UserDTO dto = this.mapper.toDTO(user);

		this.shapes.forEach(shape -> {
			assertTrue(dto.getShapes().stream()
					.filter(shapeDTO -> shapeDTO.getSkillKey().equals(shape.getSkill().getKey())).findAny()
					.isPresent());
		});
	}

	// request -> model

	@Test
	public void should_SetUserId_On_MapRequestToModel() {

		final UserBuilder builder = this.defaultBuilder();
		final RequestUserDTO dto = builder.buildRequestDTO();

		final User user = this.mapper.toModel(dto);

		assertEquals(dto.getId(), user.getId());
	}

	// edit -> model

	@Test
	public void should_SetUserId_On_MapEditToModel() {

		final UserBuilder builder = this.defaultBuilder();
		final EditUserDTO dto = builder.buildEditDTO();

		final User user = this.mapper.toModel(dto);

		assertEquals(dto.getId(), user.getId());
	}

}
