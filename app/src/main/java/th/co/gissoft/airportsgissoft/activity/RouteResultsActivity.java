package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.adapter.RouteListAdapter;
import th.co.gissoft.airportsgissoft.data.DbField;
import th.co.gissoft.airportsgissoft.data.Routes;

public class RouteResultsActivity extends ActionBarActivity {

    private ExpandableStickyListHeadersListView listView;

    private String sourceCountry;
    private String destCountry;
    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_results);

        Intent in = getIntent();

        mActionbar = getSupportActionBar();
        mActionbar.setTitle(getResources().getString(R.string.airline));
        mActionbar.setDisplayHomeAsUpEnabled(true);

        sourceCountry = in.getStringExtra(DbField.ROUTES_SOURCE_AIRPORT_COUNTRY);
        destCountry = in.getStringExtra(DbField.ROUTES_DEST_AIRPORT_COUNTRY);

        listView = (ExpandableStickyListHeadersListView) findViewById(R.id.list_route_result);

        final RouteListAdapter routeListAdapter = new RouteListAdapter(getApplicationContext(), sourceCountry, destCountry);
        listView.setAdapter(routeListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Routes route = (Routes) routeListAdapter.getItem(position);

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MapActivity.class);

                intent.putExtra(DbField.AIRLINES_NAME, route.getAirlineName());
                intent.putExtra(DbField.ROUTES_SOURCE_AIRPORT_LAT, route.getSourcelat());
                intent.putExtra(DbField.ROUTES_SOURCE_AIRPORT_LON, route.getSourcelon());
                intent.putExtra(DbField.ROUTES_DEST_AIRPORT_LAT, route.getDestlat());
                intent.putExtra(DbField.ROUTES_DEST_AIRPORT_LON, route.getDestlon());
                startActivity(intent);
            }
        });


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
