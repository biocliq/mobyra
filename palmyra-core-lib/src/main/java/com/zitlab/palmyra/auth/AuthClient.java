package com.zitlab.palmyra.auth;

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
     * The constant HEADER_DEVICE. Which sends the palmyra device string in request header.
     */
    String HEADER_DEVICE = "X-Palmyra-device";

    /**
     * Gets headers for palmyra authentication.
     *
     * @param username the username to access Palmyra APIs.
     * @param password the password to access Palmyra APIs.
     * @param context  the context of the Palmyra APIs.
     * @param deviceId the device id using to access palmyra APIs.
     * @return the headers
     */
    Map<String, String> getHeaders(String username, String password, String context, String deviceId);

}
