package com.zitlab.palmyra.auth;

import com.zitlab.palmyra.util.StringUtil;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Palmyra auth client class which provides required headers for API authentication.
 */
public class PalmyraAuthClient implements AuthClient {

    private final String HEADER_USER = "X-Palmyra-user";
    private final String HEADER_DEVICE = "X-Palmyra-device";
    private final String HEADER_RANDOM = "X-Palmyra-random";
    private final String HEADER_SECRET = "X-Palmyra-Authorization";

    @Override
    public Map<String, String> getHeaders(String username, String password, String context, String deviceId) {
        String random = getUniqueRef();
        StringBuilder auth = new StringBuilder(username).append(StringUtil.ASTERISK).append(context)
                .append(StringUtil.COLON).append(DigestUtils.md5Hex(password))
                .append(random);
        String authHeader = DigestUtils.md5Hex(auth.toString());

        Map<String, String> authMap = new HashMap<>();
        authMap.put(HEADER_SECRET, authHeader);
        authMap.put(HEADER_USER, username);
        authMap.put(HEADER_RANDOM, random);
        if (null != deviceId) {
            authMap.put(HEADER_DEVICE, deviceId);
        }
        return authMap;
    }

    /**
     * Gets the unique timestamp.
     * @return Unique timestamp string value.
     */
    private String getUniqueRef() {
        return Long.toString(new Date().getTime());
    }

}
