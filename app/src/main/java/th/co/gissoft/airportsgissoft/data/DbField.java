package th.co.gissoft.airportsgissoft.data;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class DbField {

    // Table name
    public static String TBL_AIRLINES = "airlines";
    public static String TBL_AIRPORTS = "airports";
    public static String TBL_ROUTES = "routes";

    // Field name of table airlines
    public static String AIRLINES_AIRLINEID = "airlineid";
    public static String AIRLINES_NAME = "name";
    public static String AIRLINES_IATA = "iata";
    public static String AIRLINES_COUNTRY = "country";

    // Field name of table airports
    public static String AIRPORTS_AIRPORTID = "airportid";
    public static String AIRPORTS_NAME = "name";
    public static String AIRPORTS_CITY = "city";
    public static String AIRPORTS_COUNTRY = "country";
    public static String AIRPORTS_IATA = "iata";
    public static String AIRPORTS_LAT = "lat";
    public static String AIRPORTS_LON = "lon";
    public static String AIRPORTS_ALT = "alt";
    public static String AIRPORTS_TZ = "tz";

    // Field name of table routes
    public static String ROUTES_AIRLINEID = "airlineid";
    public static String ROUTES_SOURCEAIRPORTID = "sourceairportid";
    public static String ROUTES_DESTAIRPORTID = "destairportid";
}
