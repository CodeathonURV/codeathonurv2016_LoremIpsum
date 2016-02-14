package com.codeathonurv2016.loremipsum.welcomeurv;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Context h;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //guardo el context
        h=this;

        //cargo el mapa y espero a que cargue completo
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //guardo el googleMap y cargo el tipo de mapa
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        //compruebo los permisos y si no los tengo pregunto
        if (ActivityCompat.checkSelfPermission(h, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(h, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            //

        }else {
            //inicio la localizacion si tengo permisos
            mMap.setMyLocationEnabled(true);
        }
        //creo un Latlng para guardar las cordenadas de tarragona y guardo el nivel de zoom del mapa
        LatLng latlng = new LatLng(41.1166667, 1.25);
        float zoomLevel = 14;
        //coloco la camara en la posicion de LatLng con el zoom
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel));
        //añado los dos marcadores
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.13105158687513, 1.2439837155829814)).title("URV").snippet("sant"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41.12288519324477, 1.249313264895573)).title("URV").snippet("URV"));

        //creo un listener para saber cuando precionan los marcadores y mostrar el mapa del campus seleccionado
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getSnippet().equals("sant")) {
                    startActivity(new Intent(h, ImagenUrv.class));
                }
                return false;
            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        //comprueba que numero de permiso es
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                //comprueba si a sido aceptado y activa la localizacion
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                       
                        return;
                    }
                    mMap.setMyLocationEnabled(true);

                } else {
                    Toast.makeText(this, "permiso no aceptado", Toast.LENGTH_LONG).show();
                }

            }
        }
    }



}
