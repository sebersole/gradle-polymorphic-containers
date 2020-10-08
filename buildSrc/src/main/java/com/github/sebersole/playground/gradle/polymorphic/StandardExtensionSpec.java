package com.github.sebersole.playground.gradle.polymorphic;

import javax.inject.Inject;

import org.gradle.api.provider.Property;

public abstract class StandardExtensionSpec implements ExtensionSpec {
    private final String name;

    @Inject
    public StandardExtensionSpec(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract Property<String> getRuntimeArtifact();
}