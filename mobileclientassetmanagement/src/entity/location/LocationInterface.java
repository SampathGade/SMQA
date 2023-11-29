package mobileclientassetmanagement.src.entity.location;
import java.util.List;

public interface LocationInterface {
    Location get(Integer locationID);
    void add(Location location);
    void update(Integer locationID, Location location);
    void delete(Integer locationID);
    List<Location> getAll();
}
