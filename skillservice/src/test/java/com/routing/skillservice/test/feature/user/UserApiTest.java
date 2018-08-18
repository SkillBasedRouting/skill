package com.routing.skillservice.test.feature.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Set;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.routing.skillservice.client.SkillServiceClient;
import com.routing.skillservice.dto.user.RequestUserDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.test.TestDeployment;
import com.routing.skillservice.test.mock.UserBuilder;

@RunWith(Arquillian.class)
public class UserApiTest {

	private static SkillServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {
		return new TestDeployment().build();
	}

	public UserBuilder defaultBuilder() {
		return UserBuilder.defaultBuilder();
	}

	@BeforeClass
	public static void init() {
		client = new SkillServiceClient("http://localhost:8080");
	}

	@Test
	@RunAsClient
	public void should_CreateUser_WithId_On_Post() {

		final UserBuilder builder = this.defaultBuilder();

		final RequestUserDTO request = builder.buildRequestDTO();
		final UserDTO user = client.createUser(request);

		assertEquals(request.getId(), user.getId());
	}

	@Test
	@RunAsClient
	public void should_CreateUser_WithEmptyShapes_On_Post() {

		final UserBuilder builder = this.defaultBuilder();

		final RequestUserDTO request = builder.buildRequestDTO();
		final UserDTO user = client.createUser(request);

		assertNotNull(user.getShapes());
		assertEquals(0, user.getShapes().size());
	}

	// GET

	@Test
	@RunAsClient
	public void should_GetUser_On_Get() {

		final UserDTO user = client.createUser(this.defaultBuilder().buildRequestDTO());

		final UserDTO getUser = client.getUser(user.getId());

		assertEquals(user, getUser);
	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExist_On_Get() {

		try {
			client.getUser(UUID.randomUUID().toString());
			fail("can get user that does not exist");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	// PUT

	@Test
	@RunAsClient
	public void should_UpdateUser_On_Put() {

		final UserBuilder builder = this.defaultBuilder();
		final UserDTO user = client.createUser(builder.buildRequestDTO());

		final String newId = UUID.randomUUID().toString();
		builder.setId(newId);

		final UserDTO updatedUser = client.updateUser(user.getId(), builder.buildEditDTO());

		assertEquals(newId, updatedUser.getId());
	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExist_On_Put() {

		final UserBuilder builder = this.defaultBuilder();

		try {
			client.updateUser(UUID.randomUUID().toString(), builder.buildEditDTO());
			fail("can update user that does not exist");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	// PATCH

	@Test
	@RunAsClient
	public void should_UpdateUser_On_Patch() {

		final UserBuilder builder = this.defaultBuilder();
		final UserDTO user = client.createUser(builder.buildRequestDTO());

		final String newId = UUID.randomUUID().toString();
		builder.setId(newId);

		final UserDTO updatedUser = client.patchUser(user.getId(), builder.buildEditDTO());

		assertEquals(newId, updatedUser.getId());
	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExist_On_Patch() {

		final UserBuilder builder = this.defaultBuilder();

		try {
			client.patchUser(UUID.randomUUID().toString(), builder.buildEditDTO());
			fail("can patch user that does not exist");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	// DELETE

	@Test
	@RunAsClient
	public void should_DeleteUser_On_Delete() {

		final UserDTO user = client.createUser(this.defaultBuilder().buildRequestDTO());

		client.removeUser(user.getId());
	}

	@Test
	@RunAsClient
	public void should_Return404_When_UserDoesNotExist_On_Delete() {

		try {
			client.removeUser(UUID.randomUUID().toString());
			fail("can remove user that does not exist");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	// GET ALL

	@Test
	@RunAsClient
	public void should_ReturnAllUsers_On_Get() {

		final UserDTO user = client.createUser(this.defaultBuilder().buildRequestDTO());

		final Set<UserDTO> users = client.getUsers();

		assertTrue(users.contains(user));

	}

}
