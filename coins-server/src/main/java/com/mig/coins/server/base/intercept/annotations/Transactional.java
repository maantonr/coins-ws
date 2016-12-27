package com.mig.coins.server.base.intercept.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// PDTE Doc
/**
 * @author jjimenezg
 */
@Target({ ElementType.CONSTRUCTOR, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Transactional {
}
