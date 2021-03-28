/**
 * <LICENSE/>
 */
package com.zitlab.palmyra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface Mobyra type.
 *
 * @author ksvraja
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MobyraType {
    /**
     * Value string.
     *
     * @return the string
     */
    String value();
}
