package th.co.gissoft.airportsgissoft.DBmanager;

import android.database.Cursor;

import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import th.co.gissoft.airportsgissoft.data.Airport;
import th.co.gissoft.airportsgissoft.data.DbField;
import th.co.gissoft.airportsgissoft.data.Routes;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class DBmanager {

    private DbApi dbCommand;

    public DBmanager() {
        dbCommand = new DbApi();

    }



    public ArrayList<Airport> selectAirports(){
        ArrayList<Airport> airports_List = new ArrayList<Airport>();
        Cursor cursor = dbCommand.Select(DbField.TBL_AIRPORTS, null, DbField.AIRPORTS_COUNTRY +", "+ DbField.AIRPORTS_NAME + " ASC");
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DbField.AIRPORTS_AIRPORTID));
            String name = cursor.getString(cursor.getColumnIndex(DbField.AIRPORTS_NAME));
            String city = cursor.getString(cursor.getColumnIndex(DbField.AIRPORTS_CITY));
            String country = cursor.getString(cursor.getColumnIndex(DbField.AIRPORTS_COUNTRY));
            String iata = cursor.getString(cursor.getColumnIndex(DbField.AIRPORTS_IATA));
            double lat = cursor.getDouble(cursor.getColumnIndex(DbField.AIRPORTS_LAT));
            double lon = cursor.getDouble(cursor.getColumnIndex(DbField.AIRPORTS_LON));
            int alt = cursor.getInt(cursor.getColumnIndex(DbField.AIRPORTS_COUNTRY));
            int tz =cursor.getInt(cursor.getColumnIndex(DbField.AIRPORTS_TZ));

            airports_List.add(new Airport(id, name, city, country, iata, lat, lon, alt, tz));
        }

        cursor.close();
        dbCommand.close();

        return airports_List;
    }

    public Cursor selectAirlines(int airportid){

        String sqlCmd = " SELECT A.airlineid, A.name, A.iata FROM ROUTES R JOIN AIRLINES A on R.airlineid = A.airlineid WHERE R.destairportid = '"+ airportid +"' GROUP BY R.airlineid ";
        Cursor cursor = dbCommand.RawSelect(sqlCmd);

        return cursor;
    }

    public Cursor selectAirlinesDetails(int airlineId,int destAirportId){
        ArrayList<Routes> routes = new ArrayList<>();

        String sqlCmd = " SELECT R.airlineid, SP.airportid as sourceid, SP.name as sourcename, SP.country as sourcecountry, SP.lat as sourcelat, SP.lon as sourcelon, DP.airportid as destid, DP.name as destname, DP.country as destcountry, DP.lat as destlat, DP.lon as destlon\n" +
                "FROM airlines A JOIN routes R ON A.aiRlineid = R.airlineid\n" +
                "JOIN airports SP on R.sourceairportid = SP.airportid \n" +
                "JOIN airports DP on R.destairportid = DP.airportid WHERE A.airlineid = '"+ airlineId +"' AND R.destairportid = '"+ destAirportId +"' ";
        Cursor cursor = dbCommand.RawSelect(sqlCmd);

        return cursor;
    }
}
