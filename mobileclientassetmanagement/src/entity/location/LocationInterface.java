package entity.location;
import java.util.List;

public interface LocationInterface {
    void add(Location location);
    void update(Integer locationID, Location location);
    void delete(Integer locationID);
}
