package th.co.gissoft.airportsgissoft.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import th.co.gissoft.airportsgissoft.DBmanager.DBmanager;
import th.co.gissoft.airportsgissoft.R;
import th.co.gissoft.airportsgissoft.Utils;
import th.co.gissoft.airportsgissoft.data.Airline;
import th.co.gissoft.airportsgissoft.data.Routes;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class RouteListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer{

    private final Context mContext;
    private ArrayList<Routes> mResultList;



    private ArrayList<Integer> mSectionIndices;
    public ArrayList<Integer> mHeaderId;
    public ArrayList<Airline> mAirlines;
    private LayoutInflater mInflater;


    public RouteListAdapter(Context mContext, String sourceCountry, String destCountry) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        if (this.mResultList == null) {
            this.mResultList = new DBmanager().selectRouteResult(sourceCountry, destCountry);
            this.mSectionIndices = new ArrayList<Integer>();
            this.mAirlines = new ArrayList<Airline>();
            this.mHeaderId = new ArrayList<>();
            if (this.mResultList.size() != 0) {
                setDataList();
            }
        }

    }



    public int getmSectionIndices(int position) {
        return mSectionIndices.get(position);
    }

    public void setDataList() {

        int countHeaderID = 0;

        Airline lastAirline = new Airline();
        lastAirline.setAirlineid(mResultList.get(0).getAirlineID());
        lastAirline.setName(mResultList.get(0).getAirlineName());
        lastAirline.setIata(mResultList.get(0).getIATA());

        mAirlines.add(lastAirline);
        mSectionIndices.add(0);
        for (int i = 0; i < mResultList.size(); i++) {

            // add Country
            if (mResultList.get(i).getAirlineID() != lastAirline.getAirlineid()) {
                lastAirline = new Airline();
                lastAirline.setAirlineid(mResultList.get(i).getAirlineID());
                lastAirline.setName(mResultList.get(i).getAirlineName());
                lastAirline.setIata(mResultList.get(i).getIATA());

                mAirlines.add(lastAirline);
                mSectionIndices.add(i);
                countHeaderID++;
            }

            //add HeaderID
            mHeaderId.add(countHeaderID);
        }
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        //todo getHeader from Country in DB
        HeaderViewHolder headView;
        if (convertView == null) {
            headView = new HeaderViewHolder();
            convertView = mInflater.inflate(R.layout.head_route_result_list, parent, false);
            headView.imgLogo = (ImageView) convertView.findViewById(R.id.img_airline_logo);
            headView.textView = (TextView) convertView.findViewById(R.id.txt_airlines);
            convertView.setTag(headView);
        } else {
            headView = (HeaderViewHolder) convertView.getTag();
        }

        int headerPosition = mHeaderId.get(position);
        headView.textView.setText(mAirlines.get(headerPosition).getName());

        Utils util = new Utils(mContext);
        Bitmap airlineLogo = util.getPictureFromAssets(mAirlines.get(headerPosition).getIata());
        headView.imgLogo.setImageBitmap(airlineLogo);

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return mHeaderId.get(position);
    }


    @Override
    public int getCount() {
        // get listView Count
        return mResultList.size();
    }

    @Override
    public Object getItem(int position) {
        return mResultList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_route_result, parent, false);
            holder.textViewSource = (TextView) convertView.findViewById(R.id.txt_source_name);
            holder.textViewDest = (TextView) convertView.findViewById(R.id.txt_dest_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textViewSource.setText(mResultList.get(position).getSourcename());
        holder.textViewDest.setText(mResultList.get(position).getDestname());

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return mAirlines.toArray();
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if (mSectionIndices.size() == 0)
            return 0;

        if (sectionIndex >= mSectionIndices.size()){
            sectionIndex = mSectionIndices.size() - 1;
        } else {
               sectionIndex = 0;
        }
        return sectionIndex;
    }

    @Override
    public int getSectionForPosition(int position) {
        for (int i = 0; i < mSectionIndices.size(); i++) {
            if (position < mSectionIndices.get(i)) {
                return i - 1;
            }
        }
        return mSectionIndices.size() - 1;
    }

    class HeaderViewHolder {
        ImageView imgLogo;
        TextView textView;
    }

    class ViewHolder {
        TextView textViewSource;
        TextView textViewDest;
    }


}
