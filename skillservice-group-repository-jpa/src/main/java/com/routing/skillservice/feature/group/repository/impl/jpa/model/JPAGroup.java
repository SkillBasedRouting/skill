package com.routing.skillservice.feature.group.repository.impl.jpa.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.routing.skillservice.feature.group.repository.exception.GroupRepositoryException;
import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup</b>
 * <p>
 *   jpa entity that represents a {@link Group} as its stored in db
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@Entity
@Table(name = "SkillService_JPAGroup")
public class JPAGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer internId;

	@Column(name = "group_id", unique = true)
	private String id;

	@Column
	private String name;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> members;

	public JPAGroup() {
		super();
	}

	public JPAGroup(final Group group) {
		this.id = group.getId();
		this.name = group.getName();
		this.members = group.getMembers();
	}

    /**
     * convert this to {@link Group}
     * @return this as {@link Group}
     */
	public Group group() {
		final Group group = new Group();
		group.setId(this.id);
		group.setName(this.name);
		group.setMembers(this.members);
		return group;
	}

    /**
     * update this with new values, all null values will be set if null value is allowed otherwise a exception is raised
     * @param newValues new values
     * @throws GroupRepositoryException if validation fails
     */
	public void update(final JPAGroup newValues) {

		if (null == newValues) {
			throw GroupRepositoryException.REQUIRED;
		}

		if (null == newValues.getId()) {
			throw GroupRepositoryException.ID_REQUIRED;
		} else {
			this.id = newValues.id;			
		}
		
		this.name = newValues.name;
		
		if (null == newValues.getMembers()) {
			throw GroupRepositoryException.MEMBERS_REQUIRED;
		} else {
			this.members = newValues.members;			
		}
	}

    /**
     * partial update this with new values, all null values will be ignored
     * @param newValues new values
     * @throws GroupRepositoryException if newValues is null
     */
	public void patch(final JPAGroup newValues) {

		if (null == newValues) {
			throw GroupRepositoryException.REQUIRED;
		}

		if (null != newValues.id) {
			this.id = newValues.id;
		}

		if (null != newValues.name) {
			this.name = newValues.name;
		}

		if (null != newValues.members) {
			this.members = newValues.members;
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

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((internId == null) ? 0 : internId.hashCode());
		result = prime * result + ((members == null) ? 0 : members.hashCode());
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
		JPAGroup other = (JPAGroup) obj;
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
		if (members == null) {
			if (other.members != null)
				return false;
		} else if (!members.equals(other.members))
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
		builder.append("JPAGroup [internId=");
		builder.append(internId);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", members=");
		builder.append(members);
		builder.append("]");
		return builder.toString();
	}

}