package com.zitlab.palmyra;

import com.zitlab.palmyra.exception.PalmyraException;
import com.zitlab.palmyra.http.ResponseStatus;

/**
 * The interface Mobyra response callback.
 *
 * @param <T> the type parameter
 */
public interface ResponseCallback<T> {

    /**
     * On mobyra response.
     *
     * @param status    the status
     * @param response  the response
     * @param exception the exception
     */
    public void onMobyraResponse(ResponseStatus status, T response, PalmyraException exception);

    public default void onFailure(ResponseStatus status, RuntimeException e){
        throw e;
    }
}
