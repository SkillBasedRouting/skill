package com.routing.skillservice.logging;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * <b>com.routing.skillservice.logging.LoggingProducer</b>
 * <p>
 *   produces a logger over cdi
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class LoggingProducer {

	private static final String DEFAULT_CATEGORY = "com.routing.skillservice";
	private Logger logger;

	@Produces
	public Logger getLogger(final InjectionPoint injectionPoint) {
		if (null != injectionPoint && null != injectionPoint.getMember()) {
			return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
		} else {
			return Logger.getLogger(DEFAULT_CATEGORY);
		}
	}

}
