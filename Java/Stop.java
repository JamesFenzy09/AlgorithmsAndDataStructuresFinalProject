package Java;

public class Stop {
    public int stopID;
    public int stopCode;
    public String stopName;
    public String stopDesc;
    public double stopLat;
    public double stopLon;
    public String zoneID;
    public String stopUrl;
    public int locationType;
    public String parentStation;

    public Stop(int stopID, int stopCode, String stopName, String stopDesc, double stopLat, double stopLon,
            String zoneID, String stopUrl, int locationType, String parentStation) {
        this.stopID = stopID;
        this.stopCode = stopCode;
        this.stopName = stopName;
        this.stopDesc = stopDesc;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.zoneID = zoneID;
        this.stopUrl = stopUrl;
        this.locationType = locationType;
        this.parentStation = parentStation;
    }
}
