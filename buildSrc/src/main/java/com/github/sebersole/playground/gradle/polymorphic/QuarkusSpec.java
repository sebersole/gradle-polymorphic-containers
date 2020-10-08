package com.github.sebersole.playground.gradle.polymorphic;

import org.gradle.api.Action;
import org.gradle.api.ExtensiblePolymorphicDomainObjectContainer;
import org.gradle.api.PolymorphicDomainObjectContainer;
import org.gradle.api.model.ObjectFactory;

public class QuarkusSpec {
    private final ExtensiblePolymorphicDomainObjectContainer<ExtensionSpec> extensionSpecContainer;

    @javax.inject.Inject
    public QuarkusSpec(ObjectFactory objectFactory) {
        extensionSpecContainer = objectFactory.polymorphicDomainObjectContainer( ExtensionSpec.class );

        extensionSpecContainer.registerBinding( ExtensionSpec.class, StandardExtensionSpec.class);

        extensionSpecContainer.registerFactory(
                StandardExtensionSpec.class,
                (name) -> objectFactory.newInstance( StandardExtensionSpec.class, name )
        );

        extensionSpecContainer.registerFactory(
                SpecialExtensionSpec .class,
                (name) -> objectFactory.newInstance( SpecialExtensionSpec .class, name )
        );
    }

    void extensionSpecs(Action<PolymorphicDomainObjectContainer<ExtensionSpec>> action) {
        action.execute( extensionSpecContainer );
    }

    PolymorphicDomainObjectContainer<ExtensionSpec> getExtensionSpecs() {
        return extensionSpecContainer;
    }
}