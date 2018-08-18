package com.routing.skillservice.feature.shape.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.routing.skillservice.dto.shape.BooleanShapeDTO;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.skill.model.BooleanSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.shape.model.BooleanShape</b>
 * <p>
 *   entity that represents a shape a {@link User} has in a specific {@link BooleanShape}
 *   possible values are true or false
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_BooleanShape")
public class BooleanShape extends Shape {

	@Column(name = "value")
	private Boolean booleanValue;

	@ManyToOne(fetch = FetchType.EAGER)
	private BooleanSkill booleanSkill;

    /**
     * map this shape to {@link BooleanShapeDTO}
     * @return mapped shape
     */
	@Override
	public BooleanShapeDTO dto() {
		final BooleanShapeDTO dto = new BooleanShapeDTO();
		super.dto(dto);
		dto.setValue(this.booleanValue);
		if (null != this.booleanSkill) {
			dto.setSkillKey(this.booleanSkill.getKey());
		}
		return dto;
	}

    /**
     * validate this shape
     * @throws {@link ShapeException} if validation error occurs
     */
	@Override
	public void validate() {

		if (null == this.booleanValue) {
			throw ShapeException.VALUE_REQUIRED;
		}

		if (null == this.booleanSkill) {
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
		final Root<BooleanShape> booleanShape = builder.treat(shape, BooleanShape.class);

		return builder.and(builder.equal(booleanShape.get(BooleanShape_.booleanSkill), this.getBooleanSkill()),
				builder.equal(booleanShape.get(BooleanShape_.booleanValue), this.getBooleanValue()));
	}

	@Override
	public Skill getSkill() {
		return this.getBooleanSkill();
	}

	public Boolean getBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(Boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public BooleanSkill getBooleanSkill() {
		return booleanSkill;
	}

	public void setBooleanSkill(BooleanSkill booleanSkill) {
		this.booleanSkill = booleanSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((booleanSkill == null) ? 0 : booleanSkill.hashCode());
		result = prime * result + ((booleanValue == null) ? 0 : booleanValue.hashCode());
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
		BooleanShape other = (BooleanShape) obj;
		if (booleanSkill == null) {
			if (other.booleanSkill != null)
				return false;
		} else if (!booleanSkill.equals(other.booleanSkill))
			return false;
		if (booleanValue == null) {
			if (other.booleanValue != null)
				return false;
		} else if (!booleanValue.equals(other.booleanValue))
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
		builder.append("BooleanShape [booleanValue=");
		builder.append(booleanValue);
		builder.append(", booleanSkill=");
		builder.append(booleanSkill);
		builder.append("]");
		return builder.toString();
	}

}
