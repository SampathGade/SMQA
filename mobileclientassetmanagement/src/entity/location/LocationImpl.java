package mobileclientassetmanagement.src.entity.location;
import mobileclientassetmanagement.src.dbmanager.DataManager;
import java.util.Map;

public class LocationImpl implements LocationInterface{

    private Map<Integer, Location> locationData;

    LocationImpl() {
        this.locationData = DataManager.getLocationData();
    }
    @Override
    public void add(Location location) {
        locationData.put(location.getLocationID(), location);
    }

    @Override
    public void update(Integer locationID, Location location) {
        locationData.put(locationID, location);
    }

    @Override
    public void delete(Integer locationID) {
       locationData.remove(locationID);
    }
}
