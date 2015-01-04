package th.co.gissoft.airportsgissoft.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import th.co.gissoft.airportsgissoft.DBmanager.DBmanager;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.activity.AirlineListActivity;
import th.co.gissoft.airportsgissoft.data.Airlines;
import th.co.gissoft.airportsgissoft.data.DbField;

/**
 * Created by getgo_000 on 4/1/2558.
 */
public class AirlinesAdapter extends BaseAdapter {

    private final Context mContext;
    private LayoutInflater mInflater;
    private Cursor mCursor_airlines;
    private ArrayList<Airlines> mAirlineList = new ArrayList<>();

    public AirlinesAdapter(Context mContext, int airportId) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        mCursor_airlines = new DBmanager().selectAirlines(airportId);

    while (mCursor_airlines.moveToNext()) {
        Airlines airline = new Airlines();
        airline.setAirlineid(mCursor_airlines.getInt(mCursor_airlines.getColumnIndex(DbField.AIRLINES_AIRLINEID)));
        airline.setName(mCursor_airlines.getString(mCursor_airlines.getColumnIndex(DbField.AIRLINES_NAME)));
        airline.setIata(mCursor_airlines.getString(mCursor_airlines.getColumnIndex(DbField.AIRLINES_IATA)));
        mAirlineList.add(airline);
    }
    }



    @Override
    public int getCount() {
        return mAirlineList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAirlineList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.airlines_list, parent, false);
            holder.imgAirline = (ImageView) convertView.findViewById(R.id.img_airline_logo);
            holder.txtAirline = (TextView) convertView.findViewById(R.id.txt_airlines);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtAirline.setText(mAirlineList.get(position).getName());

        Bitmap airlineLogo = setPicture(mAirlineList.get(position).getIata());

        holder.imgAirline.setImageBitmap(airlineLogo);

        return convertView;
    }

    private Bitmap setPicture(String filename) {
        String file = filename +".png";
        Bitmap airlineLogo;
        AssetManager assetManager = mContext.getAssets();
        InputStream istr = null;
        try {
            istr = assetManager.open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        airlineLogo = BitmapFactory.decodeStream(istr);
        return airlineLogo;
    }



    private class ViewHolder {
        TextView txtAirline;
        ImageView imgAirline;
    }
}
