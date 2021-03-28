/**
 * <LICENSE/>
 */
package com.zitlab.palmyra.http;

import com.zitlab.palmyra.ResponseCallback;
import com.zitlab.palmyra.annotation.MobyraType;
import com.zitlab.palmyra.auth.AuthClient;
import com.zitlab.palmyra.builder.CriteriaBuilder;
import com.zitlab.palmyra.builder.PaginatedQueryFilter;
import com.zitlab.palmyra.pojo.Criteria;
import com.zitlab.palmyra.pojo.FieldCriteriaQueryFilter;
import com.zitlab.palmyra.pojo.QueryFilter;
import com.zitlab.palmyra.pojo.QueryResultSet;

import java.util.List;

import static com.zitlab.palmyra.util.StringUtil.FORWARD_SLASH;

/**
 * The type Tuple rest client.
 */
public abstract class PalmyraRestClient extends BaseRestClient {
    private static final String KEY_API = "api";
    private static final String KEY_QUERY = "query";
    private static final String KEY_UNIQUE = "unique";
    private static final String KEY_DATA = "data";
    private static final String KEY_LIST = "list";
    private static final String KEY_FIRST = "first";
    private static final String KEY_LIST_MULTI = "multi";
    private final String appName;
    private final String context;
    private final String apiVersion;
    private final AuthClient authClient;

    /**
     * Instantiates a new Tuple rest client.
     *
     * @param builder the builder
     */
    public PalmyraRestClient(MobyraClientBuilder builder) {
        super(builder);
        appName = builder.getAppName();
        context = builder.getContext();
        authClient = builder.getAuthClient();
        apiVersion = builder.getApiVersion();
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

    @Override
    protected AuthClient getAuthClient() {
        return authClient;
    }

    //----------------- QUERY and Paginated Response ----------------------

    /**
     * Query.
     *
     * @param <T>             the type parameter
     * @param criteriaBuilder the criteria builder
     * @param responseType    the response type
     * @param callback        the callback
     */
    public <T> void query(CriteriaBuilder criteriaBuilder, Class<T> responseType, ResponseCallback<QueryResultSet<T>> callback) {
        String type = getAnnotation(responseType);
        Criteria criteria = new Criteria();
        criteria.setCriteria(criteriaBuilder);
        post(pathUrl(type, null), criteria, getType(QueryResultSet.class, responseType), callback);
    }

    /**
     * Query.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the query filter
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void query(QueryFilter queryFilter, Class<T> responseType, ResponseCallback<QueryResultSet<T>> callback) {
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
    public <T> void query(PaginatedQueryFilter queryFilter, Class<T> responseType, ResponseCallback<QueryResultSet<T>> callback) {
        String type = getAnnotation(responseType.getEnclosingClass());
        post(pathUrl(type, null), queryFilter, responseType, callback);
    }

    //---------- Query data and response type is List without Pagination info -------------

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
                         ResponseCallback<List<T>> callback) {
        Criteria criteria = new Criteria();
        if (null != criteriaBuilder) {
            criteria.setCriteria(criteriaBuilder);
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
                         ResponseCallback<List<T>> callback) {
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
                         ResponseCallback<List<T>> callback) {
        post(listUrl(type), queryFilter, responseType, callback);
    }

    //----------- Get Record by Primary Key with column names and with all column names ------------

    /**
     * Find by id.
     *
     * @param <T>          the type parameter
     * @param id           the id
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void findById(String id, Class<T> responseType, ResponseCallback<T> callback) {
        String type = getAnnotation(responseType);
        get(pathUrl(type, id), responseType, callback);
    }

    /**
     * Find by id.
     *
     * @param <T>          the type parameter
     * @param id           the id
     * @param fields       the fields
     * @param responseType the value type
     * @param callback     the callback
     */
    public <T> void findById(String id, List<String> fields, Class<T> responseType, ResponseCallback<T> callback) {
        String type = getAnnotation(responseType);
        if (null != fields && fields.size() > 0) {
            post(pathUrl(type, id), fields, responseType, callback);
        } else {
            findById(id, responseType, callback);
        }
    }


    //--------------- Find Unique value using <Key, Value> or using query filter------------------

    /**
     * Find unique.
     *
     * @param <T>          the type parameter
     * @param queryFilter  the criteria builder
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void findUnique(FieldCriteriaQueryFilter queryFilter, Class<T> responseType, ResponseCallback callback) {
        String type = getAnnotation(responseType);
        post(uniqueUrl(type), queryFilter, responseType, callback);
    }

    /**
     * Query first.
     *
     * @param <T>          the type parameter
     * @param fields       the fields
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void queryFirst(List<String> fields, Class<T> responseType, ResponseCallback callback) {
        String type = getAnnotation(responseType);
        post(firstUrl(type), fields, responseType, callback);
    }

    /**
     * Save.
     *
     * @param <T>          the type parameter
     * @param objectToSave the object to save
     * @param responseType the response type
     * @param callback     the callback
     */
    public <T> void save(Object objectToSave, Class<T> responseType, ResponseCallback callback) {
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
    public <T> void save(List<Object> objectListToSave, Class<T> responseType, ResponseCallback callback) {
        String type = getAnnotation(responseType);
        post(dataUrlMulti(type), objectListToSave, responseType, callback);
    }

    public <T> void delete(String id, Class<T> valueType, ResponseCallback callback) {
        String type = getAnnotation(valueType);
        delete(pathUrl(type, id), valueType, callback);
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
     * First url string.
     *
     * @param type the type
     * @return the string
     */
    protected String firstUrl(final String type) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_QUERY);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type);
        }
        sb.append(FORWARD_SLASH).append(KEY_FIRST);

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

    /**
     * Data url multi string.
     *
     * @param type the type
     * @return the string
     */
    protected String dataUrlMulti(final String type) {
        StringBuilder sb = getContextPath();
        sb.append(FORWARD_SLASH).append(KEY_DATA);
        if (null != type) {
            sb.append(FORWARD_SLASH).append(type)
                    .append(FORWARD_SLASH)
                    .append(KEY_LIST_MULTI);
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

}
