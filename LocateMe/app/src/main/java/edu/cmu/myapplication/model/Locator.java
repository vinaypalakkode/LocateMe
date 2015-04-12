package edu.cmu.myapplication.model;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;
import edu.cmu.myapplication.ui.MainActivity;

/**
 * Created by vinaypalakkode on 4/11/15.
 */
public class Locator implements LocationListener{



    private Context myContext;

    public Locator(Context context){


        this.myContext = context;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle b) {

    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(myContext,
                "GPS turned off. Please turn it on",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderEnabled(String s) {

        Toast.makeText(myContext,
                "GPS turned on. We are good.",
                Toast.LENGTH_LONG).show();

    }






}
