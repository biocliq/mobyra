package com.zitlab.mobyra.library.auth;

import android.util.Base64;
import com.zitlab.mobyra.library.util.StringUtil;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Basic auth client.
 */
public class BasicAuthClient implements AuthClient{
     private static final String TAG_BASIC_AUTH = "Basic";

    @Override
    public Map<String, String> getHeaders(String username, String password, String context, String deviceId) {
        String auth = username + StringUtil.COLON + password;
        byte[] encodedAuth =  Base64.encode(auth.getBytes(), Base64.NO_WRAP);
        String authHeader = TAG_BASIC_AUTH.concat(StringUtil.SPACE).concat(StringUtil.toString(encodedAuth));
        Map<String, String> result = new HashMap<>();
        result.put(HEADER_BASIC_AUTH, authHeader);
        if(null != deviceId) {
            result.put(HEADER_DEVICE, deviceId);
        }
        return result;
    }

}
