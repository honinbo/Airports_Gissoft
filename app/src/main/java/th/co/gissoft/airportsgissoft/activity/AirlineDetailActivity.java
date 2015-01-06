package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.Utils;
import th.co.gissoft.airportsgissoft.adapter.AirlineDetailAdapter;
import th.co.gissoft.airportsgissoft.data.Airline;
import th.co.gissoft.airportsgissoft.data.DbField;

public class AirlineDetailActivity extends ActionBarActivity {

    private ActionBar mActionbar;
    private int mAirlineId;
    private int mAirportId;
    private String mAirlinename;
    private String mIATA;
    private Airline mAirline = new Airline();
    private AirlineDetailAdapter mAirlineDetailAdapter;

    // UI
    private ListView mListView;
    private TextView mTxtAirName;
    private TextView mTxtIATA;
    private ImageView mImgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airline_detail);

        Intent intent = getIntent();
        mAirportId = intent.getIntExtra(DbField.AIRPORTS_AIRPORTID, 0);
        mAirlineId = intent.getIntExtra(DbField.AIRLINES_AIRLINEID, 0);
        mAirlinename = intent.getStringExtra(DbField.AIRLINES_NAME);
        mIATA = intent.getStringExtra(DbField.AIRLINES_IATA);

        mActionbar = getSupportActionBar();
        mActionbar.setDisplayHomeAsUpEnabled(true);
        mActionbar.setTitle(mAirlinename);

        mTxtAirName = (TextView) findViewById(R.id.txt_airlines);
        mTxtIATA = (TextView) findViewById(R.id.txt_iata);
        mImgLogo = (ImageView) findViewById(R.id.img_airline_logo);
        mListView = (ListView) findViewById(R.id.list_airlines_details);

        mTxtAirName.setText(mAirlinename);
        mTxtIATA.setText(mIATA);

        Bitmap picLogo = new Utils(getApplicationContext()).getPictureFromAssets(mIATA);
        mImgLogo.setImageBitmap(picLogo);
        if (picLogo == null) {
            mImgLogo.setLayoutParams(new LinearLayout.LayoutParams(180, 50));
        }

        mAirlineDetailAdapter = new AirlineDetailAdapter(this, mAirlineId, mAirportId, mAirlinename, mIATA);
        mListView.setAdapter(mAirlineDetailAdapter);



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
