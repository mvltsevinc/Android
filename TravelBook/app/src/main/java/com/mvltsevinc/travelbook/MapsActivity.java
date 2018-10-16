package com.mvltsevinc.travelbook;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    static SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                SharedPreferences sharedPreferences = MapsActivity.this.getSharedPreferences("com.mvltsevinc.travelbook",MODE_PRIVATE);
                boolean firstTimeCheck = sharedPreferences.getBoolean("notFirstTime",false);
                // Surekli konumu almamasi icin bunu yaptim.
                if(!firstTimeCheck) {
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,10));
                    System.out.println("Location" + location);
                    sharedPreferences.edit().putBoolean("notFirstTime",true).apply();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        /* Harita kullanımı icin izin alma */
        if(Build.VERSION.SDK_INT >=23){
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // izin yoksa
                // izin iste
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else{
                // izin varsa kullanıcın konumunu almaya basla
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,10000,20,locationListener);
                mMap.clear();
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,10));
                }
            }
        }else{
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            mMap.clear();
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null) {
                LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,10));
            }
        }
        /* Harita kullanımı icin izin alma */
    }

    /* Izin verilince calisan method */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length > 0 && requestCode == 1){
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                // izin verilince yapılacaklar
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                // Son lokasyonu alma
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,10));
                }
            }
        }
    }
    /* Izin verilince calisan method */
    @Override
    public void onMapLongClick(LatLng latLng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        String address = "";
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
            if(addressList!=null && addressList.size() > 0) {
                address += addressList.get(0).getAddressLine(0).toString();
            }else{
                address += "New Place";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        mMap.addMarker(new MarkerOptions().title(address).position(latLng));
        Toast.makeText(getApplicationContext(),"New Place Added!",Toast.LENGTH_LONG).show();

        // Adresi Db ye kaydet
        try {
            Double lat = latLng.latitude;
            Double lng = latLng.longitude;
            String latStr = lat.toString();
            String lngStr = lng.toString();

            database = this.openOrCreateDatabase("Places",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS places(name VARCHAR,latitude VARCHAR,longitude VARCHAR)");
            String sql = "INSERT INTO places(name,latitude,longitude) VALUES (?,?,?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sql);
            sqLiteStatement.bindString(1,address);
            sqLiteStatement.bindString(2,latStr);
            sqLiteStatement.bindString(3,lngStr);
            sqLiteStatement.execute();
        }catch (Exception e){

        }
    }

}
