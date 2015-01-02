package th.co.gissoft.airportsgissoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import th.co.gissoft.airportsgissoft.DBmanager.DBmanager;
import th.co.gissoft.airportsgissoft.data.Airport;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class AirportListAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer{

    private final Context mContext;
    private ArrayList<Airport> airportsList;

    private ArrayList<Integer> mSectionIndices;
    public ArrayList<Integer> mHeaderId;
    public ArrayList<String> mCountries;
    private LayoutInflater mInflater;


    public AirportListAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.airportsList = new DBmanager().selectAirports();
        this.mSectionIndices = new ArrayList<Integer>();
        this.mCountries = new ArrayList<String>();
        this.mHeaderId = new ArrayList<>();
        setDataList();

    }

    public int getmSectionIndices(int position) {
        return mSectionIndices.get(position);
    }

    public void setDataList() {

        int countHeaderID = 0;

        String lastCountry = airportsList.get(0).getCountry();
        mCountries.add(lastCountry);
        mSectionIndices.add(0);
        for (int i = 0; i < airportsList.size(); i++) {

            // add Country
            if (!airportsList.get(i).getCountry().equals(lastCountry)) {
                lastCountry = airportsList.get(i).getCountry();
                mCountries.add(lastCountry);
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
            convertView = mInflater.inflate(R.layout.txtview_country, parent, false);
            headView.textView = (TextView) convertView.findViewById(R.id.txt_country);
            convertView.setTag(headView);
        } else {
            headView = (HeaderViewHolder) convertView.getTag();
        }

        int headerPosition = mHeaderId.get(position);
        headView.textView.setText(mCountries.get(headerPosition).toString());

        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        return mHeaderId.get(position);
    }


    @Override
    public int getCount() {
        // get listView Count
        return airportsList.size();
    }

    @Override
    public Object getItem(int position) {
        return airportsList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //todo getDatalist from airport in DB
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.txtview_airport_list, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.txt_airport_list);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(airportsList.get(position).getName());

        return convertView;
    }

    @Override
    public Object[] getSections() {
        return mCountries.toArray();
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
        TextView textView;
    }

    class ViewHolder {
        TextView textView;
    }
}
