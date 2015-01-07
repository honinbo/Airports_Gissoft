package th.co.gissoft.airportsgissoft.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.Utils;

/**
 * Created by getgo_000 on 1/1/2558.
 */
public class SelectAirlineAdapter extends RouteListAdapter{

    private final Context mContext;

    public SelectAirlineAdapter(Context mContext, String sourceCountry, String destCountry) {
        super(mContext, sourceCountry, destCountry);
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mAirlines.size();
    }

    @Override
    public Object getItem(int i) {
        return mAirlines.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = mInflater.inflate(R.layout.head_route_result_list, viewGroup, false);

        ImageView imgLogo = (ImageView) root.findViewById(R.id.img_airline_logo);
        TextView header_text = (TextView) root.findViewById(R.id.txt_airlines);

        header_text.setText(mAirlines.get(i).getName());

        Utils util = new Utils(mContext);
        Bitmap airlineLogo = util.getPictureFromAssets(mAirlines.get(i).getIata());
        imgLogo.setImageBitmap(airlineLogo);

        return root;
    }
}
