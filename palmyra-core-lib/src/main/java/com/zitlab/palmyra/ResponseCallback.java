package com.zitlab.palmyra;

import com.zitlab.palmyra.exception.PalmyraException;

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
    void onMobyraResponse(boolean status, T response, PalmyraException exception);
}
