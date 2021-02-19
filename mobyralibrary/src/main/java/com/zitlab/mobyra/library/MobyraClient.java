package com.zitlab.mobyra.library;

import com.zitlab.mobyra.library.exception.MobyraException;
import com.zitlab.mobyra.library.http.TupleRestClient;
import com.zitlab.mobyra.library.pojo.Tuple;
import com.zitlab.mobyra.library.pojo.TupleFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Mobyra client.
 */
public class MobyraClient extends TupleRestClient {


    /**
     * Instantiates a new Mobyra client.
     *
     * @param baseUrl  the base url
     * @param username the username
     * @param password the password
     * @param appn     the appn
     */
    public MobyraClient(String baseUrl, String username, String password, String appn) {
        super(baseUrl, username, password, appn);
    }


    public <T> void delete(String id, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        delete(getUrl(type, id), valueType, callback);
    }

    /**
     * Find by id.
     *
     * @param <T>       the type parameter
     * @param id        the id
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void findById(String id, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        get(getUrl(type, id), valueType, callback);
    }

    /**
     * Find unique.
     *
     * @param <T>       the type parameter
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void findUnique(Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        get(uniqueUrl(type), valueType, callback);
    }

    /**
     * Query.
     *
     * @param <T>       the type parameter
     * @param filter    the filter
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void query(Object filter, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        post(queryUrl(type), filter, valueType, callback);
    }

    /**
     * List.
     *
     * @param <T>       the type parameter
     * @param filter    the filter
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void list(Object filter, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        post(listUrl(type),  filter, valueType, callback);
    }

    /**
     * Save.
     *
     * @param <T>       the type parameter
     * @param obj       the obj
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void save(Object obj, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        post(getUrl(type), obj, valueType, callback);
    }

    /**
     * Save.
     *
     * @param <T>       the type parameter
     * @param objs      the objs
     * @param type      the type
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void save(List<T> objs, String type, Class<List<T>> valueType,  MobyraResponseCallback callback) {
        post(getMultiUrl(type), objs, valueType, callback);
    }

    /**
     * Save.
     *
     * @param <T>       the type parameter
     * @param objs      the objs
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void save(List<Object> objs, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        post(getMultiUrl(type), objs, valueType, callback);
    }

    /**
     * Save.
     *
     * @param <T>       the type parameter
     * @param obj       the obj
     * @param valueType the value type
     * @param id        the id
     * @param callback  the callback
     * @throws MobyraException the mobyra exception
     */
    public <T> void  save(Object obj, Class<T> valueType, String id, MobyraResponseCallback callback) throws MobyraException {
        if (null == id)
            throw new MobyraException("'id' cannot be null while saving the data");
        String type = getAnnotation(valueType);
        post(getUrl(type, id), obj, valueType, callback);
    }

    /**
     * List.
     *
     * @param <T>       the type parameter
     * @param url       the url
     * @param obj       the obj
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void list(String url, Object obj, Class<List<T>> valueType, MobyraResponseCallback callback){
        post(customUrl(url), obj, valueType, callback);
    }

    /**
     * Post custom.
     *
     * @param <T>       the type parameter
     * @param url       the url
     * @param obj       the obj
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void postCustom(String url, Object obj, Class<T> valueType, MobyraResponseCallback callback){
        post(customUrl(url), obj, valueType, callback);
    }

    /**
     * Get custom.
     *
     * @param <T>       the type parameter
     * @param url       the url
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void getCustom(String url, Class<T> valueType, MobyraResponseCallback callback){
       get(customUrl(url), valueType, callback);
    }

    /**
     * Execute.
     *
     * @param <T>       the type parameter
     * @param action    the action
     * @param obj       the obj
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void execute(String action, Object obj, Class<T> valueType, MobyraResponseCallback callback){
        post(actionUrl(action), obj, valueType, callback);
    }

    private static String getAnnotation(Class<?> t) {
        if (t.isAnnotationPresent(MobyraType.class)) {
            MobyraType type = t.getAnnotation(MobyraType.class);
            return type.value();
        } else {
            return t.getSimpleName();
        }
    }

}
