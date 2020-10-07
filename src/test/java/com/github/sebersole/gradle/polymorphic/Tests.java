package com.github.sebersole.gradle.polymorphic;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;

import org.junit.Test;

/**
 * @author Steve Ebersole
 */
public class Tests {
	@Test
	public void testIt() {
		final GradleRunner gradleRunner = createGradleRunner( "simple", "qwe" );

		final BuildResult result = gradleRunner.build();
	}

	public static GradleRunner createGradleRunner(String projectPath, String... tasks) {
		final File projectsBaseDirectory = testProjectsBaseDirectory();
		final File projectDirectory = new File( projectsBaseDirectory, projectPath );

		final File tempDir = new File(
				projectsBaseDirectory.getParentFile().getParentFile(),
				"tmp"
		);
		final File testKitDir = new File( tempDir, "test-kit" );

		final GradleRunner gradleRunner = GradleRunner.create()
				.withPluginClasspath()
				.withProjectDir( projectDirectory )
				.withTestKitDir( testKitDir )
				.forwardOutput()
				.withDebug( true );

		if ( tasks == null ) {
			return gradleRunner.withArguments( "--stacktrace" );
		}
		else {
			final List<String> arguments = new ArrayList<>( Arrays.asList( tasks ) );
			arguments.add( "--stacktrace" );
			return gradleRunner.withArguments( arguments );
		}
	}


	public static File testProjectsBaseDirectory() {
		final URL baseUrl = Tests.class.getResource( "/project-directory.marker" );
		return new File( baseUrl.getFile() ).getParentFile();
	}
}
