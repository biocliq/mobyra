/**
 * <LICENSE/>
 */
package com.zitlab.mobyra.library.http;

import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.auth.AuthClient;
import com.zitlab.mobyra.library.auth.BasicAuthClient;
import com.zitlab.mobyra.library.builder.MobyraClientBuilder;

import java.util.Map;
import java.util.Map.Entry;

import static com.zitlab.mobyra.library.util.StringUtil.FORWARD_SLASH;

/**
 * The type Tuple rest client.
 */
public class TupleRestClient extends BaseRestClient {
    private static final String KEY_API = "api";
    private static final String KEY_QUERY = "query";
    private static final String KEY_UNIQUE = "unique";
    private static final String KEY_DATA = "data";
    private static final String KEY_LIST = "list";
    private final String username;
    private final String password;
    private final String appName;
    private final String context;
    private final String apiVersion;
    private final AuthClient authClient = new BasicAuthClient();
    private String deviceId;

    /**
     * Instantiates a new Tuple rest client.
     *
     * @param builder the builder
     */
    public TupleRestClient(MobyraClientBuilder builder) {
        super(builder);
        username = builder.getUserName();
        password = builder.getPassword();
        appName = builder.getAppName();
        context = builder.getContext();
        apiVersion = builder.getApiVersion();
    }

    /**
     * Gets auth headers.
     *
     * @return the auth headers
     */
    public final Map<String, String> getAuthHeaders() {
        return authClient.getHeaders(username, password, appName, deviceId);
    }

    /**
     * Find by id.
     *
     * @param <T>          the type parameter
     * @param id           the id
     * @param type         the type
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void findById(String id, String type, Class<T> responseType, MobyraResponseCallback callback) {
        String url = pathUrl(type, id);
        get(url, responseType, callback);
    }

    /**
     * Path url string.
     *
     * @param type the type
     * @param id   the id
     * @return the string
     */
    protected String pathUrl(final String type, final String id) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_QUERY);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type);
        }
        if (null != id) {
            sb.append(FORWARD_SLASH).append(id);
        }
        return sb.toString();
    }

    /**
     * List url string.
     *
     * @param type the type
     * @return the string
     */
    protected String listUrl(final String type) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_QUERY);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type);
        }
        sb.append(FORWARD_SLASH).append(KEY_LIST);

        return sb.toString();
    }

    /**
     * Unique url string.
     *
     * @param type the type
     * @return the string
     */
    protected final String uniqueUrl(final String type) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_QUERY);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type);
        }
        sb.append(FORWARD_SLASH).append(KEY_UNIQUE);
        return sb.toString();
    }

    /**
     * Data url string.
     *
     * @param type the type
     * @return the string
     */
    protected String dataUrl(final String type) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_DATA);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type);
        }
        return sb.toString();
    }

    private StringBuilder getContextPath() {
        StringBuilder sb = new StringBuilder();
        sb.append(appName)
                .append(FORWARD_SLASH).append(KEY_API)
                .append(FORWARD_SLASH).append(apiVersion)
                .append(FORWARD_SLASH).append(context);
        return sb;
    }

    @Override
    protected final void setAuthentication(Map<String, String> headers) {
        Map<String, String> authHeaders = getAuthHeaders();
        for (Entry<String, String> entry : authHeaders.entrySet()) {
            headers.put(entry.getKey(), entry.getValue());
        }
    }
}
