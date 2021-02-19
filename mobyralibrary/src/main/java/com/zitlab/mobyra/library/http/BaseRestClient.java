package com.zitlab.mobyra.library.http;

import com.google.gson.Gson;
import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.exception.MobyraError;
import com.zitlab.mobyra.library.exception.MobyraException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
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

    private Gson gson = new Gson();

    /**
     * The constant JSON.
     */
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    /**
     * Sets authentication.
     *
     * @param authHeaders the auth headers
     */
    protected abstract void setAuthentication(final Map<String, String> authHeaders);

    /**
     * Sets log level.
     *
     * @param level the level
     */
    protected abstract void setLogLevel(final HttpLoggingInterceptor.Level level);


    private OkHttpClient getNewHttpClient(){
        return new OkHttpClient();
    }

    /**
     * Get response.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void get(final String url, final Class<T> responseType, final MobyraResponseCallback callback) {
        Request.Builder builder = getHttpBuilder();
        Request request = builder
                .url(url)
                .build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Post response.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param obj          the obj
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void post(final String url, final Object obj, final Class<T> responseType, final MobyraResponseCallback callback){
        String requestBody = gson.toJson(obj);
        this.post(url, requestBody, responseType, callback);
    }

    /**
     * Post response.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param jsonData     the json data
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void post(final String url, final String jsonData, final Class<T> responseType, MobyraResponseCallback callback){
        RequestBody body = RequestBody.create(jsonData, JSON);
        Request.Builder builder = getHttpBuilder();
        Request request = builder
                .url(url)
                .post(body)
                .build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Put.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param obj          the obj
     * @param responseType the response type
     * @param callback     the callback
     */
    protected <T> void put(final String url, final Object obj, final Class<T> responseType, final MobyraResponseCallback callback){
        String requestBody = gson.toJson(obj);
        this.put(url, requestBody, responseType, callback);
    }


    /**
     * Put.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param jsonData     the json data
     * @param responseType the response type
     * @param callback     the callback
     */
    protected <T> void put(final String url, final String jsonData, final Class<T> responseType, MobyraResponseCallback callback){
        RequestBody body = RequestBody.create(jsonData, JSON);
        Request.Builder builder = getHttpBuilder();
        Request request = builder
                .url(url)
                .put(body)
                .build();
        executeRequest(request, responseType, callback);
    }


    /**
     * Delete response.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void delete(final String url, final Class<T> responseType, final MobyraResponseCallback callback){
        Request.Builder builder = getHttpBuilder();
        Request request = builder
                .url(url)
                .delete()
                .build();
        executeRequest(request, responseType, callback);
    }


    private Request.Builder getHttpBuilder(){
        Request.Builder builder = new Request.Builder();
        Map<String, String> headers = new HashMap<>();
        setAuthentication(headers);
        for (Map.Entry<String,String> entry : headers.entrySet()){
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    private <T> void executeRequest(Request request, Class<T> valueType, MobyraResponseCallback callback){
        getNewHttpClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                callback.onMobyraResponse(false, Object.class, new MobyraException(e));
            }

            @Override public void onResponse(Call call, Response response) {
                boolean isSuccess = response.isSuccessful();
                if(isSuccess){
                    try {
                        T obj = deserialize(response, valueType);
                        callback.onMobyraResponse(true, obj, null);
                    } catch (MobyraException e) {
                        e.printStackTrace();
                        callback.onMobyraResponse(false, null, e);
                    }
                }else{
                    callback.onMobyraResponse(false, null, new MobyraException(response.code(), response.message()));
                }
            }
        });
    }

    /**
     * Deserialize t.
     *
     * @param <T>       the type parameter
     * @param response  the response
     * @param valueType the value type
     * @return the t
     * @throws MobyraException the mobyra exception
     */
    protected final <T> T deserialize(Response response, Class<T> valueType) throws MobyraException {
        try {
            return this.deserialize(response.body().string(), valueType);
        }catch (IOException e){
            throw  new MobyraException(MobyraError.IO, e);
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
    protected final <T> T deserialize(String response, Class<T> valueType) {
        return gson.fromJson(response, valueType);
    }
}
