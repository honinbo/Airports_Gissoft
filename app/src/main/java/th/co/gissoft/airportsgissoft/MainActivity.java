package th.co.gissoft.airportsgissoft;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import se.emilsjolander.stickylistheaders.ExpandableStickyListHeadersListView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import th.co.gissoft.airportsgissoft.data.AppConfig;


public class MainActivity extends ActionBarActivity {

    private ExpandableStickyListHeadersListView listView;
    View listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppConfig.createPath(this);
        setContentView(R.layout.activity_main);

        listView = (ExpandableStickyListHeadersListView) findViewById(R.id.list_airports);

        AirportListAdapter airportAdapter = new AirportListAdapter(this);
        listView.setAdapter(airportAdapter);

        listFragment = findViewById(R.id.fragment);
        listFragment.setVisibility(View.GONE);

        listView.setOnHeaderClickListener(new StickyListHeadersListView.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(StickyListHeadersListView l, View header, int itemPosition, long headerId, boolean currentlySticky) {
                listFragment.setVisibility(View.VISIBLE);
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
