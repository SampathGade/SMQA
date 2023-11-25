package entity.location;

public class Location {
    private Integer locationID;
    private String locationName;

    public Location()
    {

    }

    public Location(Integer locationID, String locationName) {
        this.locationID = locationID;
        this.locationName = locationName;
    }

    public void setLocationID(Integer locationID) {
        this.locationID = locationID;
    }

    public Integer getLocationID() {
        return locationID;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }
}
