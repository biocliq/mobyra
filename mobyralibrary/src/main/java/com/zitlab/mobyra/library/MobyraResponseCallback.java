package com.zitlab.mobyra.library;

import com.zitlab.mobyra.library.exception.MobyraException;

import okhttp3.Response;

/**
 * The interface Mobyra response callback.
 */
public interface MobyraResponseCallback {

    /**
     * On mobyra response.
     *
     * @param <T>       the type parameter
     * @param status    the status
     * @param response  the response
     * @param exception the exception
     */
    <T> void onMobyraResponse(boolean status, T response, MobyraException exception);
}
