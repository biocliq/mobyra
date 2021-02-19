package com.zitlab.mobyra.library.exception;

import com.zitlab.mobyra.library.R;

/**
 * The enum Mobyra error.
 */
public enum MobyraError {

    /**
     * Malformed url mobyra error.
     */
    MalformedURL(1000, R.string.err1000),
    /**
     * Unsupported encoding mobyra error.
     */
    UnsupportedEncoding(1001, R.string.err1001),
    /**
     * Protocol mobyra error.
     */
    Protocol(1002, R.string.err1002),
    /**
     * Io mobyra error.
     */
    IO(1003, R.string.err1003);

    private final int code;
    private final int messageResourceId;

    MobyraError(int code, int messageResourceId) {
        this.code = code;
        this.messageResourceId = messageResourceId;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Gets message resource id.
     *
     * @return the message resource id
     */
    public int getMessageResourceId() {
        return messageResourceId;
    }

    @Override
    public String toString() {
        return code + ": " + messageResourceId;
    }
}
