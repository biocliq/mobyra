package com.zitlab.palmyra.http;

import com.google.gson.Gson;
import com.zitlab.palmyra.ResponseCallback;
import com.zitlab.palmyra.auth.AuthClient;
import com.zitlab.palmyra.exception.PalmyraError;
import com.zitlab.palmyra.exception.PalmyraException;
import com.zitlab.palmyra.headers.PalmyraHeader;
import com.zitlab.palmyra.util.BaseDevice;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * The type Base rest client.
 */
public abstract class BaseRestClient {

    /**
     * The constant JSON.
     */
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    private final Gson gson = new Gson();
    private final MobyraClientBuilder builder;
    private Map<String, String> requestHeaders;


    /**
     * Instantiates a new Base rest client.
     *
     * @param builder the builder
     */
    public BaseRestClient(MobyraClientBuilder builder) {
        this.builder = builder;
    }

    /**
     * Gets auth client.
     *
     * @return the auth client
     */
    protected abstract AuthClient getAuthClient();

    private OkHttpClient getNewHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.valueOf(builder.getLogLevel().name()));

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(builder.getConnectionTimeout(), TimeUnit.SECONDS)
                .writeTimeout(builder.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(builder.getReadTimeout(), TimeUnit.SECONDS)
                .addInterceptor(logging)
                .hostnameVerifier((hostname, session) -> true)
                .build();

        return client;
    }

    /**
     * Gets request headers.
     *
     * @return the request headers
     */
    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * Sets request headers.
     *
     * @param requestHeaders the request headers
     */
    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    /**
     * Add request header.
     *
     * @param key   the key
     * @param value the value
     */
    public void addRequestHeader(String key, String value) {
        if (null != key) {
            this.requestHeaders.put(key, value);
        }
    }

    /**
     * Add request header.
     *
     * @param header the header
     */
    public void addRequestHeader(PalmyraHeader header) {
        if (null != header && null != header.key()) {
            this.requestHeaders.put(header.key(), header.value());
        }
    }

    /**
     * Get response.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void get(final String path, final Class<T> responseType, final ResponseCallback callback) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme(builder.getScheme())
                .host(builder.getHostName())
                .addPathSegment(path)
                .build();
        Request request = getHttpBuilder().url(url).build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Post response.
     *
     * @param path         the path
     * @param obj          the obj
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected void post(final String path, final Object obj, final Type responseType, final ResponseCallback callback) {
        String requestBody = gson.toJson(obj);
        this.post(path, requestBody, responseType, callback);
    }

    /**
     * Post response.
     *
     * @param path         the path
     * @param jsonData     the json data
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected void post(final String path, final String jsonData, final Type responseType, ResponseCallback callback) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme(builder.getScheme())
                .host(builder.getHostName())
                .addPathSegment(path)
                .build();

        RequestBody body = RequestBody.create(jsonData, JSON);
        Request request = getHttpBuilder()
                .url(url)
                .post(body)
                .build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Put.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param obj          the obj
     * @param responseType the response type
     * @param callback     the callback
     */
    protected <T> void put(final String path, final Object obj, final Class<T> responseType, final ResponseCallback callback) {
        String requestBody = gson.toJson(obj);
        this.put(path, requestBody, responseType, callback);
    }


    /**
     * Put.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param jsonData     the json data
     * @param responseType the response type
     * @param callback     the callback
     */
    protected <T> void put(final String path, final String jsonData, final Class<T> responseType, ResponseCallback callback) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme(builder.getScheme())
                .host(builder.getHostName())
                .addPathSegment(path)
                .build();

        RequestBody body = RequestBody.create(jsonData, JSON);
        Request request = getHttpBuilder()
                .url(url)
                .put(body)
                .build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Delete response.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void delete(final String path, final Class<T> responseType, final ResponseCallback callback) {

        HttpUrl url = new HttpUrl.Builder()
                .scheme(builder.getScheme())
                .host(builder.getHostName())
                .addPathSegment(path)
                .build();

        Request request = getHttpBuilder()
                .url(url)
                .delete()
                .build();
        executeRequest(request, responseType, callback);
    }


    private Request.Builder getHttpBuilder() {
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = getAllRequestHeaders(getRequestHeaders());
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    private <T> void executeRequest(Request request, Type valueType, ResponseCallback callback) {
        getNewHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onMobyraResponse(false, Object.class, new PalmyraException(e));
            }

            @Override
            public void onResponse(Call call, Response response) {
                boolean isSuccess = response.isSuccessful();
                if (isSuccess) {
                    try {
                        T obj = deserialize(response, valueType);
                        sendCallback(callback, true, obj, null);
                        //callback.onMobyraResponse(true, obj, null);
                    } catch (PalmyraException e) {
                        e.printStackTrace();
                        sendCallback(callback, false, null, e);
                        //callback.onMobyraResponse(false, null, e);
                    }
                } else {
                    sendCallback(callback, false, null, new PalmyraException(response.code(), response.message()));
                    //callback.onMobyraResponse(false, null, new MobyraException(response.code(), response.message()));
                }
            }
        });
    }

    /**
     * Sends callback on main thread.
     *
     * @param <T>      the type parameter
     * @param callback the callback
     * @param status   the status
     * @param response the response
     * @param ex       the ex
     */
//    public  <T> void sendCallback(MobyraResponseCallback callback, boolean status, T response, MobyraException ex) {
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                callback.onMobyraResponse(status, response, ex);
//            }
//        });
//    }
    protected abstract <T> void sendCallback(ResponseCallback callback, boolean status, T response, PalmyraException ex);

    /**
     * Deserialize t.
     *
     * @param <T>       the type parameter
     * @param response  the response
     * @param valueType the value type
     * @return the t
     * @throws PalmyraException the mobyra exception
     */
    protected final <T> T deserialize(Response response, Type valueType) throws PalmyraException {
        try {
            return this.deserialize(response.body().string(), valueType);
        } catch (IOException e) {
            throw new PalmyraException(PalmyraError.IO, e);
        }
    }

    /**
     * Deserialize t.
     *
     * @param <T>       the type parameter
     * @param response  the response
     * @param valueType the value type
     * @return the t
     * @throws IOException the io exception
     */
    protected final <T> T deserialize(String response, Type valueType) {
        return gson.fromJson(response, valueType);
    }

    /**
     * Gets type.
     *
     * @param rawClass  the raw class
     * @param parameter the parameter
     * @return the type
     */
    protected Type getType(Class<?> rawClass, Class<?> parameter) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{parameter};
            }

            @Override
            public Type getRawType() {
                return rawClass;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
    }

    private final Map<String, String> getAllRequestHeaders(Map<String, String> requestHeaders) {
        Map<String, String> headers = new HashMap<>();
        BaseDevice device = builder.getDevice();
        String deviceId = "";
        if (null != device) {
            deviceId = device.getDeviceId();
        }
        Map<String, String> authHeaders = getAuthClient().getHeaders(builder.getUserName(), builder.getPassword(), builder.getAppName(), deviceId);
        if (null != authHeaders) {
            for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }

        if (null != requestHeaders) {
            for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                headers.put(entry.getKey(), entry.getValue());
            }
        }

        return headers;
    }
}
