package entity.maintenancerequest;

import java.util.List;

public interface MaintenanceRequestInterface {
    MaintenanceRequest get(Integer requestID);
    void add(MaintenanceRequest maintenanceRequest);
    void update(Integer reqID, MaintenanceRequest maintenanceRequest);
    void delete(Integer requestID);
    List<MaintenanceRequest> getAll();
}
