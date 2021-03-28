package com.zitlab.palmyra.exception;

/**
 * The type Mobyra exception.
 */
public class PalmyraException extends Exception {
    private int code;

    /**
     * Instantiates a new Http exception.
     *
     * @param error the error
     * @param ex    the ex
     */
    public PalmyraException(PalmyraError error, Throwable ex) {
        super(ex);
        this.code = error.getCode();
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

}
