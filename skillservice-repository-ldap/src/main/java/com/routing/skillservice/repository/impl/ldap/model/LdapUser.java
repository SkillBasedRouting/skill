package com.routing.skillservice.repository.impl.ldap.model;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;

import com.routing.skillservice.feature.user.repository.model.User;

/**
 * <b>com.routing.skillservice.repository.impl.ldap.model.LdapUser</b>
 * <p>
 *   entity that represent an {@link User} in ldap
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class LdapUser extends User {

	public LdapUser(final Entry entry) {

		try {
			super.setId(entry.get("employeeNumber").getString());
			super.setName(entry.get("cn").getString());
		} catch (LdapInvalidAttributeValueException e) {
			throw new RuntimeException("invalid ldap attribute", e);
		}

	}

}
