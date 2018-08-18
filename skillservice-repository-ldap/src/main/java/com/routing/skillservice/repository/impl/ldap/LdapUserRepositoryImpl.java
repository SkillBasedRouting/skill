package com.routing.skillservice.repository.impl.ldap;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.directory.api.ldap.model.cursor.EntryCursor;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.LdapConnection;

import com.routing.skillservice.feature.user.repository.UserRepository;
import com.routing.skillservice.feature.user.repository.exception.UserNotFoundException;
import com.routing.skillservice.feature.user.repository.model.User;
import com.routing.skillservice.repository.impl.ldap.model.LdapUser;

/**
 * <b>com.routing.skillservice.repository.impl.ldap.LdapUserRepositoryImpl</b>
 * <p>
 *   ldap implementation of {@link UserRepository}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class LdapUserRepositoryImpl implements UserRepository {

	@Inject
	private LdapConnection connection;

    /**
     * not supported yet
     * @param user
     * @return
     */
	@Override
	public User createUser(final User user) {
		throw new UnsupportedOperationException();
	}

    /**
     * not supported yet
     * @param id
     * @param newValues
     * @return
     */
	@Override
	public User updateUser(final String id, final User newValues) {
		throw new UnsupportedOperationException();
	}

    /**
     * not supported yet
     * @param id
     * @param newValues
     * @return
     */
	@Override
	public User patchUser(final String id, final User newValues) {
		throw new UnsupportedOperationException();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public User getUser(final String id) {

		final String query = String.format("(&(objectclass=inetOrgPerson)(employeeNumber=%s))", id);
		System.out.println(query);

		final Set<User> users = this.searchUser(query);

		if (0 == users.size()) {
			throw new UserNotFoundException(id);
		} else if (users.size() > 1) {
			throw new RuntimeException("multiple users eligable");
		}

		return users.iterator().next();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<User> getUsers() {

		final String query = "(objectclass=inetOrgPerson)";

		return this.searchUser(query);
	}

    /**
     * not supported yet
     * @param id
     */
	@Override
	public void removeUser(final String id) {
		throw new UnsupportedOperationException();
	}

	private Set<User> searchUser(final String query) {

		try {
			final EntryCursor cursor = connection.search("ou=users,dc=example,dc=org", query, SearchScope.SUBTREE);

			final Stream<Entry> stream = StreamSupport.stream(cursor.spliterator(), false);

			return stream.map(e -> new LdapUser(e)).collect(Collectors.toSet());

		} catch (LdapException e) {
			throw new RuntimeException(e);
		}

	}

}
