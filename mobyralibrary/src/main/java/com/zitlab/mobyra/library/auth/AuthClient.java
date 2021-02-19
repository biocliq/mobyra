package com.zitlab.mobyra.library.auth;

import java.util.Map;

/**
 * The interface Auth client.
 */
public interface AuthClient {

    /**
     * The constant HEADER_BASIC_AUTH.
     */
    String HEADER_BASIC_AUTH = "Authorization";

    /**
     * The constant HEADER_DEVICE.
     */
    String HEADER_DEVICE = "X-Palmyra-device";

    /**
     * Gets headers.
     *
     * @param username the username
     * @param password the password
     * @param context  the context
     * @param deviceId the device id
     * @return the headers
     */
    Map<String, String> getHeaders(String username, String password, String context, String deviceId);

}
