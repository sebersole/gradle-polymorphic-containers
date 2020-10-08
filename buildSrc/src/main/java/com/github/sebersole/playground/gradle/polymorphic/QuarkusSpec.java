package com.github.sebersole.playground.gradle.polymorphic;

import org.gradle.api.Action;
import org.gradle.api.ExtensiblePolymorphicDomainObjectContainer;
import org.gradle.api.PolymorphicDomainObjectContainer;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.util.ConfigureUtil;

import groovy.lang.Closure;

public class QuarkusSpec {
    private final Project project;
    private final ExtensiblePolymorphicDomainObjectContainer<ExtensionSpec> extensionSpecContainer;

    public QuarkusSpec(Project project) {
        this.project = project;

        final ObjectFactory objectFactory = project.getObjects();

        extensionSpecContainer = objectFactory.polymorphicDomainObjectContainer( ExtensionSpec.class );

        // this does not work - research how this is supposed to work
        //extensionSpecContainer.registerBinding( ExtensionSpec.class, StandardExtensionSpec.class );

        extensionSpecContainer.registerFactory(
                ExtensionSpec.class,
                (name) -> {
                    project.getLogger().lifecycle( "Creating (default) `StandardExtensionSpec` via registered factory" );
                    return objectFactory.newInstance( StandardExtensionSpec.class, name );
                    //return new StandardExtensionSpec ( name, objectFactory ) {
                    //};
                }
        );

        extensionSpecContainer.registerFactory(
                StandardExtensionSpec.class,
                (name) -> {
                    project.getLogger().lifecycle( "Creating `StandardExtensionSpec` via registered factory" );
                    return objectFactory.newInstance( StandardExtensionSpec.class, name );
                }
        );

        extensionSpecContainer.addRule(
                "hibernateOrm",
                name -> {
                    final ExtensionSpec extensionSpec = extensionSpecContainer.maybeCreate( "hibernateOrm" );
                    extensionSpec.setRuntimeArtifact( "org.hibernate:hibernate-core:SOMETHING" );
                }
        );

        extensionSpecContainer.registerFactory(
                SpecialExtensionSpec.class,
                name -> {
                    project.getLogger().lifecycle( "Creating `SpecialExtensionSpec` via registered factory" );
                    return objectFactory.newInstance( SpecialExtensionSpec .class, name );
                }
        );
    }

    public void extensionSpecs(Action<PolymorphicDomainObjectContainer<ExtensionSpec>> action) {
        project.getLogger().lifecycle( "Access to `extensionSpecs` container via Action" );
        action.execute( extensionSpecContainer );
    }

    public void extensionSpecs(Closure<PolymorphicDomainObjectContainer<ExtensionSpec>> closure) {
        project.getLogger().lifecycle( "Access to `extensionSpecs` container via Closure" );
        ConfigureUtil.configure( closure, extensionSpecContainer );
    }

    public PolymorphicDomainObjectContainer<ExtensionSpec> getExtensionSpecContainer() {
        return extensionSpecContainer;
    }
}