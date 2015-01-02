package th.co.gissoft.airportsgissoft.data;

/**
 * Created by getgo_000 on 30/12/2557.
 */
public class Airlines {

    private int airlineid;
    private String name;
    private String iata;
    private String country;

    public Airlines(int airlineid, String name, String iata, String country) {

        this.airlineid = airlineid;
        this.name = name;
        this.iata = iata;
        this.country = country;
    }

    public int getAirlineid() {
        return airlineid;
    }

    public void setAirlineid(int airlineid) {
        this.airlineid = airlineid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
