package com.routing.skillservice.feature.skill.resource;

import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.spi.HttpResponse;
import org.jboss.resteasy.util.HttpResponseCodes;

import com.routing.skillservice.dto.skill.SkillDTO;
import com.routing.skillservice.dto.skill.request.RequestSkillDTO;
import com.routing.skillservice.feature.skill.boundary.SkillBoundary;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <b>com.routing.skillservice.feature.group.resource.SkillResource</b>
 * <p>
 *   JAX-RS endpoint for all skill methods
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Path("/skills")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(tags = { "Skill" })
public class SkillResource {

	@Inject
	private SkillBoundary skillBoundary;

	@Context
	private HttpResponse httpResponse;

	@GET
	@Path("/{key}")
	@ApiOperation("get a skill")
	public SkillDTO get(@ApiParam("the key of the skill") @PathParam("key") final String key) {
		return this.skillBoundary.get(key);
	}

	@POST
	@Path("/")
	@ApiOperation("create a skill")
	public SkillDTO create(
			@ApiParam("the skill to create, see objects extending RequestSkillDTO for possible types") final RequestSkillDTO requestSkillDTO) {

		final SkillDTO createdSkill = this.skillBoundary.create(requestSkillDTO);

		this.httpResponse.setStatus(HttpResponseCodes.SC_CREATED);

		return createdSkill;
	}

	@GET
	@Path("/")
	@ApiOperation("get all skills")
	public Set<SkillDTO> getAll() {
		return this.skillBoundary.getAll();
	}

	@POST
	@Path("/multipliers")
	@ApiOperation("get multipliers")
	public Map<String, Double> getMultipliers(@ApiParam("list of skill keys") final Set<String> keys) {
		return this.skillBoundary.getMultipliers(keys);
	}

}
