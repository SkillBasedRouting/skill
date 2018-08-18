package com.routing.skillservice.api.error;

import com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException;
import com.routing.skillservice.feature.group.repository.exception.GroupRepositoryException;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.shape.exception.ShapeNotFoundException;
import com.routing.skillservice.feature.skill.exception.SkillException;
import com.routing.skillservice.feature.skill.exception.SkillNotFoundException;
import com.routing.skillservice.feature.user.repository.exception.UserNotFoundException;
import com.routing.skillservice.feature.user.repository.exception.UserRepositoryException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * <b>com.routing.skillservice.api.error.HttpStatusCodeMapper</b>
 * <p>
 * maps exception to http status codes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@ApplicationScoped
public class HttpStatusCodeMapper {

    private Map<RuntimeException, Integer> refCodes;
    private Map<Class<? extends RuntimeException>, Integer> typeCodes;

    @PostConstruct
    private void init() {

        this.refCodes = new HashMap<>(20);
        this.typeCodes = new HashMap<>(10);

        // UserException

        this.refCodes.put(UserRepositoryException.REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(UserRepositoryException.ID_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(UserRepositoryException.ID_CONFLICT, Response.Status.CONFLICT.getStatusCode());
        this.typeCodes.put(UserNotFoundException.class, Response.Status.NOT_FOUND.getStatusCode());

        // GroupException

        this.refCodes.put(GroupRepositoryException.REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.typeCodes.put(GroupNotFoundException.class, Response.Status.NOT_FOUND.getStatusCode());

        // SkillException

        this.refCodes.put(SkillException.REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(SkillException.LABEL_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(SkillException.KEY_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(SkillException.VALUE_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.typeCodes.put(SkillNotFoundException.class, Response.Status.NOT_FOUND.getStatusCode());

        // ShapeException

        this.refCodes.put(ShapeException.REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(ShapeException.SKILL_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(ShapeException.USER_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(ShapeException.VALUE_REQUIRED, Response.Status.BAD_REQUEST.getStatusCode());
        this.refCodes.put(ShapeException.QUERY_NOT_FOUND, Response.Status.NOT_FOUND.getStatusCode());
        this.refCodes.put(ShapeException.UNKNOWN_TYPE, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        this.typeCodes.put(ShapeNotFoundException.class, Response.Status.NOT_FOUND.getStatusCode());
    }

    /**
     * Get http status code of exception, if not specified 500 is returned
     *
     * @param e the occured exception
     * @return http status code
     */
    public Integer getStatus(final RuntimeException e) {

        Integer status = this.refCodes.get(e);
        if (null == status) {
            status = this.typeCodes.get(e.getClass());
        }
        if (null == status) {
            status = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }

        return status;
    }

}
