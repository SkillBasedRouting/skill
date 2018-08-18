package com.routing.skillservice.feature.shape.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.routing.skillservice.dto.shape.IntegerShapeDTO;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.skill.model.IntegerSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.shape.model.BooleanShape</b>
 * <p>
 *   entity that represents a shape a {@link User} has in a specific {@link IntegerSkill}
 *   possible values are all integer values
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Entity
@Table(name = "SkillService_IntegerShape")
public class IntegerShape extends Shape {

	@Column(name = "value")
	private Integer integerValue;

	@ManyToOne(fetch = FetchType.EAGER)
	private IntegerSkill integerSkill;

    /**
     * map this shape to {@link IntegerShapeDTO}
     * @return mapped shape
     */
	@Override
	public IntegerShapeDTO dto() {
		final IntegerShapeDTO dto = new IntegerShapeDTO();
		super.dto(dto);
		dto.setValue(this.integerValue);
		if (null != this.integerSkill) {
			dto.setSkillKey(this.integerSkill.getKey());
		}
		return dto;
	}

    /**
     * validate this shape
     * @throws {@link ShapeException} if validation error occurs
     */
	@Override
	public void validate() {

		if (null == this.integerValue) {
			throw ShapeException.VALUE_REQUIRED;
		}

		if (null == this.integerSkill) {
			throw ShapeException.SKILL_REQUIRED;
		}

	}

	/**
	 * returns a jpa criteria api assertion of this shape
	 * @param builder current criteria builder of the query
	 * @param shape query root
	 * @return query assertions
	 */
	@Override
	public Predicate assertion(final CriteriaBuilder builder, final Root<Shape> shape) {

		final Root<IntegerShape> integerShape = builder.treat(shape, IntegerShape.class);

		return builder.and(builder.equal(integerShape.get(IntegerShape_.integerSkill), this.getIntegerSkill()),
				builder.greaterThanOrEqualTo(integerShape.get(IntegerShape_.integerValue), this.getIntegerValue()));
	}

	@Override
	public Skill getSkill() {
		return this.getIntegerSkill();
	}

	public Integer getIntegerValue() {
		return integerValue;
	}

	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}

	public IntegerSkill getIntegerSkill() {
		return integerSkill;
	}

	public void setIntegerSkill(IntegerSkill integerSkill) {
		this.integerSkill = integerSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((integerSkill == null) ? 0 : integerSkill.hashCode());
		result = prime * result + ((integerValue == null) ? 0 : integerValue.hashCode());
		result = prime * result + ((super.getId() == null) ? 0 : super.getId().hashCode());
		result = prime * result + ((super.getInternId() == null) ? 0 : super.getInternId().hashCode());
		result = prime * result + ((super.getUser() == null) ? 0 : super.getUser().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		IntegerShape other = (IntegerShape) obj;
		if (integerSkill == null) {
			if (other.integerSkill != null)
				return false;
		} else if (!integerSkill.equals(other.integerSkill))
			return false;
		if (integerValue == null) {
			if (other.integerValue != null)
				return false;
		} else if (!integerValue.equals(other.integerValue))
			return false;
		if (super.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!super.getId().equals(other.getId()))
			return false;
		if (super.getInternId() == null) {
			if (other.getInternId() != null)
				return false;
		} else if (!super.getInternId().equals(other.getInternId()))
			return false;
		if (super.getUser() == null) {
			if (other.getUser() != null)
				return false;
		} else if (!super.getUser().equals(other.getUser()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntegerShape [integerValue=");
		builder.append(integerValue);
		builder.append(", integerSkill=");
		builder.append(integerSkill);
		builder.append("]");
		return builder.toString();
	}

}
