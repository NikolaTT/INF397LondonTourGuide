package aubg.edu.londontourguide;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng londonEye = new LatLng(51.500858, -0.119675);
        LatLng bigBen = new LatLng(51.5007359, -0.1246254);
        LatLng towerBridge = new LatLng(51.505406, -0.075405);
        LatLng buckinghamPalace = new LatLng(51.501319, -0.141863);
        LatLng westminsterAbbey = new LatLng(51.499419, -0.127571);

        mMap.addMarker(new MarkerOptions().position(londonEye).title("London Eye"));
        mMap.addMarker(new MarkerOptions().position(bigBen).title("Big Ben"));
        mMap.addMarker(new MarkerOptions().position(towerBridge).title("Tower Bridge"));
        mMap.addMarker(new MarkerOptions().position(buckinghamPalace).title("Buckingham Palace"));
        mMap.addMarker(new MarkerOptions().position(westminsterAbbey).title("Westminster Abbey"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(bigBen));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));

    }
}
