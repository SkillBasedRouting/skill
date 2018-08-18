package com.routing.skillservice.test.builder;

import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.dto.shape.request.RequestShapeDTO;
import com.routing.skillservice.feature.shape.model.Shape;

public abstract class ShapeBuilder<E> {

	public abstract ShapeBuilder<E> setValue(final E value);

	public abstract ShapeBuilder<E> setId(final String id);

	public abstract ShapeBuilder<E> setUser(final String user);

	public abstract Shape build();

	public abstract ShapeDTO buildDTO();

	public abstract RequestShapeDTO buildRequestDTO();

}
