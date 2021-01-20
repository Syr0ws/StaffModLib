package fr.syrows.staffmodlib.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// The annotation can be used only on methods.
@Target(ElementType.METHOD)
// The annotation is available in runtime by introspection.
@Retention(RetentionPolicy.RUNTIME)
// The annotation will be documented in the Javadoc.
@Documented
public @interface UseEvent {
}
