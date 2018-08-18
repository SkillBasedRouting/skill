package com.routing.skillservice.feature.shape.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.routing.skillservice.dto.shape.ShapeDTO;
import com.routing.skillservice.feature.skill.model.Skill;

/**
 * <b>com.routing.skillservice.feature.shape.model.Shape</b>
 * <p>
 *   base class for all derived shapes
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_Shape")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shape {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer internId;

	@Column(name = "id", unique = true)
	private String id;

	@Column(name = "user")
	private String user;

	public abstract ShapeDTO dto();

    /**
     * copy all values of this to given {@link ShapeDTO}
     * @param shapeDTO where the values are copied to
     */
	public void dto(final ShapeDTO shapeDTO) {
		shapeDTO.setId(id);
	}

    /**
     * Validate this shape
     */
	public abstract void validate();

	public abstract Skill getSkill();

    /**
     * returns a jpa criteria api assertion of this shape
     * @param builder current criteria builder of the query
     * @param shape query root
     * @return query assertions
     */
	public abstract Predicate assertion(final CriteriaBuilder builder, final Root<Shape> shape);

	public Integer getInternId() {
		return internId;
	}

	public void setInternId(Integer internId) {
		this.internId = internId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((internId == null) ? 0 : internId.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shape other = (Shape) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (internId == null) {
			if (other.internId != null)
				return false;
		} else if (!internId.equals(other.internId))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Shape [internId=");
		builder.append(internId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}

}
