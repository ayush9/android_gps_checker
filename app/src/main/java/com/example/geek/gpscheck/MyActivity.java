package com.example.geek.gpscheck;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MyActivity extends Activity {
    Boolean isInternetPresent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    public void checkC(View v) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            Toast.makeText(this, "you are connected to internet", Toast.LENGTH_LONG).show();
            boolean isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
            if (isWiFi) {
                Toast.makeText(this, "you are dependent on wifi for internet ", Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyActivity.this);


            alertDialog.setTitle("Internet is OFF...");


            alertDialog.setMessage("Are you sure you want to ON it?");


            alertDialog.setIcon(R.drawable.ic_launcher);


            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                    Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    startActivity(intent);
                }
            });


            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    finish();
                }
            });


            alertDialog.show();
        }

    }

    public void gpsCheck(View v)
    {
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            Toast.makeText(this, "GPS is Enabled in your device", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ShowLocationActivity.class);
            startActivity(intent);
        }
        else
        {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyActivity.this);


            alertDialog.setTitle("GPS is OFF...");


            alertDialog.setMessage("Are you sure you want to ON it?");


            alertDialog.setIcon(R.drawable.ic_launcher);


            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {


                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });


            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                    finish();
                }
            });


            alertDialog.show();
        }
    }
    public void onStop()
    {
        super.onStop();
    }
public void onDestroy()
{
    super.onDestroy();
}



    @Override
    public boolean onOptionsItemSelected (MenuItem item){
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
