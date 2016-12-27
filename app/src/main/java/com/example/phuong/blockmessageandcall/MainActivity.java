package com.example.phuong.blockmessageandcall;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap map;
    Marker marker;
    private Button mBtnClick;
    private ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnClick = (Button) findViewById(R.id.btnGoHere);
        showProgressBar();
        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMapAsync(this);

        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
                } else {
                    showCurrentLocation();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mProgress.dismiss();
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomIn());

        map.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(map.getCameraPosition().target);
                map.clear();
                map.addMarker(markerOptions);
            }
        });
    }

    public void showCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location lastLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if (lastLocation != null) {
            LatLng myLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(myLatLng, 13));
            CameraPosition myPosition = new CameraPosition.Builder()
                    .target(myLatLng).zoom(15).tilt(30).build();
            if (marker != null) {
                marker.remove();
            }
            marker = map
                    .addMarker(new MarkerOptions()
                            .position(myLatLng)
                            .title("you are here")
                            .icon(BitmapDescriptorFactory
                                    .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            map.animateCamera(CameraUpdateFactory
                    .newCameraPosition(myPosition));
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Please make sure you turned on location from setting, \n"
                            + "if so please wait few more sec and click again.",
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showProgressBar() {
                mProgress = new ProgressDialog(this);
                mProgress.setTitle("Map Loading ...");
                mProgress.setMessage("Please wait...");
                mProgress.setCancelable(false);
                mProgress.show();
           }
}
