package mobileclientassetmanagement.src.entity.maintenancerequest;



import mobileclientassetmanagement.src.dbmanager.DataManager;
import java.util.Map;

public class MaintenanceRequestImpl implements MaintenanceRequestInterface{

    private Map<Integer, MaintenanceRequest> maintenanceRequestData;
    MaintenanceRequestImpl() {
        this.maintenanceRequestData = DataManager.getMaintenanceRequestData();
    }


    @Override
    public void add(MaintenanceRequest maintenanceRequest) {
        maintenanceRequestData.put(maintenanceRequest.getRequestID(), maintenanceRequest);
    }

    @Override
    public void update(Integer reqID, MaintenanceRequest maintenanceRequest) {
        maintenanceRequestData.put(reqID, maintenanceRequest);
    }

    @Override
    public void delete(Integer requestID) {
        maintenanceRequestData.remove(requestID);
    }
}
