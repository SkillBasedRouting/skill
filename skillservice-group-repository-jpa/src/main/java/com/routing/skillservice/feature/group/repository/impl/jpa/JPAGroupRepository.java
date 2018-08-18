package com.routing.skillservice.feature.group.repository.impl.jpa;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.routing.skillservice.feature.group.repository.GroupRepository;
import com.routing.skillservice.feature.group.repository.exception.GroupRepositoryException;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;
import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupRepository</b>
 * <p>
 *   jpa implementation of {@link GroupRepository}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class JPAGroupRepository implements GroupRepository {

	@Inject
	protected JPAGroupDAO jpaGroupDAO;

	/**
	 * create a group
	 * @param group the group to create
	 * @return created group
	 * @throws GroupRepositoryException if group is not valid
	 * @throws GroupRepositoryException if id is already taken
	 */
	@Override
	public Group createGroup(final Group group) {

		if (null == group) {
			throw GroupRepositoryException.REQUIRED;
		} else if (null == group.getId()) {
			throw GroupRepositoryException.ID_REQUIRED;
		} else if (this.jpaGroupDAO.exists(group.getId())) {
			throw GroupRepositoryException.ID_CONFLICT;
		}

		JPAGroup jpaGroup = new JPAGroup(group);

		jpaGroup = this.jpaGroupDAO.persist(jpaGroup);

		return jpaGroup.group();
	}

	/**
	 * update a group, all null values will be set
	 * @param id id of group
	 * @param newValues new values
	 * @return updated group
	 * @throws GroupRepositoryException if id or newValues are null
	 * @throws GroupRepositoryException if id is already taken
	 * @throws com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException if group id does not exist
	 */
	@Override
	public Group updateGroup(final String id, final Group newValues) {

		if (null == newValues) {
			throw GroupRepositoryException.REQUIRED;
		} else if (null == id) {
			throw GroupRepositoryException.ID_REQUIRED;
		}

		final JPAGroup jpaNewValues = new JPAGroup(newValues);
		final JPAGroup jpaGroup = this.jpaGroupDAO.get(id);

		if (false == id.equals(newValues.getId()) && this.jpaGroupDAO.exists(newValues.getId())) {
			throw GroupRepositoryException.ID_CONFLICT;
		}

		jpaGroup.update(jpaNewValues);

		return jpaGroup.group();
	}

	/**
	 * partial update a group, all null values will be ignored
	 * @param id id of group
	 * @param newValues new values
	 * @return updated group
	 * @throws GroupRepositoryException if id or newValues are null
	 * @throws GroupRepositoryException if new id is already taken
	 * @throws com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException if group id does not exist
	 */
	@Override
	public Group patchGroup(String id, Group newValues) {

		if (null == newValues) {
			throw GroupRepositoryException.REQUIRED;
		} else if (null == id) {
			throw GroupRepositoryException.ID_REQUIRED;
		}

		final JPAGroup jpaNewValues = new JPAGroup(newValues);
		final JPAGroup jpaGroup = this.jpaGroupDAO.get(id);

		if (null != newValues.getId() && false == id.equals(newValues.getId())
				&& this.jpaGroupDAO.exists(newValues.getId())) {
			throw GroupRepositoryException.ID_CONFLICT;
		}

		jpaGroup.patch(jpaNewValues);

		return jpaGroup.group();

	}

	/**
	 * {@inheritDoc}
     * @throws com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException if group id does not exist
	 */
	@Override
	public Group getGroup(final String id) {

		final JPAGroup jpaGroup = this.jpaGroupDAO.get(id);

		return jpaGroup.group();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<Group> getGroups() {

		final Set<JPAGroup> jpaGroups = this.jpaGroupDAO.getAll();

		return jpaGroups.stream().map(JPAGroup::group).collect(Collectors.toSet());
	}

    /**
     * removes a group
     * @param id id of group
     * @throws com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException if group does not exist
     */
	@Override
	public void removeGroup(final String id) {
		this.jpaGroupDAO.remove(id);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<Group> ofMember(String member) {

		final Set<JPAGroup> jpaGroups = this.jpaGroupDAO.ofUser(member);

		return jpaGroups.stream().map(JPAGroup::group).collect(Collectors.toSet());
	}

}
