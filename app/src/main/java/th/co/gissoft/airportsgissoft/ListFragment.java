package th.co.gissoft.airportsgissoft;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import th.co.gissoft.airportsgissoft.adapter.SelectAdapter;

/**
 * Created by getgo_000 on 1/1/2558.
 */
public class ListFragment extends Fragment {

    SelectAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView list_select = (ListView) rootView.findViewById(R.id.list_select);

        adapter = new SelectAdapter(getActivity());
        list_select.setAdapter(adapter);
        list_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Activity act = getActivity();
                MainActivity mainAct = (MainActivity) act;
                int pos = adapter.getmSectionIndices(i);
                mainAct.setJumpList(pos);
            }
        });


        return rootView;
    }
}
