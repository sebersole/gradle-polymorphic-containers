package com.github.sebersole.playground.gradle.polymorphic;

import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

/**
 * "custom" as in no registered factory - will this work?
 */
public class CustomExtensionSpec implements ExtensionSpec {
	private final String name;
	private final Property<String> runtimeArtifact;
	private final Property<String> rarePieceOfTrivia;


	public CustomExtensionSpec(String name, ObjectFactory objectFactory) {
		this.name = name;
		this.runtimeArtifact = objectFactory.property( String.class );
		this.rarePieceOfTrivia = objectFactory.property( String.class );
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Property<String> getRuntimeArtifact() {
		return runtimeArtifact;
	}

	public Property<String> getRarePieceOfTrivia() {
		return rarePieceOfTrivia;
	}
}
