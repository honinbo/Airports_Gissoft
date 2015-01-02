package th.co.gissoft.airportsgissoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by getgo_000 on 1/1/2558.
 */
public class SelectAdapter extends AirportListAdapter{

    private final Context mContext;


    public SelectAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mCountries.size();
    }

    @Override
    public Object getItem(int i) {
        return mCountries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = mInflater.inflate(R.layout.txtview_country, viewGroup, false);
        TextView header_text = (TextView) root.findViewById(R.id.txt_country);

        header_text.setText(mCountries.get(i).toString());

        return root;
    }
}
