package mobileclientassetmanagement.src.entity.maintenancerequest;

import mobileclientassetmanagement.src.status.Status;

public class MaintenanceRequestStatusImpl extends Status {
    @Override
    public void close(Object entity) {
        MaintenanceRequest maintenanceRequest = (MaintenanceRequest) entity;
        Integer requestID = maintenanceRequest.getRequestID();
        Integer closedStatusCode = MaintenanceRequestStatus.CLOSED.getStatusCode();
        maintenanceRequest.setRequestStatus(closedStatusCode);
        MaintenanceRequestInterface maintenanceRequestInterface = new MaintenanceRequestFactoryImpl().createMaintenanceRequest();
        maintenanceRequestInterface.update(requestID, maintenanceRequest);
    }
}
