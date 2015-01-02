package th.co.gissoft.airportsgissoft.data;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class Airport {

    private int airportid;
    private String name;
    private String city;
    private String country;
    private String iata;
    private double lat;
    private double lon;
    private int alt;
    private int tz;

    public Airport(int airportid, String name, String city, String country, String iata, double lat, double lon, int alt, int tz) {
        this.airportid = airportid;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iata = iata;
        this.lat = lat;
        this.lon = lon;
        this.alt = alt;
        this.tz = tz;
    }

    public Airport() {}

    public int getAirportid() {
        return airportid;
    }

    public void setAirportid(int airportid) {
        this.airportid = airportid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getTz() {
        return tz;
    }

    public void setTz(int tz) {
        this.tz = tz;
    }
}
