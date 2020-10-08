package com.github.sebersole.playground.gradle.polymorphic;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Steve Ebersole
 */
public class QuarkusPlugin implements Plugin<Project> {
	@Override
	public void apply(Project project) {
		project.getExtensions().add(
				"quarkus",
				new QuarkusSpec( project )
		);
	}
}
