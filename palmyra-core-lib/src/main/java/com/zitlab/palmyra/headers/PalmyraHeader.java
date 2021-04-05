package com.zitlab.palmyra.headers;

import java.util.HashMap;
import java.util.Map;

/**
 * The enum Palmyra header class defined with few pre define header enums. You can pass the enum to set palmyra headers.
 */
public enum PalmyraHeader {

    /**
     * Io mobyra error.
     */
    RETURN_SAVE_ALL("X-Palmyra-returnsave", "all"),
    /**
     * Return save system palmyra header.
     */
    RETURN_SAVE_SYSTEM("X-Palmyra-returnsave", "system");

    private final String key;
    private final String value;

    /**
     *Constructs palmyra header with key and value.
     *
     * @param key Header key.
     * @param value Header value.
     */
    PalmyraHeader(final String key, final String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String key() {
        return this.key;
    }

    /**
     * Value string.
     *
     * @return the string
     */
    public String value() {
        return this.value;
    }

    /**
     * Headers map.
     *
     * @return the headers map.
     */
    public Map<String, String> map() {
        return new HashMap<String, String>() {{
            put(key, value);
        }};
    }

}
