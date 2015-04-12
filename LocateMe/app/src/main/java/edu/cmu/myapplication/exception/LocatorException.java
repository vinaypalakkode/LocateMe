package edu.cmu.myapplication.exception;

import android.content.Context;

/**
 * Created by vinaypalakkode on 4/11/15.
 */
public class LocatorException extends Exception{

    private String message = null;

    public LocatorException() {
        super();
    }

    public LocatorException(Context context,String message) {
        super(message);

        ExceptionHandler handler = new ExceptionHandler(context, message);

    }

    public LocatorException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
