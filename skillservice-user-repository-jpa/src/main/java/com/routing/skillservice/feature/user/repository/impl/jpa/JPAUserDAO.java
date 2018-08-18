package com.routing.skillservice.feature.user.repository.impl.jpa;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.routing.skillservice.feature.user.repository.exception.UserNotFoundException;
import com.routing.skillservice.feature.user.repository.impl.jpa.model.JPAUser;

/**
 * <b>com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserDAO</b>
 * <p>
 *   data access object of {@link JPAUser}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class JPAUserDAO {

	@PersistenceContext
	protected EntityManager em;

    /**
     * persist an user
     * @param jpaUser user to persist
     * @return persisted user
     */
	public JPAUser persist(final JPAUser jpaUser) {

		this.em.persist(jpaUser);

		return jpaUser;
	}

    /**
     * get an user
     * @param id id of user
     * @return user
     */
	public JPAUser get(final String id) {

		final TypedQuery<JPAUser> query = this.em.createQuery("Select u from JPAUser u where u.id = :parId",
				JPAUser.class);
		query.setParameter("parId", id);

		try {
			final JPAUser jpaUser = query.getSingleResult();
			if (null == jpaUser) {
				throw new NoResultException();
			}
			return jpaUser;
		} catch (NoResultException e) {
			throw new UserNotFoundException(id);
		}

	}

    /**
     * check if user exists
     * @param id id of user
     * @return true if found otherwise false
     */
	public boolean exists(final String id) {

		try {
			this.get(id);
			return true;
		} catch (UserNotFoundException e) {
			return false;
		}

	}

    /**
     * get all users
     * @return set of all users
     */
	public Set<JPAUser> getAll() {

		final TypedQuery<JPAUser> query = this.em.createQuery("Select u from JPAUser u", JPAUser.class);

		return new LinkedHashSet<>(query.getResultList());
	}

    /**
     * remove an user
     * @param jpaUser user to remove
     */
	public void remove(final JPAUser jpaUser) {
		this.em.remove(jpaUser);
	}

    /**
     * remove an user
     * @param id id of user
     * @throws UserNotFoundException if user is not found
     */
	public void remove(final String id) {

		final Query removeQuery = em.createQuery("DELETE FROM JPAUser u where u.id = :parId");
		removeQuery.setParameter("parId", id);

		final int rows = removeQuery.executeUpdate();

		if (0 == rows) {
			throw new UserNotFoundException(id);
		}
	}

}
