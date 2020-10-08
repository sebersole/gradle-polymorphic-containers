package com.github.sebersole.playground.gradle.polymorphic;

import org.gradle.api.Named;
import org.gradle.api.provider.Property;

/**
 * @author Steve Ebersole
 */
public interface ExtensionSpec extends Named {
	Property<String> getRuntimeArtifact();
}