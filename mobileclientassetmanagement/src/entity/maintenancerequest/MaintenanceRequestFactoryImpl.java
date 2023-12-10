package mobileclientassetmanagement.src.entity.maintenancerequest;

public class MaintenanceRequestFactoryImpl implements MaintenanceRequestFactory{
    @Override
    public MaintenanceRequestInterface createMaintenanceRequest() {
        return new MaintenanceRequestImpl();
    }
}
