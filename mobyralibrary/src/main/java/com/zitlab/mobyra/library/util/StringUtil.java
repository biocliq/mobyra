package com.zitlab.mobyra.library.util;

/**
 * The type String util.
 */
public class StringUtil {
    /**
     * The constant SPACE.
     */
    public static final String SPACE = " ";
    /**
     * The constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The constant COLON.
     */
    public static final String COLON = ":";
    /**
     * The constant ASTERISK.
     */
    public static final String ASTERISK = "*";

    /**
     * The constant FORWARD_SLASH.
     */
    public static final String FORWARD_SLASH = "/";

    /**
     * The constant GREATER_THAN.
     */
    public static final String GREATER_THAN = ">";

    /**
     * The constant GREATER_THAN_EQUAL.
     */
    public static final String GREATER_THAN_EQUAL = ">=";

    /**
     * The constant LESS_THAN.
     */
    public static final String LESS_THAN = "<";

    /**
     * The constant LESS_THAN_EQUAL.
     */
    public static final String LESS_THAN_EQUAL = "<=";

    /**
     * The constant BETWEEN.
     */
    public static final String BETWEEN = "...";

    /**
     * The constant EQUALS.
     */
    public static final String EQUALS = "==";
    /**
     * The constant NOT_EQUALS.
     */
    public static final String NOT_EQUALS = "!=";
    /**
     * The constant UNDERSCORE.
     */
    public static final String UNDERSCORE = "_";


    /**
     * To string string.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String toString(byte[] bytes) {
        return new String(bytes);
    }
}
