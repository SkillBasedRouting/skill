package com.routing.skillservice.repository.impl.ldap.model;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.entry.Value;
import org.apache.directory.api.ldap.model.exception.LdapInvalidAttributeValueException;

import com.routing.skillservice.feature.group.repository.model.Group;

/**
 * <b>com.routing.skillservice.repository.impl.ldap.model.LdapGroup</b>
 * <p>
 *   entity that represent a {@link Group} in ldap
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
public class LdapGroup extends Group {

	public LdapGroup(final Entry entry) {

		try {
			
			super.setId(entry.get("gidNumber").getString());
			super.setName(entry.get("cn").getString());
			final Stream<Value<?>> stream = StreamSupport.stream(entry.get("memberUid").spliterator(), false);
			super.setMembers(stream.map(v -> v.getString()).collect(Collectors.toSet()));

		} catch (LdapInvalidAttributeValueException e) {
			throw new RuntimeException("unsupported ldap attribute", e);
		}
	}

}
