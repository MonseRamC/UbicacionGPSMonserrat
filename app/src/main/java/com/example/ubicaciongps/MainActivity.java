package com.example.ubicaciongps;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvLatitude;
    private TextView tvLongitude;
    private Button btnGenerarUbicacion;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongitude = findViewById(R.id.tv_longitude);
        btnGenerarUbicacion = findViewById(R.id.btn_generar_ubicacion);
        context = getApplicationContext();

        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        btnGenerarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if(location != null){
                        double longitude = location.getLongitude();
                        double latitude = location.getLatitude();

                        tvLatitude.setText("Latitude : " + String.valueOf(latitude));
                        tvLongitude.setText("Longitude : " + String.valueOf(longitude));
                    }
                    else{
                        Toast.makeText(context,"Error de GPS",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}