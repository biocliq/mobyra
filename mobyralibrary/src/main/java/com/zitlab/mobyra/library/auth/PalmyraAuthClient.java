package com.zitlab.mobyra.library.auth;

import com.zitlab.mobyra.library.util.StringUtil;

import org.apache.commons.codec.digest.DigestUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Palmyra auth client.
 */
public class PalmyraAuthClient implements AuthClient {

    private String HEADER_USER = "X-Palmyra-user";
    private String HEADER_DEVICE = "X-Palmyra-device";
    private String HEADER_RANDOM = "X-Palmyra-random";
    private String HEADER_SECRET = "X-Palmyra-Authorization";

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
        if(null != deviceId) {
            authMap.put(HEADER_DEVICE, deviceId);
        }
        return authMap;
    }

    private String getUniqueRef() {
        return Long.toString(new Date().getTime());
    }

}
