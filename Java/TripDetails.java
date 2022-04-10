package Java;

public class TripDetails {
    int tripID;
    String arrivalTime;
    String departureTime;
    int stopID;
    int stopSequence;
    int stopHeadsign;
    int pickupType;
    int dropOffType;
    float distTravelled;

    public TripDetails(int tripID, String arrivalTime, String departureTime, int stopID, int stopSequence,
            int stopHeadsign, int pickupType, int dropOffType, float distTravelled) {
        this.tripID = tripID;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.stopID = stopID;
        this.stopSequence = stopSequence;
        this.stopHeadsign = stopHeadsign;
        this.pickupType = pickupType;
        this.dropOffType = dropOffType;
        this.distTravelled = distTravelled;
    }
}
