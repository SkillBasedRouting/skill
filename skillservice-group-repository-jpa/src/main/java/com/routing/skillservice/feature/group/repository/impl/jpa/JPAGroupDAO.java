package com.routing.skillservice.feature.group.repository.impl.jpa;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException;
import com.routing.skillservice.feature.group.repository.impl.jpa.model.JPAGroup;

/**
 * <b>com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupDAO</b>
 * <p>
 *   data access object for {@link JPAGroup}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class JPAGroupDAO {

	@PersistenceContext
	protected EntityManager em;

    /**
     * persist a group
     * @param group group to persist
     * @return persisted group
     */
	public JPAGroup persist(final JPAGroup group) {

		this.em.persist(group);

		return group;
	}

    /**
     * get a group
     * @param id id of group
     * @return group
     * @throws GroupNotFoundException if group does not exist
     */
	public JPAGroup get(final String id) {

		final TypedQuery<JPAGroup> query = this.em.createQuery("Select g from JPAGroup g where g.id = :parId",
				JPAGroup.class);
		query.setParameter("parId", id);

		try {
			final JPAGroup jpaGroup = query.getSingleResult();
			if (null == jpaGroup) {
				throw new NoResultException();
			}
			return jpaGroup;
		} catch (NoResultException e) {
			throw new GroupNotFoundException(id);
		}

	}

    /**
     * check if a group exist
     * @param id id of group
     * @return true if group exists otherwise false
     */
	public boolean exists(final String id) {

		try {
			this.get(id);
			return true;
		} catch (GroupNotFoundException e) {
			return false;
		}

	}

    /**
     * get all groups
     * @return set of all groups
     */
	public Set<JPAGroup> getAll() {

		final TypedQuery<JPAGroup> query = this.em.createQuery("Select g from JPAGroup g", JPAGroup.class);

		return new LinkedHashSet<>(query.getResultList());
	}

    /**
     * remove a group
     * @param group group to remove
     */
	public void remove(final JPAGroup group) {
		this.em.remove(group);
	}

    /**
     * get all groups of a member
     * @param userId member as its stored in group
     * @return all groups of member
     */
	public Set<JPAGroup> ofUser(final String userId) {

		final TypedQuery<JPAGroup> query = this.em
				.createQuery("Select g from JPAGroup g join g.members m where m = :parUserId", JPAGroup.class);
		query.setParameter("parUserId", userId);

		return new LinkedHashSet<>(query.getResultList());
	}

    /**
     * remove a group by its id
     * @param id id of group
     * @throws GroupNotFoundException if group does not exist
     */
	public void remove(final String id) {

		final Query removeQuery = em.createQuery("DELETE FROM JPAGroup g where g.id = :parId");
		removeQuery.setParameter("parId", id);

		final int rows = removeQuery.executeUpdate();

		if (0 == rows) {
			throw new GroupNotFoundException(id);
		}
	}

}
