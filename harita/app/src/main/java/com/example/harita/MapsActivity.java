package com.example.harita;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
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
        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener =new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                //mMap.clear(); dememizin sebebi her bir konum değiştiğinde bir önceki konumu silmek için.
               /* mMap.clear();
                LatLng kullaniciKonum=new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(kullaniciKonum).title("Konumunuz"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kullaniciKonum ,15));

                */

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

        //Kontrol olumzsuz ise izin istemeliyiz..

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            sonKonum();
        }else{
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            //burada ne kadar bi sürede ve uzaklıkta locakyonu güncellemesi gerektiğini belirtiyoruz
            //0 0 yaparsak eğer sürekli yapar ama efektif değildir çünkü kullanıcının pilinden çok yer.

            sonKonum();


        }

       mMap.setOnMapLongClickListener(this);
}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length>0){
            if(requestCode==1){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED);
                locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER,0,0,locationListener);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    public void sonKonum(){
        Location sonKonum=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        System.out.println("Son konumunuz: "+sonKonum);
        LatLng kullaniciSonKonum=new LatLng(sonKonum.getLatitude(),sonKonum.getLongitude());
        mMap.addMarker(new MarkerOptions().title("Pozisyonun").position(kullaniciSonKonum));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kullaniciSonKonum,15));

    }

    //Haritaya uzun basıldığında ne olacağını yapabilmemiz için bunu implement ettik
    //Ve bunu implement ettikten sonra bize bi fonk. tanımladı
    @Override
    public void onMapLongClick(LatLng latLng) {


        mMap.clear();
        Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());

        String adres="";

        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if(addressList!=null && addressList.size()>0){
                if(addressList.get(0).getThoroughfare()!=null){
                    adres+=addressList.get(0).getThoroughfare();

                    if (addressList.get(0).getSubThoroughfare()!=null){
                        adres += addressList.get(0).getSubThoroughfare();

                    }
                }
            }
        }catch(IOException e) {
            e.getStackTrace();
        }

        if(adres.matches("")){
            adres="Adres Yok";
        }

        mMap.addMarker(new MarkerOptions().position(latLng).title(adres));
    }
}
