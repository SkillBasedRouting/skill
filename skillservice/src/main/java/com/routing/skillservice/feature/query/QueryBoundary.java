package com.routing.skillservice.feature.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.dto.user.UserShapeDTO;
import com.routing.skillservice.exception.ExceptionLogInterceptor;
import com.routing.skillservice.feature.group.repository.GroupRepository;
import com.routing.skillservice.feature.group.repository.model.Group;
import com.routing.skillservice.feature.shape.dao.ShapeDAO;
import com.routing.skillservice.feature.shape.model.UserShape;

/**
 * <b>com.routing.skillservice.feature.query.QueryBoundary</b>
 * <p>
 *   Boundary for querying possible users for a task
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors({ ExceptionLogInterceptor.class })
public class QueryBoundary {

	@Inject
	private QueryMapper queryMapper;

	@Inject
	private QueryDAO queryDAO;

	@Inject
	private ShapeDAO shapeDAO;

	@Inject
	private GroupRepository groupRepository;

    /**
     * Query possible users for a task
     * @param queryRequestDTO query request
     * @return list of all possible users and its shape values
     */
	public Set<UserShapeDTO> query(final QueryRequestDTO queryRequestDTO) {

		final QueryRequest queryRequest = this.queryMapper.toModel(queryRequestDTO);

		final List<String> groups = new ArrayList<>(queryRequest.getGroups());
		final Set<String> possibleUsers = new HashSet<String>();

		// prefill possible users by shapes
		if (0 != queryRequest.getShapes().size()) {
			possibleUsers.addAll(this.queryDAO.matchShapes(queryRequest.getShapes()));
		} else if (0 != groups.size()) {
			final String groupId = groups.get(0);
			possibleUsers.addAll(this.groupRepository.getGroup(groupId).getMembers());
			groups.remove(0);
		} else {
			throw new RuntimeException("no requirement");
		}

		// filter for groups
		groups.stream().forEach(groupId -> {
			final Group group = this.groupRepository.getGroup(groupId);
			possibleUsers.removeIf(user -> false == group.getMembers().contains(user));
		});

		// get information for each possible user
		return possibleUsers.stream().map(possibleUser -> {
			final UserShape userShape = new UserShape();
			userShape.setUser(possibleUser);
			userShape.setShapes(this.shapeDAO.getByUser(possibleUser));
			return userShape;
		}).map(UserShape::dto).collect(Collectors.toSet());
	}

}
