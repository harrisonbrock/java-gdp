package com.harrisonbrock.javajdp.exceptions;

public class NationNotFoundException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public NationNotFoundException( Long id) {
        super("Could not find Nation with id: " + id);
    }
}
