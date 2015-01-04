package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.adapter.AirlinesAdapter;
import th.co.gissoft.airportsgissoft.data.DbField;

public class AirlineListActivity extends ActionBarActivity {

    private ActionBar mActionbar;
    private AirlinesAdapter mAirlinesAdapter;

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
    }

    private void setdata() {
        Intent intent = getIntent();
        int airportId = intent.getIntExtra(DbField.AIRPORTS_AIRPORTID, 0);
        mAirlinesAdapter  = new AirlinesAdapter(getApplicationContext(), airportId);

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