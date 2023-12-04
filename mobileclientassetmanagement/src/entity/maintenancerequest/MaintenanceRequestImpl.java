package entity.maintenancerequest;

import dbmanager.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MaintenanceRequestImpl implements MaintenanceRequestInterface{

    private Map<Integer, MaintenanceRequest> maintenanceRequestData;
    MaintenanceRequestImpl() {
        this.maintenanceRequestData = DataManager.getMaintenanceRequestData();
    }
    @Override
    public MaintenanceRequest get(Integer requestID) {
        return maintenanceRequestData.get(requestID);
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

    @Override
    public List<MaintenanceRequest> getAll() {
        return new ArrayList<>(maintenanceRequestData.values());
    }
}
