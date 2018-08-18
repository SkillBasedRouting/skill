package com.routing.skillservice.test.feature.shape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import com.routing.skillservice.dto.shape.BooleanShapeDTO;
import com.routing.skillservice.dto.shape.EnumShapeDTO;
import com.routing.skillservice.dto.shape.IntegerShapeDTO;
import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.skill.BooleanSkillDTO;
import com.routing.skillservice.dto.skill.EnumSkillDTO;
import com.routing.skillservice.dto.skill.IntegerSkillDTO;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.test.TestDeployment;
import com.routing.skillservice.test.builder.BooleanShapeBuilder;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumShapeBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerShapeTestBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;

@RunWith(Arquillian.class)
public class ShapeApiTest extends ShapeTest {

	private static SkillServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {
		return new TestDeployment().build();
	}

	@BeforeClass
	public static void init() {
		client = new SkillServiceClient("http://localhost:8080");
	}

	@Override
	public BooleanShapeBuilder defaultBooleanShapeBuilder() {
		return super.defaultBooleanShapeBuilder().setSkill(this.booleanSkill());
	}

	@Override
	public IntegerShapeTestBuilder defaultIntegerShapeBuilder() {
		return super.defaultIntegerShapeBuilder().setSkill(this.integerSkill());
	}

	@Override
	public EnumShapeBuilder defaultEnumShapeBuilder() {
		return super.defaultEnumShapeBuilder().setSkill(this.enumSkill());
	}

	public String user() {
		return UUID.randomUUID().toString();
	}

	public BooleanSkill booleanSkill() {
		final BooleanSkillDTO skillDTO = (BooleanSkillDTO) client
				.createSkill(BooleanSkillBuilder.defaultBuilder().buildRequestDTO());
		final BooleanSkillBuilder builder = new BooleanSkillBuilder().setKey(skillDTO.getKey());
		return builder.build();
	}

	public IntegerSkill integerSkill() {
		final IntegerSkillDTO skillDTO = (IntegerSkillDTO) client
				.createSkill(IntegerSkillBuilder.defaultBuilder().buildRequestDTO());
		final IntegerSkillBuilder builder = new IntegerSkillBuilder().setKey(skillDTO.getKey());
		return builder.build();
	}

	public EnumSkill enumSkill() {
		final EnumSkillDTO skillDTO = (EnumSkillDTO) client
				.createSkill(EnumSkillBuilder.defaultBuilder().buildRequestDTO());
		final EnumSkillBuilder builder = new EnumSkillBuilder().setKey(skillDTO.getKey());
		return builder.build();
	}

	// Post

	@Test
	@RunAsClient
	public void should_CreateBooleanShape_On_Post() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		builder.setSkill(booleanSkill());
		builder.setUser(user());

		final ShapeDTO shape = client.createShape(builder.buildRequestDTO());

		assertTrue(shape instanceof BooleanShapeDTO);
		assertNotNull(shape.getId());
	}

	@Test
	@RunAsClient
	public void should_CreateIntegerShape_On_Post() {

		final IntegerShapeTestBuilder builder = this.defaultIntegerShapeBuilder();
		builder.setSkill(integerSkill());
		builder.setUser(user());

		final ShapeDTO shape = client.createShape(builder.buildRequestDTO());

		assertTrue(shape instanceof IntegerShapeDTO);
		assertNotNull(shape.getId());
	}

	@Test
	@RunAsClient
	public void should_CreateEnumShape_On_Post() {

		final EnumShapeBuilder builder = this.defaultEnumShapeBuilder();
		builder.setSkill(enumSkill());
		builder.setUser(user());

		final ShapeDTO shape = client.createShape(builder.buildRequestDTO());

		assertTrue(shape instanceof EnumShapeDTO);
		assertNotNull(shape.getId());
	}

	@Test
	@RunAsClient
	public void should_Return400_When_ShapeValueIsInvalid_On_Post() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		builder.setValue(null);
		builder.setUser(user());

		try {
			client.createShape(builder.buildRequestDTO());
			fail("can post shape with invalid value");
		} catch (WebApplicationException e) {
			assertEquals(400, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_Return400_When_ShapeSkillIsInvalid_On_Post() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		builder.setSkill(null);
		builder.setUser(user());

		try {
			client.createShape(builder.buildRequestDTO());
			fail("can post shape with invalid skill");
		} catch (WebApplicationException e) {
			assertEquals(400, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_Return400_When_ShapeUserIsInvalid_On_Post() {

		final BooleanShapeBuilder builder = this.defaultBooleanShapeBuilder();
		builder.setSkill(booleanSkill());
		builder.setValue(true);
		builder.setUser(null);

		try {
			client.createShape(builder.buildRequestDTO());
			fail("can post shape with invalid user");
		} catch (WebApplicationException e) {
			assertEquals(400, e.getResponse().getStatus());
		}

	}

	@Test
	@RunAsClient
	public void should_Return400_When_ShapeIsNull_On_Post() {

		try {
			client.createShape(null);
			fail("can post shape with null shape");
		} catch (WebApplicationException e) {
			assertEquals(400, e.getResponse().getStatus());
		}

	}

	// Get

	@Test
	@RunAsClient
	public void should_ReturnShape_On_Get() {

		final ShapeDTO dto = client.createShape(this.defaultIntegerShapeBuilder().setUser(user()).buildRequestDTO());

		final ShapeDTO dtoGet = client.getShape(dto.getId());

		assertEquals(dto, dtoGet);
	}

	@Test
	@RunAsClient
	public void should_Return404_When_ShapeDoesNotExist_On_Get() {

		try {
			client.getShape(UUID.randomUUID().toString());
			fail("can get non existing shape");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}

	}

}
