package com.routing.skillservice.api.error;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.routing.skillservice.exception.SkillServiceErrorResponse;
import com.routing.skillservice.exception.SkillServiceException;
import org.jboss.logging.Logger;

/**
 * <b>com.routing.skillservice.api.error.RuntimeExceptionMapper</b>
 * <p>
 *   catches all exceptions and returns them as json to client
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<SkillServiceException> {

	@Inject
	private HttpStatusCodeMapper statusCodeMapper;

	@Inject
	private Logger logger;

	@Override
	public Response toResponse(SkillServiceException e) {

		this.logger.warn("error at api level: " + e.toString());

		final Integer status = this.statusCodeMapper.getStatus(e);

		final SkillServiceErrorResponse respEntity = this.map(e);

		return Response.status(status).entity(respEntity).type(MediaType.APPLICATION_JSON).build();
	}

	private SkillServiceErrorResponse map(final SkillServiceException e) {
		final SkillServiceErrorResponse resp = new SkillServiceErrorResponse();
		resp.setMessage(e.getMessage());
			resp.setReasonCode(e.getReasonCode());
		
		return resp;
	}

}
