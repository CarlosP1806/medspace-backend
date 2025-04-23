package com.medspace.infrastructure.rest.annotations;

import jakarta.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@NameBinding // Marks this as a JAX-RS name binding annotation
@Retention(RetentionPolicy.RUNTIME) // Ensures the annotation is available at runtime for processing
@Target({ElementType.TYPE, ElementType.METHOD}) // Allows this annotation on classes and methods
public @interface LandlordOnly {
}
