package th.co.gissoft.airportsgissoft.DBmanager;

import android.database.Cursor;

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

    public ArrayList<String> getCountryList() {
        ArrayList<String> country_List = new ArrayList<String>();
        String sqlCmd = " SELECT country FROM airports GROUP BY country ";
        Cursor cursor = dbCommand.RawSelect(sqlCmd);
        while (cursor.moveToNext()) {
            Airport airport = new Airport();
            String country = cursor.getString(cursor.getColumnIndex(DbField.AIRPORTS_COUNTRY));

            country_List.add(country);
        }
        cursor.close();
        dbCommand.close();

        return country_List;
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

    public ArrayList<Routes> selectRouteResult(String sourceCountry, String destCountry){
        ArrayList<Routes> resultList = new ArrayList<>();
        String sqlCmd = " SELECT R.airlineid, A.name, A.iata, SP.airportid as sourceid, SP.name as sourcename, SP.country as sourcecountry," +
                " SP.lat as sourcelat, SP.lon as sourcelon, DP.airportid as destid, DP.name as destname, DP.country as destcountry, DP.lat as destlat, DP.lon as destlon " +
                "FROM airlines A JOIN routes R ON A.aiRlineid = R.airlineid " +
                "JOIN airports SP on R.sourceairportid = SP.airportid " +
                "JOIN airports DP on R.destairportid = DP.airportid " +
                "WHERE Sp.country = '"+ sourceCountry +"' AND DP.[country] = '"+ destCountry +"' " +
                "ORDER BY A.airlineid ASC ";
        Cursor cursor = dbCommand.RawSelect(sqlCmd);

        while (cursor.moveToNext()) {

            int airlineID = cursor.getInt(cursor.getColumnIndex(DbField.ROUTES_AIRLINEID));
            String airlineName = cursor.getString(cursor.getColumnIndex(DbField.AIRLINES_NAME));
            String IATA = cursor.getString(cursor.getColumnIndex(DbField.AIRLINES_IATA));

            int sourceid = cursor.getInt(cursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_ID));
            String sourcename = cursor.getString(cursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_NAME));
            String sourcecountry = cursor.getString(cursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_COUNTRY));
            double sourcelat = cursor.getDouble(cursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_LAT));
            double sourcelon = cursor.getDouble(cursor.getColumnIndex(DbField.ROUTES_SOURCE_AIRPORT_LON));

            int destid = cursor.getInt(cursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_ID));
            String destname = cursor.getString(cursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_NAME));
            String destcountry = cursor.getString(cursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_COUNTRY));
            double destlat = cursor.getDouble(cursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_LAT));
            double destlon = cursor.getDouble(cursor.getColumnIndex(DbField.ROUTES_DEST_AIRPORT_LON));

            Routes route = new Routes(airlineID, airlineName, IATA, sourceid, sourcename, sourcecountry, sourcelat, sourcelon, destid, destname, destcountry, destlat, destlon);
            resultList.add(route);
        }
        cursor.close();
        dbCommand.close();

        return resultList;
    }
}
