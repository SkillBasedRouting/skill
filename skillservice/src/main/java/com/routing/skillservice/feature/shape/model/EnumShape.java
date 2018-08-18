package com.routing.skillservice.feature.shape.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.routing.skillservice.dto.shape.EnumShapeDTO;
import com.routing.skillservice.feature.shape.exception.ShapeException;
import com.routing.skillservice.feature.skill.model.EnumSkill;
import com.routing.skillservice.feature.skill.model.Skill;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.shape.model.BooleanShape</b>
 * <p>
 *   entity that represents a shape a {@link User} has in a specific {@link EnumSkill}
 *   possible values are the values in {@link EnumSkill}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
 **/
@Entity
@Table(name = "SkillService_EnumShape")
public class EnumShape extends Shape {

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> enumValue;

	@ManyToOne(fetch = FetchType.EAGER)
	private EnumSkill enumSkill;

	/**
	 * map this shape to {@link EnumShapeDTO}
	 * @return mapped shape
	 */
	@Override
	public EnumShapeDTO dto() {
		final EnumShapeDTO dto = new EnumShapeDTO();
		super.dto(dto);
		dto.setValue(this.enumValue);
		if (null != this.enumSkill) {
			dto.setSkillKey(this.enumSkill.getKey());
		}
		return dto;
	}

	/**
	 * validate this shape
	 * @throws {@link ShapeException} if validation error occurs
	 */
	@Override
	public void validate() {

		if (null == this.enumValue) {
			throw ShapeException.VALUE_REQUIRED;
		}

		if (null == this.enumSkill) {
			throw ShapeException.SKILL_REQUIRED;
		}

		this.validateValue();
	}

	private void validateValue() {
		this.enumValue.forEach(value -> {
			if (!this.enumSkill.getValue().contains(value)) {
				throw ShapeException.VALUE_INVALID;
			}
		});
	}

    /**
     * returns a jpa criteria api assertion of this shape
     * @param builder current criteria builder of the query
     * @param shape query root
     * @return query assertions
     */
	@Override
	public Predicate assertion(final CriteriaBuilder builder, final Root<Shape> shape) {
		final Root<EnumShape> enumShape = builder.treat(shape, EnumShape.class);

		return builder.and(builder.equal(enumShape.get(EnumShape_.enumSkill), this.getEnumSkill()),
				this.valueAssertion(builder, enumShape));
	}

	private Predicate valueAssertion(final CriteriaBuilder builder, final Root<EnumShape> enumShape) {

		Predicate pre = null;

		for (String value : this.getEnumValue()) {
			Predicate valuePre = builder.isMember(value, enumShape.get(EnumShape_.enumValue));
			if (null == pre) {
				pre = valuePre;
			} else {
				pre = builder.and(pre, valuePre);
			}
		}

		return pre;
	}

	@Override
	public Skill getSkill() {
		return this.getEnumSkill();
	}

	public Set<String> getEnumValue() {
		return enumValue;
	}

	public void setEnumValue(Set<String> enumValue) {
		this.enumValue = enumValue;
	}

	public EnumSkill getEnumSkill() {
		return enumSkill;
	}

	public void setEnumSkill(EnumSkill enumSkill) {
		this.enumSkill = enumSkill;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((enumSkill == null) ? 0 : enumSkill.hashCode());
		result = prime * result + ((enumValue == null) ? 0 : enumValue.hashCode());
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
		EnumShape other = (EnumShape) obj;
		if (enumSkill == null) {
			if (other.enumSkill != null)
				return false;
		} else if (!enumSkill.equals(other.enumSkill))
			return false;
		if (enumValue == null) {
			if (other.enumValue != null)
				return false;
		} else if (!enumValue.equals(other.enumValue))
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
		builder.append("EnumShape [enumValue=");
		builder.append(enumValue);
		builder.append(", enumSkill=");
		builder.append(enumSkill);
		builder.append("]");
		return builder.toString();
	}

}
