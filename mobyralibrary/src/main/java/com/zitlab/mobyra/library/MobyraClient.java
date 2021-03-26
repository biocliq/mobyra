package com.zitlab.mobyra.library;

import android.util.Pair;

import com.zitlab.mobyra.library.annotation.MobyraType;
import com.zitlab.mobyra.library.builder.CriteriaBuilder;
import com.zitlab.mobyra.library.builder.MobyraClientBuilder;
import com.zitlab.mobyra.library.builder.PaginatedQueryFilter;
import com.zitlab.mobyra.library.http.TupleRestClient;
import com.zitlab.mobyra.library.pojo.Criteria;
import com.zitlab.mobyra.library.pojo.QueryFilter;
import com.zitlab.mobyra.library.pojo.QueryResultSet;

import java.util.List;

/**
 * The type Mobyra client.
 */
public class MobyraClient extends TupleRestClient {


    /**
     * Instantiates a new Mobyra client.
     *
     * @param builder the builder
     */
    public MobyraClient(MobyraClientBuilder builder) {
        super(builder);
    }

    /**
     * @param t
     * @return
     */
    private static String getAnnotation(Class<?> t) {
        if (null != t && t.isAnnotationPresent(MobyraType.class)) {
            MobyraType type = t.getAnnotation(MobyraType.class);
            return type.value();
        } else {
            return t.getSimpleName();
        }
    }

    //---------- QUERY and Paginated Response ----------------------

    /**
     * Query.
     *
     * @param <T>             the type parameter
     * @param criteriaBuilder the criteria builder
     * @param responseType    the response type
     * @param callback        the callback
     */
    public <T> void query(CriteriaBuilder criteriaBuilder, Class<T> responseType, MobyraResponseCallback callback) {
        String type = getAnnotation(responseType);
        Criteria criteria = new Criteria();
        criteria.setCriteria(criteriaBuilder.getCriteriaMap());
        post(pathUrl(type, null), criteria, responseType, callback);
    }

    /**
     * Query.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the query filter
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void query(QueryFilter queryFilter, Class<QueryResultSet<T>> responseType, MobyraResponseCallback<QueryResultSet<T>> callback) {
        String type = getAnnotation(responseType.getEnclosingClass());
        post(pathUrl(type, null), queryFilter, responseType, callback);
    }

    /**
     * Query.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the query filter
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void query(PaginatedQueryFilter queryFilter, Class<QueryResultSet<T>> responseType, MobyraResponseCallback<QueryResultSet<T>> callback) {
        String type = getAnnotation(responseType.getEnclosingClass());
        post(pathUrl(type, null), queryFilter, responseType, callback);
    }

    //---------- Query data and response type is List without Pagination info.

    /**
     * List.
     *
     * @param <T>             the type parameter
     * @param criteriaBuilder the criteria builder
     * @param type            the type
     * @param responseType    the response type
     * @param callback        the callback
     */
    public <T> void list(CriteriaBuilder criteriaBuilder, String type, Class<List<T>> responseType,
                         MobyraResponseCallback<List<T>> callback) {
        Criteria criteria = new Criteria();
        if (null != criteriaBuilder) {
            criteria.setCriteria(criteriaBuilder.getCriteriaMap());
        }
        post(listUrl(type), criteria, responseType, callback);
    }

    /**
     * List.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the query filter
     * @param type         the type
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void list(QueryFilter queryFilter, String type, Class<List<T>> responseType,
                         MobyraResponseCallback<List<T>> callback) {
        post(listUrl(type), queryFilter, responseType, callback);
    }

    /**
     * List.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the query filter
     * @param type         the type
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void list(PaginatedQueryFilter queryFilter, String type, Class<List<T>> responseType,
                         MobyraResponseCallback<List<T>> callback) {
        post(listUrl(type), queryFilter, responseType, callback);
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
        get(pathUrl(type, id), valueType, callback);
    }

    /**
     * Find unique.
     *
     * @param <T>       the type parameter
     * @param keyValue  the key value
     * @param valueType the value type
     * @param callback  the callback
     */
    public <T> void findUnique(Pair<String, String> keyValue, Class<T> valueType, MobyraResponseCallback callback) {
        CriteriaBuilder criteriaBuilder = new CriteriaBuilder.Builder().keyValue(keyValue.first, keyValue.second).build();
        findUnique(criteriaBuilder, valueType, callback);
    }

    /**
     * Find unique.
     *
     * @param <T>             the type parameter
     * @param criteriaBuilder the criteria builder
     * @param responseType    the response type
     * @param callback        the callback
     */
    public <T> void findUnique(CriteriaBuilder criteriaBuilder, Class<T> responseType, MobyraResponseCallback callback) {
        String type = getAnnotation(responseType);
        Criteria criteria = new Criteria();
        criteria.setCriteria(criteriaBuilder.getCriteriaMap());
        post(uniqueUrl(type), criteria, responseType, callback);
    }

    /**
     * Save.
     *
     * @param <T>          the type parameter
     * @param objectToSave the object to save
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void save(Object objectToSave, Class<T> responseType, MobyraResponseCallback callback) {
        String type = getAnnotation(responseType);
        post(dataUrl(type), objectToSave, responseType, callback);
    }

    /**
     * Save.
     *
     * @param <T>              the type parameter
     * @param objectListToSave the object list to save
     * @param responseType     the response type
     * @param callback         the callback
     */
    public <T> void save(List<Object> objectListToSave, Class<T> responseType, MobyraResponseCallback callback) {
        String type = getAnnotation(responseType);
        post(dataUrl(type), objectListToSave, responseType, callback);
    }

    public <T> void delete(String id, Class<T> valueType, MobyraResponseCallback callback) {
        String type = getAnnotation(valueType);
        delete(pathUrl(type, id), valueType, callback);
    }

}
