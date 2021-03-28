package com.zitlab.mobyra.library.exception;

/**
 * The enum Mobyra error.
 */
public enum PalmyraError {

    /**
     * Io mobyra error.
     */
    IO(1003);

    private final int code;

    PalmyraError(int code) {
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return this.code;
    }

}
