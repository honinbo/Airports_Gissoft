package th.co.gissoft.airportsgissoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Point;

import th.co.gissoft.airportsgissoft.data.Airport;
import th.co.gissoft.airportsgissoft.data.AppConfig;
import th.co.gissoft.airportsgissoft.data.DbField;


public class AirportDetailActivity extends ActionBarActivity {

    private MapView mMapview;
    private ArcGISTiledMapServiceLayer mBaseMaplayer;
    private ActionBar mActionbar;
    private TextView mTxtName;
    private TextView mTxtCity;
    private TextView mTxtCountry;
    private TextView mTxtIATA;
    private TextView mTxtAirlines;

    private Airport mAirport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_detail);

        mBaseMaplayer = new ArcGISTiledMapServiceLayer(AppConfig.url_basemap);
        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);

        // set map
        mMapview = (MapView) findViewById(R.id.mapView);
        mMapview.addLayer(mBaseMaplayer);

        mMapview.enableWrapAround(true);



        //create Airport object
        mAirport = new Airport();

        // getdata from Intent
        Intent intent = getIntent();
        mAirport.setAirportid(intent.getIntExtra(DbField.AIRPORTS_AIRPORTID, 0));
        mAirport.setName(intent.getStringExtra(DbField.AIRPORTS_NAME));
        mAirport.setCity(intent.getStringExtra(DbField.AIRPORTS_CITY));
        mAirport.setCountry(intent.getStringExtra(DbField.AIRPORTS_COUNTRY));
        mAirport.setIata(intent.getStringExtra(DbField.AIRPORTS_IATA));
        mAirport.setLat(intent.getDoubleExtra(DbField.AIRPORTS_LAT, 0));
        mAirport.setLon(intent.getDoubleExtra(DbField.AIRPORTS_LON, 0));

//        mMapview.centerAt(mAirport.getLon(), mAirport.getLon(), true);


        mMapview.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {
                Point point = new Point(mAirport.getLon(), mAirport.getLat());
                mMapview.centerAt(mAirport.getLat(), mAirport.getLon(), true);

                double[] info = mBaseMaplayer.getTileInfo().getResolutions();
                double resolution = info[16];
                mMapview.zoomToResolution(null, resolution);
            }
        });

        setTextViewData();

        mActionbar.setTitle(mAirport.getName());

        mMapview.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(float v, float v2) {
                mMapview.centerAt(mAirport.getLat(), mAirport.getLon(), true);

            }
        });
    }





    private void setTextViewData() {
        //set reference to UI
        mTxtName = (TextView) findViewById(R.id.txt_name);
        mTxtCity = (TextView) findViewById(R.id.txt_city);
        mTxtCountry = (TextView) findViewById(R.id.txt_country);
        mTxtIATA = (TextView) findViewById(R.id.txt_iata);
        mTxtAirlines = (TextView) findViewById(R.id.txt_airlines);

        //set data to textview
        mTxtName.setText(mAirport.getName());
        mTxtCity.setText(mAirport.getCity());
        mTxtCountry.setText(mAirport.getCountry());
        mTxtIATA.setText(mAirport.getIata());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapview.unpause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapview.pause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
