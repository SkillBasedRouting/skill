package com.routing.skillservice.test.feature.skill;

import static org.junit.Assert.assertEquals;
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
import com.routing.skillservice.dto.skill.BooleanSkillDTO;
import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestBooleanSkillDTO;
import com.routing.skillservice.test.TestDeployment;
import com.routing.skillservice.test.builder.BooleanSkillBuilder;
import com.routing.skillservice.test.builder.EnumSkillBuilder;
import com.routing.skillservice.test.builder.IntegerSkillBuilder;

@RunWith(Arquillian.class)
public class SkillApiTest {

	private static SkillServiceClient client;

	@Deployment
	public static WebArchive createDeployment() {
		return new TestDeployment().build();
	}

	@BeforeClass
	public static void init() {
		client = new SkillServiceClient("http://localhost:8080");
	}

	public BooleanSkillBuilder defaultBooleanBuilder() {
		return BooleanSkillBuilder.defaultBuilder();
	}

	public IntegerSkillBuilder defaultIntegerBuilder() {
		return IntegerSkillBuilder.defaultBuilder();
	}

	public EnumSkillBuilder defaultEnumBuilder() {
		return EnumSkillBuilder.defaultBuilder();
	}

	// POST

	@Test
	@RunAsClient
	public void should_CreateSkill_On_Post() {

		final BooleanSkillBuilder skillBuilder = this.defaultBooleanBuilder();
		final RequestBooleanSkillDTO reqSkill = skillBuilder.buildRequestDTO();

		final SkillDTO skill = client.createSkill(reqSkill);

		assertTrue(skill instanceof BooleanSkillDTO);
		assertEquals(reqSkill.getKey(), skill.getKey());
	}

	@Test
	@RunAsClient
	public void should_Return_400_When_SkillIsInvalid_On_Post() {

		final BooleanSkillBuilder skillBuilder = this.defaultBooleanBuilder();
		skillBuilder.setKey(null);
		final RequestBooleanSkillDTO reqSkill = skillBuilder.buildRequestDTO();

		try {
			client.createSkill(reqSkill);
			fail("can create invalid skill");
		} catch (WebApplicationException e) {
			assertEquals(400, e.getResponse().getStatus());
		}
	}

	// GET

	@Test
	@RunAsClient
	public void should_GetSkill_On_Get() {

		final SkillDTO skill = client.createSkill(this.defaultIntegerBuilder().buildRequestDTO());

		final SkillDTO getSkill = client.getSkill(skill.getKey());

		assertEquals(skill, getSkill);
	}

	@Test
	@RunAsClient
	public void should_Return_404_When_SkillDoesNotExist_On_Get() {

		try {
			client.getSkill(UUID.randomUUID().toString());
			fail("can get non existing skill");
		} catch (WebApplicationException e) {
			assertEquals(404, e.getResponse().getStatus());
		}
	}

	// GetAll

	@Test
	@RunAsClient
	public void should_ReturnAllSkill_On_GetAll() {

		final SkillDTO skill = client.createSkill(this.defaultEnumBuilder().buildRequestDTO());

		final Set<SkillDTO> skills = client.getSkills();

		assertTrue(skills.contains(skill));
	}

}
