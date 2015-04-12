package edu.cmu.myapplication.ui;


import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import edu.cmu.myapplication.R;
import edu.cmu.myapplication.exception.LocatorException;
import edu.cmu.myapplication.model.Locator;

public class MainActivity extends ActionBarActivity {


    private ImageButton mButton;
    private LocationManager locationmanager;


    private static final long DISTANCE_DELTA = 0; 															// Meters
    private static final long DELTA_MIN = 100;
    private static final String Phone_Number = "4124824207";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (ImageButton) findViewById(R.id.imageButton);
        locationmanager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // get the updated locations
        locationmanager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                DELTA_MIN,
                DISTANCE_DELTA,
                new Locator(MainActivity.this));



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsLocation(Phone_Number);
            }
        });
    }



    // text the location
    protected void smsLocation(String number) {
        Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        // for self healing: try 5 times and quit after that
        boolean validLocation = false;
        int retryCount = 5;

        while( !validLocation && (retryCount!=0))  {

            try {
                String message = String.format(
                        "You are located at \n Longitude: %1$s, Latitude: %2$s",
                        location.getLongitude(), location.getLatitude());

                SmsManager sManager = SmsManager.getDefault();
                sManager.sendTextMessage(number, null, message, null, null);

                validLocation = true;

                Toast.makeText(MainActivity.this, message +
                        "\n\nGeo Coordinate sent to :  "
                        + number, Toast.LENGTH_LONG).show();



            } catch (NullPointerException e) {

                LocatorException excp = new LocatorException(MainActivity.this, e.getMessage());

                retryCount--;

            }
            finally{

                if(!validLocation){


                    Toast.makeText(MainActivity.this, "Location finder is shutting down", Toast.LENGTH_SHORT).show();
                    System.exit(0);

                }

            }
        }

    }


}
