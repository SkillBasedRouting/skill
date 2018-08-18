package com.routing.skillservice.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

/**
 * <b>com.routing.skillservice.api.ApiActivator</b>
 * <p>
 *   JAX-RS activator for api
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationPath("/api/v1")
public class ApiActivator extends Application {

	public ApiActivator() {

	    // config for swagger doc
		final BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("SkillService");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost("routing.schwenkschuster.com");
		beanConfig.setBasePath("skillservice/api/v1");
		beanConfig.setScan(true);
		beanConfig.setResourcePackage("com.routing.skillservice");
		beanConfig.setContact("Arndt Schwenkschuster (arndt@schwenkschuster.de)");

	}

}
