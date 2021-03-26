package com.zitlab.mobyra.library;

import com.zitlab.mobyra.library.exception.MobyraException;

/**
 * The interface Mobyra response callback.
 *
 * @param <T> the type parameter
 */
public interface MobyraResponseCallback<T> {

    /**
     * On mobyra response.
     *
     * @param status    the status
     * @param response  the response
     * @param exception the exception
     */
    void onMobyraResponse(boolean status, T response, MobyraException exception);
}
