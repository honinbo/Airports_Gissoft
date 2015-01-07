package th.co.gissoft.airportsgissoft.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import th.co.gissoft.airportsgissoft.DBmanager.DBmanager;
import th.co.gissoft.airportsgissoft.MainActivity;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.data.DbField;

public class RoutesSearchActivity extends ActionBarActivity {

    private ArrayList<String> mCoutryList;
    private Spinner mSpnSourceCountry;
    private Spinner mSpnDestCountry;
    private ActionBar mActionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes_search);

        mActionbar = getSupportActionBar();
        mActionbar.setTitle(getResources().getString(R.string.routes));

        mSpnSourceCountry = (Spinner) findViewById(R.id.spinner_source_country);
        mSpnDestCountry = (Spinner) findViewById(R.id.spinner_destination_country);

        mCoutryList = new DBmanager().getCountryList();
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mCoutryList);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpnSourceCountry.setAdapter(myAdapter);
        mSpnDestCountry.setAdapter(myAdapter);

        findViewById(R.id.btn_airports).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                in.setClass(getApplicationContext(), MainActivity.class);
                startActivity(in);
                finish();

                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_routes_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_search) {

            String sourceCountry = (String) mSpnSourceCountry.getSelectedItem();
            String destCountry = (String) mSpnDestCountry.getSelectedItem();

            Intent in = new Intent();
            in.setClass(getApplicationContext(), RouteResultsActivity.class);
            in.putExtra(DbField.ROUTES_SOURCE_AIRPORT_COUNTRY, sourceCountry);
            in.putExtra(DbField.ROUTES_DEST_AIRPORT_COUNTRY, destCountry);

            startActivity(in);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
