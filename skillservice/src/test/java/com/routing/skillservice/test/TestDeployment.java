package com.routing.skillservice.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

public class TestDeployment {

	public static void main(String[] args) {
		new TestDeployment().build();
	}

	public WebArchive build() {

		final List<Package> packages = packages();

		final File[] libs = Maven.resolver().loadPomFromFile("pom.xml").importCompileAndRuntimeDependencies().resolve()
				.withTransitivity().asFile();

		final WebArchive deployment = ShrinkWrap.create(WebArchive.class).addAsLibraries(libs);

		if (packages.size() > 0) {
			deployment.addPackages(true, Filters.exclude(packages.toArray(new Package[packages.size()])),
					"com.routing");
		} else {
			deployment.addPackages(true, "com.routing");
		}

		deployment.addAsResource("META-INF/persistence-integration.xml", "META-INF/persistence.xml")
				.addAsResource("project-defaults.yml");
		return deployment;
	}

	private List<Package> packages() {
		List<Package> packages = new ArrayList<>(2);
		this.loadUserRepository();
		final Package userRepositoryImpl = Package
				.getPackage("com.routing.skillservice.feature.user.repository.impl.jpa");
		if (null != userRepositoryImpl) {
			packages.add(userRepositoryImpl);
		}
		this.loadGroupRepository();
		final Package groupRepositoryImpl = Package
				.getPackage("com.routing.skillservice.feature.group.repository.impl.jpa");
		if (null != groupRepositoryImpl) {
			packages.add(groupRepositoryImpl);
		}
		return packages;
	}

	private void loadUserRepository() {
		try {
			Thread.currentThread().getContextClassLoader()
					.loadClass("com.routing.skillservice.feature.user.repository.impl.jpa.JPAUserRepository");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadGroupRepository() {
		try {
			Thread.currentThread().getContextClassLoader()
					.loadClass("com.routing.skillservice.feature.group.repository.impl.jpa.JPAGroupRepository");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
