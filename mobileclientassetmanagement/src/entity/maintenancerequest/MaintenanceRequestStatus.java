package mobileclientassetmanagement.src.entity.maintenancerequest;


public enum MaintenanceRequestStatus {
    OPEN(MaintenanceRequestUtil.OPEN, MaintenanceRequestUtil.OPEN_STRING),
    CLOSED(MaintenanceRequestUtil.CLOSED, MaintenanceRequestUtil.CLOSED_STRING);

    private int statusCode;

    private String statusName;

    MaintenanceRequestStatus(int statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static String getStatusName(int statusCode){
        for(MaintenanceRequestStatus maintenanceRequestStatus : MaintenanceRequestStatus.values()){
            if(maintenanceRequestStatus.getStatusCode() == statusCode) {
                return maintenanceRequestStatus.getStatusName();
            }
        }
        throw new IllegalArgumentException("No StatusName for the statuscode");
    }

    public static int getStatusCode(String statusName) {
        for(MaintenanceRequestStatus maintenanceRequestStatus : MaintenanceRequestStatus.values()){
            if(maintenanceRequestStatus.getStatusName().equals(statusName)) {
                return maintenanceRequestStatus.getStatusCode();
            }
        }
        throw new IllegalArgumentException("No StatusCode for the StatusName");
    }

}
