package com.acmesoft.stock;

public class SerializationException extends RuntimeException {

    public SerializationException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SerializationException(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SerializationException(String s, Throwable throwable) {
        super(s, throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public SerializationException(Throwable throwable) {
        super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
