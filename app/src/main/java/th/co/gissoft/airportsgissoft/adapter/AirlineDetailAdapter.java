package th.co.gissoft.airportsgissoft.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import th.co.gissoft.airportsgissoft.DBmanager.DBmanager;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.Utils;
import th.co.gissoft.airportsgissoft.data.DbField;
import th.co.gissoft.airportsgissoft.data.Routes;

/**
 * Created by getgo_000 on 5/1/2558.
 */
public class AirlineDetailAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private Cursor mCursor;

    private int mAirlineId;
    private int mAirportId;
    private String mAirlinename;
    private String mIATA;

    private ArrayList<Routes> mRoutes;

    public AirlineDetailAdapter(Context mContext, int mAirlineId, int mAirportId, String mAirlinename, String mIATA) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mAirlineId = mAirlineId;
        this.mAirportId = mAirportId;
        this.mAirlinename = mAirlinename;
        this.mIATA = mIATA;

        mRoutes = new ArrayList<>();
        mCursor = new DBmanager().selectAirlinesDetails(mAirlineId, mAirportId);
        while (mCursor.moveToNext()) {
            int airlineID = mCursor.getInt(mCursor.getColumnIndex(DbField.AIRLINES_AIRLINEID));


            int sourceid = mCursor.getInt(mCursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_ID));
            String sourcename = mCursor.getString(mCursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_NAME));
            String sourcecountry = mCursor.getString(mCursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_COUNTRY));
            double sourcelat = mCursor.getDouble(mCursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_LAT));;
            double sourcelon = mCursor.getDouble(mCursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_LON));;

            int destid = mCursor.getInt(mCursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_ID));
            String destname = mCursor.getString(mCursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_NAME));
            String destcountry = mCursor.getString(mCursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_COUNTRY));;
            double destlat = mCursor.getDouble(mCursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_LAT));;
            double destlon = mCursor.getDouble(mCursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_LON));;

            mRoutes.add(new Routes(airlineID, sourceid, sourcename, sourcecountry, sourcelat, sourcelon, destid, destname, destcountry, destlat, destlon));
        }

    }

    @Override
    public int getCount() {
        return mRoutes.size();
    }

    @Override
    public Object getItem(int position) {
        return mRoutes.get(position);
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
            convertView = mInflater.inflate(R.layout.airline_detail_list, parent, false);
            holder.txt_airportName = (TextView) convertView.findViewById(R.id.txt_airport_name);
            holder.txt_country = (TextView) convertView.findViewById(R.id.txt_country);
            holder.txt_distance = (TextView) convertView.findViewById(R.id.txt_distance);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txt_airportName.setText(mRoutes.get(position).getSourcename());
        holder.txt_country.setText(mRoutes.get(position).getSourcecountry());
        holder.txt_distance.setText("Temp");

        return convertView;
    }

    private class  ViewHolder {
        TextView txt_airportName;
        TextView txt_country;
        TextView txt_distance;
    }
}
