package com.routing.skillservice.test.feature.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.routing.skillservice.client.SkillServiceClient;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.group.RequestGroupDTO;
import com.routing.skillservice.test.TestDeployment;
import com.routing.skillservice.test.builder.GroupBuilder;

@RunWith(Arquillian.class)
public class GroupApiTest {

	private static SkillServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {
		return new TestDeployment().build();
	}

	public static void main(String[] args) {

		final File target = new File("/home/arndt/test/test.war");
		if (target.exists()) {
			target.delete();
		}
		GroupApiTest.createDeployment().as(ZipExporter.class).exportTo(target);
		System.out.println("done");

	}

	public GroupBuilder defaultBuilder() {
		return GroupBuilder.defaultBuilder();
	}

	@BeforeClass
	public static void init() {
		client = new SkillServiceClient("http://localhost:8080");
	}

	@Test
	@RunAsClient
	public void should_CreateGroup_On_Post() {

		final GroupBuilder builder = this.defaultBuilder();

		final RequestGroupDTO request = builder.buildRequestDTO();
		final GroupDTO group = client.createGroup(request);

		assertEquals(request.getId(), group.getId());
		assertEquals(request.getName(), group.getName());
		assertEquals(request.getMembers(), group.getMembers());
	}

	@Test
	@RunAsClient
	public void should_GetGroup_On_Get() {

		final GroupBuilder builder = this.defaultBuilder();
		final GroupDTO createdGroup = client.createGroup(builder.buildRequestDTO());

		final GroupDTO group = client.getGroup(createdGroup.getId());

		assertEquals(createdGroup, group);
	}

	@Test
	@RunAsClient
	public void should_Return404_WhenGroupDoesNotExist_On_Get() {

		try {
			client.getGroup(UUID.randomUUID().toString());
			fail();
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_UpdateGroup_On_Put() {

		final GroupBuilder builder = this.defaultBuilder();
		final GroupDTO createdGroup = client.createGroup(builder.buildRequestDTO());

		final String newName = "newLabel";
		builder.setName(newName);
		final GroupDTO group = client.updateGroup(createdGroup.getId(), builder.buildEditDTO());

		assertEquals(newName, group.getName());
	}

	@Test
	@RunAsClient
	public void should_PatchGroup_On_Put() {

		final GroupBuilder builder = this.defaultBuilder();
		final GroupDTO createdGroup = client.createGroup(builder.buildRequestDTO());

		final String oldName = createdGroup.getName();
		builder.setName(null);
		final GroupDTO group = client.patchGroup(createdGroup.getId(), builder.buildEditDTO());

		assertEquals(oldName, group.getName());
	}

	@Test
	@RunAsClient
	public void should_DeleteGroup_On_Delete() {

		final GroupBuilder builder = this.defaultBuilder();
		final GroupDTO createdGroup = client.createGroup(builder.buildRequestDTO());

		client.removeGroup(createdGroup.getId());

		try {
			client.getGroup(createdGroup.getId());
			fail();
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}
	}

}
