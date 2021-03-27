package com.zitlab.mobyra.library;

import android.os.Handler;
import android.os.Looper;

import com.zitlab.mobyra.library.builder.MobyraClientBuilder;
import com.zitlab.mobyra.library.exception.PalmyraException;
import com.zitlab.mobyra.library.http.PalmyraRestClient;

/**
 * The type Mobyra client.
 */
public final class MobyraClient extends PalmyraRestClient {


    /**
     * Instantiates a new Mobyra client.
     *
     * @param builder the builder
     */
    public MobyraClient(MobyraClientBuilder builder) {
        super(builder);
    }


    @Override
    protected <T> void sendCallback(ResponseCallback callback, boolean status, T response, PalmyraException ex) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callback.onMobyraResponse(status, response, ex);
            }
        });
    }

}
