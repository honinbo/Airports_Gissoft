package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnStatusChangedListener;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleLineSymbol;

import Gissoft.UnitAdapter.WebMercator;
import Gissoft.UnitConverter.CoordinateConverter;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.data.AppConfig;
import th.co.gissoft.airportsgissoft.data.DbField;

public class MapActivity extends ActionBarActivity {

    private ArcGISTiledMapServiceLayer mBaseMaplayer;
    private GraphicsLayer mGraphicsLayer;
    private ActionBar mActionbar;
    private MapView mMapview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mBaseMaplayer = new ArcGISTiledMapServiceLayer(AppConfig.url_basemap);
        mGraphicsLayer = new GraphicsLayer(GraphicsLayer.RenderingMode.DYNAMIC);
        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);

        // set map
        mMapview = (MapView) findViewById(R.id.mapView);
        mMapview.addLayer(mBaseMaplayer);
        mMapview.addLayer(mGraphicsLayer);

        mMapview.enableWrapAround(true);

        Intent intent = getIntent();

        String name = intent.getStringExtra(DbField.AIRLINES_NAME);
        final double sourceLat = intent.getDoubleExtra(DbField.ROUTES_SOURCE_AIRPORT_LAT, 0);
        final double sourceLon = intent.getDoubleExtra(DbField.ROUTES_SOURCE_AIRPORT_LON, 0);
        final double destLat = intent.getDoubleExtra(DbField.ROUTES_DEST_AIRPORT_LAT, 0);
        final double destLon = intent.getDoubleExtra(DbField.ROUTES_DEST_AIRPORT_LON, 0);

        mActionbar.setTitle(name);

        mMapview.setOnStatusChangedListener(new OnStatusChangedListener() {
            @Override
            public void onStatusChanged(Object o, STATUS status) {

                //Set point of location's airport
                WebMercator sourceMercator = CoordinateConverter.LLT2WebMercator(sourceLat, sourceLon);
                WebMercator destMercator = CoordinateConverter.LLT2WebMercator(destLat, destLon);

                Point sourcePoint = new Point(sourceMercator.X, sourceMercator.Y);
                Point destPoint = new Point(destMercator.X, destMercator.Y);

                Envelope extent = new Envelope();

                // Line route
                Polyline poly = new Polyline();
                poly.startPath(sourcePoint);
                poly.lineTo(destPoint);


                Graphic lineGraphic = new Graphic(poly, new SimpleLineSymbol(Color.BLUE, 5));
                mGraphicsLayer.addGraphic(lineGraphic);

                // Set Picture for pin
                PictureMarkerSymbol pictureSource = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.airport_n));
                PictureMarkerSymbol pictureDest = new PictureMarkerSymbol(getResources().getDrawable(R.drawable.target));


                // Pin graphic to graphic Layer
                Graphic sourceGraphic = new Graphic(sourcePoint, pictureSource);
                Graphic destGraphic = new Graphic(destPoint, pictureDest);

                extent.merge((Point)sourceGraphic.getGeometry());
                extent.merge((Point)destGraphic.getGeometry());

                mGraphicsLayer.addGraphic(sourceGraphic);
                mGraphicsLayer.addGraphic(destGraphic);

                mMapview.setExtent(extent, 250);



            }
        });
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
