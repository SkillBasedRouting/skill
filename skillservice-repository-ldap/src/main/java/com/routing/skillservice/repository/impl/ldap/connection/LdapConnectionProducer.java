package com.routing.skillservice.repository.impl.ldap.connection;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.ldap.client.api.DefaultLdapConnectionFactory;
import org.apache.directory.ldap.client.api.DefaultPoolableLdapConnectionFactory;
import org.apache.directory.ldap.client.api.LdapConnection;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapConnectionPool;

/**
 * <b>com.routing.skillservice.repository.impl.ldap.connection.LdapConnectionProducer</b>
 * <p>
 *   produces a valid ldap connection over cdi
 * </p>
 *
 * @author <a href="mailto:arndt@schwenkschuster.de">Arndt Schwenkschuster</a>
**/
@ApplicationScoped
public class LdapConnectionProducer {

	private LdapConnectionPool pool;

	@PostConstruct
	public void init() {

		final LdapConnectionConfig config = new LdapConnectionConfig();
		config.setLdapHost("openldap");
		config.setLdapPort(389);
		config.setName("cn=admin,dc=example,dc=org");
		config.setCredentials("admin");
		config.setTimeout(300L);

		final DefaultLdapConnectionFactory factory = new DefaultLdapConnectionFactory(config);
		factory.setTimeOut(300L);
		pool = new LdapConnectionPool(new DefaultPoolableLdapConnectionFactory(factory));

	}

	@Produces
	@RequestScoped
	public LdapConnection getConnection() {
		try {
			return this.pool.getConnection();
		} catch (LdapException e) {
			throw new RuntimeException(e);
		}
	}

	public void displose(@Disposes final LdapConnection connection) {
		try {
			this.pool.releaseConnection(connection);
		} catch (LdapException e) {
			throw new RuntimeException(e);
		}
	}

}
