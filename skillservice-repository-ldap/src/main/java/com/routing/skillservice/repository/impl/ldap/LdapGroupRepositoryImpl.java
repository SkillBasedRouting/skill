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

import com.routing.skillservice.feature.group.repository.GroupRepository;
import com.routing.skillservice.feature.group.repository.exception.GroupNotFoundException;
import com.routing.skillservice.feature.group.repository.model.Group;
import com.routing.skillservice.repository.impl.ldap.model.LdapGroup;

/**
 * <b>com.routing.skillservice.repository.impl.ldap.LdapGroupRepositoryImpl</b>
 * <p>
 *   ldap implementation of {@link GroupRepository}
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class LdapGroupRepositoryImpl implements GroupRepository {

	@Inject
	private LdapConnection connection;

	/**
	 * not supported yet
	 * @param group the group to create
	 * @return
	 */
	@Override
	public Group createGroup(final Group group) {
		throw new UnsupportedOperationException();
	}

    /**
     * not supported yet
     * @param id id of group
     * @param newValues new values
     * @return
     */
	@Override
	public Group updateGroup(final String id, final Group newValues) {
		throw new UnsupportedOperationException();
	}

    /**
     * not supported yet
     * @param id id of group
     * @param newValues new values
     * @return
     */
	@Override
	public Group patchGroup(final String id, final Group newValues) {
		throw new UnsupportedOperationException();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Group getGroup(final String id) {

		final String query = String.format("(&(objectClass=posixGroup)(gidNumber=%s))", id);

		final Set<Group> groups = this.searchGroup(query);
		
		if (groups.size() == 0) {
			throw new GroupNotFoundException(id);
		} else if (groups.size() > 1) {
			throw new RuntimeException("multiple groups eligable");
		} 
		
		return groups.iterator().next();
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<Group> getGroups() {

		final String query = "(objectclass=posixGroup)";
		return this.searchGroup(query);
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Set<Group> ofMember(final String member) {

		final String query = String.format("(&(objectClass=posixGroup)(memberUid=%s))", member);

		return this.searchGroup(query);
	}

    /**
     * not supported yet
     * @param id id of group
     */
	@Override
	public void removeGroup(final String id) {
		throw new UnsupportedOperationException();
	}

	private Set<Group> searchGroup(final String query) {

		try {
			final EntryCursor cursor = connection.search("ou=groups,dc=example,dc=org", query, SearchScope.SUBTREE);

			final Stream<Entry> stream = StreamSupport.stream(cursor.spliterator(), false);

			return stream.map(e -> new LdapGroup(e)).collect(Collectors.toSet());

		} catch (LdapException e) {
			throw new RuntimeException(e);
		}

	}

}
