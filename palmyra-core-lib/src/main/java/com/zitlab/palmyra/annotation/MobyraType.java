/**
 * <LICENSE/>
 */
package com.zitlab.palmyra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface provides the Mobyra type. We will use this for creating API path url.
 *
 * @author ksvraja
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MobyraType {
    /**
     * Value of mobyra type.
     *
     * @return the  mobyra string value.
     */
    String value();
}
