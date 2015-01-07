package th.co.gissoft.airportsgissoft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import th.co.gissoft.airportsgissoft.activity.AirportDetailActivity;
import th.co.gissoft.airportsgissoft.activity.RoutesSearchActivity;
import th.co.gissoft.airportsgissoft.adapter.AirportListAdapter;
import th.co.gissoft.airportsgissoft.data.Airport;
import th.co.gissoft.airportsgissoft.data.AppConfig;
import th.co.gissoft.airportsgissoft.data.DbField;


public class MainActivity extends ActionBarActivity {

    private ExpandableStickyListHeadersListView listView;
    View listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.createPath(this);
        setContentView(R.layout.activity_main);

        listView = (ExpandableStickyListHeadersListView) findViewById(R.id.list_airports);

        final AirportListAdapter airportAdapter = new AirportListAdapter(this);
        listView.setAdapter(airportAdapter);

        listFragment = findViewById(R.id.fragment);
        listFragment.setVisibility(View.GONE);

        listView.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                listFragment.setVisibility(View.VISIBLE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), AirportDetailActivity.class);
                // put data to detailActivity
                Airport airport = (Airport) airportAdapter.getItem(position);
                intent.putExtra(DbField.AIRPORTS_AIRPORTID, airport.getAirportid());
                intent.putExtra(DbField.AIRPORTS_NAME, airport.getName());
                intent.putExtra(DbField.AIRPORTS_CITY, airport.getCity());
                intent.putExtra(DbField.AIRPORTS_COUNTRY, airport.getCountry());
                intent.putExtra(DbField.AIRPORTS_IATA, airport.getIata());
                intent.putExtra(DbField.AIRPORTS_LAT, airport.getLat());
                intent.putExtra(DbField.AIRPORTS_LON, airport.getLon());

                startActivity(intent);
            }
        });

        findViewById(R.id.btn_routes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(getApplicationContext(), RoutesSearchActivity.class);
                startActivity(in);
                finish();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }


    public void setJumpList(final int jumpList) {
        listFragment.setVisibility(View.GONE);
        listView.clearFocus();
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.setSelection(jumpList);
            }
        });
    }



}
