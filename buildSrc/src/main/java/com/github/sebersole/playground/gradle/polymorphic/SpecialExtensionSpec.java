package com.github.sebersole.playground.gradle.polymorphic;

import javax.inject.Inject;

import org.gradle.api.provider.Property;

/**
 * @author Steve Ebersole
 */
public abstract class SpecialExtensionSpec implements ExtensionSpec {
	private final String name;

	@Inject
	public SpecialExtensionSpec(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	public abstract Property<String> getRuntimeArtifact();
	public abstract Property<String> specialValue();

}
