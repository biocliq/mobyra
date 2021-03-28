package com.zitlab.palmyra.lib;

import com.zitlab.palmyra.http.MobyraClientBuilder;
import com.zitlab.palmyra.exception.PalmyraException;
import com.zitlab.palmyra.http.PalmyraRestClient;

/**
 * The type Mobyra client.
 */
public final class PalmyraClient extends PalmyraRestClient {


    /**
     * Instantiates a new Mobyra client.
     *
     * @param builder the builder
     */
    public PalmyraClient(MobyraClientBuilder builder) {
        super(builder);
    }


    @Override
    protected <T> void sendCallback(ResponseCallback callback, boolean status, T response, PalmyraException ex) {
        callback.onMobyraResponse(status, response, ex);
    }

}
