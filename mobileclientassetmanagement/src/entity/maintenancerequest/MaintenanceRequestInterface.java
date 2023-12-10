package mobileclientassetmanagement.src.entity.maintenancerequest;

import java.util.List;

public interface MaintenanceRequestInterface {
    void add(MaintenanceRequest maintenanceRequest);
    void update(Integer reqID, MaintenanceRequest maintenanceRequest);
    void delete(Integer requestID);
}
