package annotations;

import actions.AuthorizationAction;
import enums.Role;
import play.mvc.With;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * Includes all security parameter that the application need to take into account in every web service
 *
 * @see {@link AuthorizationAction}
 */
@With(AuthorizationAction.class)
@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Secured {

    /**
     * Specifies the list of roles allowed to access method(s) in the application
     * The values of the rolesAllowed is a {@link List} of {@link Role}
     */
    Role[] rolesAllowed() default {Role.USER, Role.ADMIN};
}
