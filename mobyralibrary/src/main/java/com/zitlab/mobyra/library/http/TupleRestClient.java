/**
 * <LICENSE/>
 */
package com.zitlab.mobyra.library.http;

import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.auth.AuthClient;
import com.zitlab.mobyra.library.auth.BasicAuthClient;
import com.zitlab.mobyra.library.pojo.Tuple;

import java.util.Map;
import java.util.Map.Entry;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * The type Tuple rest client.
 *
 */
//TODO: Need to do Tuple and Tuple Filter implementation. And need to implement custom Serialization on deserialization.
public class TupleRestClient extends BaseRestClient {
    private String baseUrl;
    private final String username;
    private final String password;
    private final String appn;
    private String deviceId;
    private final AuthClient authClient = new BasicAuthClient();

	/**
	 * Instantiates a new Tuple rest client.
	 *
	 * @param baseUrl  the base url
	 * @param username the username
	 * @param password the password
	 * @param appn     the appn
	 */
	public TupleRestClient(String baseUrl, String username, String password, String appn) {
        super();
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.appn = appn;
        if (!this.baseUrl.endsWith("/"))
            this.baseUrl += "/";
    }

	/**
	 * Find by id.
	 *
	 * @param id       the id
	 * @param type     the type
	 * @param callback the callback
	 */
	public void findById(String id, String type, MobyraResponseCallback callback) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl)
                // .append("/api/")
                .append(appn).append("/data/").append(type).append("/").append(id);
        String url = sb.toString();
        get(url, Tuple.class, callback);
    }


    @Override
    protected final void setAuthentication(Map<String, String> headers) {
        Map<String, String> authHeaders = getAuthHeaders();
        for (Entry<String, String> entry : authHeaders.entrySet()) {
            headers.put(entry.getKey(), entry.getValue());
        }
    }

	/**
	 * Gets auth headers.
	 *
	 * @return the auth headers
	 */
	public final Map<String, String> getAuthHeaders() {
        return authClient.getHeaders(username, password, appn, deviceId);
    }

    @Override
    protected void setLogLevel(HttpLoggingInterceptor.Level level) {

    }


	/**
	 * Gets url.
	 *
	 * @param type the type
	 * @param id   the id
	 * @return the url
	 */
	protected final String getUrl(String type, String id) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/data/").append(type).append("/").append(id);
		return sb.toString();
	}

	/**
	 * Gets multi url.
	 *
	 * @param type the type
	 * @return the multi url
	 */
	protected final String getMultiUrl(String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/data/").append(type).append("/multi");
		return sb.toString();
	}

	/**
	 * Gets url.
	 *
	 * @param type the type
	 * @return the url
	 */
	protected final String getUrl(String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/data/").append(type);
		return sb.toString();
	}

	/**
	 * Query url string.
	 *
	 * @param type the type
	 * @return the string
	 */
	protected final String queryUrl(String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/query/").append(type);
		return sb.toString();
	}

	/**
	 * Action url string.
	 *
	 * @param action the action
	 * @param type   the type
	 * @return the string
	 */
	protected final String actionUrl(String action, String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/exec/");
		sb.append("/").append(type).append("/").append(action);
		return sb.toString();
	}

	/**
	 * Action url string.
	 *
	 * @param action the action
	 * @return the string
	 */
	protected final String actionUrl(String action) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/exec/__common/").append(action);
		return sb.toString();
	}

	/**
	 * List url string.
	 *
	 * @param type the type
	 * @return the string
	 */
	protected final String listUrl(String type) {
		StringBuilder sb = new StringBuilder(type.length() + 32);
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/query/").append(type).append("/list");
		return sb.toString();
	}

	/**
	 * Unique url string.
	 *
	 * @param type the type
	 * @return the string
	 */
	protected final String uniqueUrl(String type) {
		StringBuilder sb = new StringBuilder();
		sb.append(baseUrl)
				// .append("/api/")
				.append(appn).append("/query/").append(type).append("/unique");
		return sb.toString();
	}

	/**
	 * Custom url string.
	 *
	 * @param url the url
	 * @return the string
	 */
	protected final String customUrl(String url) {
		StringBuilder sb = new StringBuilder(baseUrl);
		sb.append(appn).append("/").append(url);
		return sb.toString();
	}

}
