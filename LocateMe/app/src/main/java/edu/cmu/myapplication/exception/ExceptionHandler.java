package edu.cmu.myapplication.exception;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by vinaypalakkode on 4/11/15.
 */
public class ExceptionHandler {

    String message;

    public ExceptionHandler(Context context, String message){

        Log.e("LocateMe: ",message);
        Toast.makeText(context,"Exception: " + message, Toast.LENGTH_LONG).show();
    }

}
