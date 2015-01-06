package th.co.gissoft.airportsgissoft.data;

/**
 * Created by getgo_000 on 5/1/2558.
 */
public class Routes {

    private int airlineID;
    private String airlineName;

    private int sourceid;
    private String sourcename;
    private String sourcecountry;
    private double sourcelat;
    private double sourcelon;

    private int destid;
    private String destname;
    private String destcountry;
    private double destlat;
    private double destlon;

    public Routes(int airlineID, int sourceid, String sourcename, String sourcecountry, double sourcelat, double sourcelon, int destid, String destname, String destcountry, double destlat, double destlon) {
        this.airlineID = airlineID;
        this.sourceid = sourceid;
        this.sourcename = sourcename;
        this.sourcecountry = sourcecountry;
        this.sourcelat = sourcelat;
        this.sourcelon = sourcelon;
        this.destid = destid;
        this.destname = destname;
        this.destcountry = destcountry;
        this.destlat = destlat;
        this.destlon = destlon;
    }

    public Routes() {
    }

    public int getAirlineID() {
        return airlineID;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public int getSourceid() {
        return sourceid;
    }

    public void setSourceid(int sourceid) {
        this.sourceid = sourceid;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getSourcecountry() {
        return sourcecountry;
    }

    public void setSourcecountry(String sourcecountry) {
        this.sourcecountry = sourcecountry;
    }

    public double getSourcelat() {
        return sourcelat;
    }

    public void setSourcelat(double sourcelat) {
        this.sourcelat = sourcelat;
    }

    public double getSourcelon() {
        return sourcelon;
    }

    public void setSourcelon(double sourcelon) {
        this.sourcelon = sourcelon;
    }

    public int getDestid() {
        return destid;
    }

    public void setDestid(int destid) {
        this.destid = destid;
    }

    public String getDestname() {
        return destname;
    }

    public void setDestname(String destname) {
        this.destname = destname;
    }

    public String getDestcountry() {
        return destcountry;
    }

    public void setDestcountry(String destcountry) {
        this.destcountry = destcountry;
    }

    public double getDestlat() {
        return destlat;
    }

    public void setDestlat(double destlat) {
        this.destlat = destlat;
    }

    public double getDestlon() {
        return destlon;
    }

    public void setDestlon(double destlon) {
        this.destlon = destlon;
    }
}
