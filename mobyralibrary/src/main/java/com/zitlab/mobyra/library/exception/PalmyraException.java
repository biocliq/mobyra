package com.zitlab.mobyra.library.exception;

import android.content.Context;

/**
 * The type Mobyra exception.
 */
public class PalmyraException extends Exception {
    private int code;
    private int msgResId;

    /**
     * Instantiates a new Http exception.
     *
     * @param error the error
     * @param ex    the ex
     */
    public PalmyraException(PalmyraError error, Throwable ex) {
        super(ex);
        this.code = error.getCode();
        this.msgResId = error.getMessageResourceId();
    }

    /**
     * Instantiates a new Mobyra exception.
     *
     * @param message the message
     */
    public PalmyraException(String message) {
        super(message);
        this.code = -1;
    }

    /**
     * Instantiates a new Mobyra exception.
     *
     * @param code    the code
     * @param message the message
     */
    public PalmyraException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new Http exception.
     *
     * @param ex the ex
     */
    public PalmyraException(Throwable ex) {
        super(ex);
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @param context the context
     * @return the message
     */
    public String getResourceMessage(Context context) {
        if (context != null) {
            return context.getResources().getString(msgResId);
        } else return null;
    }

}
