package com.routing.skillservice.feature.query;

import java.util.HashSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.skillservice.dto.query.QueryRequestDTO;
import com.routing.skillservice.feature.shape.boundary.ShapeMapper;

/**
 * <b>com.routing.skillservice.feature.query.QueryMapper</b>
 * <p>
 *   mapper to transform {@link QueryRequestDTO} to {@link QueryRequest}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class QueryMapper {

	@Inject
	private ShapeMapper shapeMapper;

	/**
	 * transform {@link QueryRequestDTO} to {@link QueryRequest}
	 * @param queryRequestDTO request to map
	 * @return mapped request
	 */
	public QueryRequest toModel(final QueryRequestDTO queryRequestDTO) {

		if (null == queryRequestDTO) {
			return null;
		}

		final QueryRequest queryRequest = new QueryRequest();
		if (null != queryRequestDTO.getGroups()) {
			queryRequest.setGroups(queryRequestDTO.getGroups());
		} else {
			queryRequest.setGroups(new HashSet<>(0));
		}

		if (null != queryRequestDTO.getShapes()) {
			queryRequest.setShapes(this.shapeMapper.toModel(queryRequestDTO.getShapes()));
		} else {
			queryRequest.setShapes(new HashSet<>(0));
		}

		return queryRequest;
	}

}
