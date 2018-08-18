package com.routing.skillservice.test.feature.query;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.routing.skillservice.client.SkillServiceClient;
import com.routing.skillservice.dto.group.GroupDTO;
import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.user.UserDTO;
import com.routing.skillservice.dto.user.UserShapeDTO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.test.TestDeployment;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.GroupBuilder;
import com.routing.skillservice.test.mock.UserBuilder;

@RunWith(Arquillian.class)
public class QueryApiTest {

	private static SkillServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {
		return new TestDeployment().build();
	}

	@BeforeClass
	public static void init() {
		client = new SkillServiceClient("http://localhost:8080");
	}

	public UserDTO newUser() {
		return client.createUser(UserBuilder.defaultBuilder().buildRequestDTO());
	}

	public GroupDTO newGroup(final String... user) {
		return client.createGroup(GroupBuilder.defaultBuilder()
				.setMembers(Arrays.asList(user).stream().collect(Collectors.toSet())).buildRequestDTO());
	}

	public SkillDTO newSkill() {
		return client.createSkill(BooleanSkillBuilder.defaultBuilder().buildRequestDTO());
	}

	public ShapeDTO newShape(final String user, final SkillDTO skill) {
		return client.createShape(this.shapeBuilderFromSkillAndUser(user, skill).buildRequestDTO());
	}

	public BooleanShapeBuilder shapeBuilderFromSkillAndUser(final String user, final SkillDTO skill) {
		final BooleanSkill booleanSkill = BooleanSkillBuilder.defaultBuilder().setKey(skill.getKey()).build();
		return BooleanShapeBuilder.defaultBuilder().setUser(user).setSkill(booleanSkill).setValue(true);
	}

	@Test
	@RunAsClient
	public void should_ReturnUsers_ThatMatch_Group() {

		final UserDTO user1 = newUser();
		newUser();
		final GroupDTO group1 = newGroup(user1.getId());

		final QueryRequestDTO request = new QueryRequestDTO();
		request.setGroups(Arrays.asList(group1.getId()).stream().collect(Collectors.toSet()));

		final Set<UserShapeDTO> result = client.query(request);

		assertEquals(1, result.size());
		assertEquals(user1.getId(), result.iterator().next().getUserId());
	}

	@Test
	@RunAsClient
	public void should_ReturnUsers_ThatMatch_Shapes() {

		final UserDTO user1 = newUser();
		newUser();
		final SkillDTO skill1 = newSkill();
		newShape(user1.getId(), skill1);

		final QueryRequestDTO request = new QueryRequestDTO();
		request.setShapes(new HashSet<>(1));
		request.getShapes().add(this.shapeBuilderFromSkillAndUser(user1.getId(), skill1).buildRequestDTO());

		final Set<UserShapeDTO> result = client.query(request);

		assertEquals(1, result.size());
		assertEquals(user1.getId(), result.iterator().next().getUserId());
	}

	@Test
	@RunAsClient
	public void should_ReturnUsers_ThatMatch_ShapesAndGroups() {

		final UserDTO user1 = newUser();
		final UserDTO user2 = newUser();
		final SkillDTO skill1 = newSkill();
		newShape(user1.getId(), skill1);
		newShape(user2.getId(), skill1);
		final GroupDTO group1 = newGroup(user1.getId());

		final QueryRequestDTO request = new QueryRequestDTO();
		request.setShapes(new HashSet<>(1));
		request.getShapes().add(this.shapeBuilderFromSkillAndUser(user1.getId(), skill1).buildRequestDTO());
		request.setGroups(new HashSet<>(1));
		request.getGroups().add(group1.getId());

		final Set<UserShapeDTO> result = client.query(request);

		assertEquals(1, result.size());
		assertEquals(user1.getId(), result.iterator().next().getUserId());
	}

};