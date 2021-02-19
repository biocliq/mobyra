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
     * To string string.
     *
     * @param bytes the bytes
     * @return the string
     */
    public static String toString(byte[] bytes){
        return new String(bytes);
    }
}
