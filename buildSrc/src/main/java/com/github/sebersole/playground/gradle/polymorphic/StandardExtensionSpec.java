package com.github.sebersole.playground.gradle.polymorphic;

import javax.inject.Inject;

import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

public abstract class StandardExtensionSpec implements ExtensionSpec {
    private final String name;
    private final Property<String> runtimeArtifact;

    @Inject
    public StandardExtensionSpec(String name, ObjectFactory objectFactory) {
        this.name = name;
        this.runtimeArtifact = objectFactory.property( String.class );
    }

    @Override
    public String getName() {
        return name;
    }

    public Property<String> getRuntimeArtifact() {
        return runtimeArtifact;
    }
}