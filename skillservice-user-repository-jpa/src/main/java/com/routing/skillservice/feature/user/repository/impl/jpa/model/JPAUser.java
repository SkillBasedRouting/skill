package com.routing.skillservice.feature.user.repository.impl.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.routing.skillservice.feature.user.repository.exception.UserRepositoryException;
import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser</b>
 * <p>
 *   jpa representation of {@link User}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_JPAUser")
public class JPAUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer internId;

	@Column(name = "userId", unique = true)
	private String id;

	@Column(name = "name")
	private String name;

	public JPAUser() {

	}

	public JPAUser(final JPAUser jpaUser) {
		this.internId = jpaUser.internId;
		this.id = jpaUser.id;
		this.name = jpaUser.name;
	}

	public JPAUser(final User user) {
		this.id = user.getId();
		this.name = user.getName();
	}
	
	public User user() {
		final User user = new User();
		user.setId(this.getId());
		user.setName(this.getName());
		return user;
	}

	public void update(final JPAUser newValues) {

		if (null != newValues.getId()) {
			this.id = newValues.getId();
		} else {
			throw UserRepositoryException.ID_REQUIRED;
		}

		this.name = newValues.name;
	}

	public void patch(final JPAUser newValues) {

		if (null != newValues.getId()) {
			this.id = newValues.getId();
		}

		if (null != newValues.getName()) {
			this.name = newValues.getName();
		}

	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((internId == null) ? 0 : internId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		JPAUser other = (JPAUser) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [internId=");
		builder.append(internId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}