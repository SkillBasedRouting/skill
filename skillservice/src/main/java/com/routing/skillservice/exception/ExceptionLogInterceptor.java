package com.routing.skillservice.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * <b>com.routing.skillservice.exception.ExceptionLogInterceptor</b>
 * <p>
 *   interceptor that logs all exceptions and throws generic error on all unexpected errors
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class ExceptionLogInterceptor {

	@Inject
	private Logger logger;

	@AroundInvoke
	public Object mdbInterceptor(InvocationContext ctx) throws Exception {
		try {
			return ctx.proceed();
		} catch (SkillServiceException e) {
			this.logger.error("error: " + e);
			throw e;
		} catch (Exception e) {
			this.logger.error("unexpected error: " + e);
			e.printStackTrace();
			throw SkillServiceException.GENERIC_ERROR;
		}
	}

}
