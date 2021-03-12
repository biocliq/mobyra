package com.zitlab.mobyra.library.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.zitlab.mobyra.library.MobyraResponseCallback;
import com.zitlab.mobyra.library.builder.MobyraClientBuilder;
import com.zitlab.mobyra.library.exception.MobyraError;
import com.zitlab.mobyra.library.exception.MobyraException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

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

    private Gson gson = new Gson();
    private MobyraClientBuilder builder;

    /**
     * The constant JSON.
     */
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");


    /**
     * Instantiates a new Base rest client.
     *
     * @param builder the builder
     */
    public BaseRestClient(MobyraClientBuilder builder){
        this.builder = builder;
    }

    /**
     * Sets authentication.
     *
     * @param authHeaders the auth headers
     */
    protected abstract void setAuthentication(final Map<String, String> authHeaders);

    private OkHttpClient getNewHttpClient(){
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
     * Get response.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void get(final String path, final Class<T> responseType, final MobyraResponseCallback callback) {
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
     * @param <T>          the type parameter
     * @param path         the path
     * @param obj          the obj
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void post(final String path, final Object obj, final Class<T> responseType, final MobyraResponseCallback callback){
        String requestBody = gson.toJson(obj);
        this.post(path, requestBody, responseType, callback);
    }

    /**
     * Post response.
     *
     * @param <T>          the type parameter
     * @param path         the path
     * @param jsonData     the json data
     * @param responseType the response type
     * @param callback     the callback
     * @return the response
     * @throws IOException the io exception
     */
    protected <T> void post(final String path, final String jsonData, final Class<T> responseType, MobyraResponseCallback callback){

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
    protected <T> void put(final String path, final Object obj, final Class<T> responseType, final MobyraResponseCallback callback){
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
    protected <T> void put(final String path, final String jsonData, final Class<T> responseType, MobyraResponseCallback callback){

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
    protected <T> void delete(final String path, final Class<T> responseType, final MobyraResponseCallback callback){

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
                        sendCallbackOnUIThread(callback, true, obj, null);
                        //callback.onMobyraResponse(true, obj, null);
                    } catch (MobyraException e) {
                        e.printStackTrace();
                        sendCallbackOnUIThread(callback, false, null, e);
                        //callback.onMobyraResponse(false, null, e);
                    }
                }else{
                    sendCallbackOnUIThread(callback, false, null, new MobyraException(response.code(), response.message()));
                    //callback.onMobyraResponse(false, null, new MobyraException(response.code(), response.message()));
                }
            }
        });
    }

    /**
     * Sends callback on main thread.
     *
     * @param callback
     * @param status
     * @param response
     * @param ex
     * @param <T>
     */
    private <T> void sendCallbackOnUIThread(MobyraResponseCallback callback, boolean status, T response, MobyraException ex){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onMobyraResponse(status, response, ex);
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
