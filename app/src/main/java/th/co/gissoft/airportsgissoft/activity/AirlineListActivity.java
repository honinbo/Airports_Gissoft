package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.adapter.AirlinesAdapter;
import th.co.gissoft.airportsgissoft.data.Airline;
import th.co.gissoft.airportsgissoft.data.DbField;

public class AirlineListActivity extends ActionBarActivity {

    private ActionBar mActionbar;
    private AirlinesAdapter mAirlinesAdapter;
    private int mAirportId;
    private ListView mListview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setdata();
        setContentView(R.layout.activity_airline_list);

        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);
        mActionbar.setTitle(getResources().getString(R.string.airline));

        mListview = (ListView) findViewById(R.id.listView_airlines);
        mListview.setAdapter(mAirlinesAdapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Airline airline = (Airline) mAirlinesAdapter.getItem(position);
                Intent intent = new Intent(getApplicationContext(), AirlineDetailActivity.class);
                intent.putExtra(DbField.AIRPORTS_AIRPORTID, mAirportId);
                intent.putExtra(DbField.AIRLINES_AIRLINEID, airline.getAirlineid());
                intent.putExtra(DbField.AIRLINES_NAME, airline.getName());
                intent.putExtra(DbField.AIRLINES_IATA, airline.getIata());
                startActivity(intent);
            }
        });
    }

    private void setdata() {
        Intent intent = getIntent();
        mAirportId = intent.getIntExtra(DbField.AIRPORTS_AIRPORTID, 0);
        mAirlinesAdapter  = new AirlinesAdapter(getApplicationContext(), mAirportId);

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
