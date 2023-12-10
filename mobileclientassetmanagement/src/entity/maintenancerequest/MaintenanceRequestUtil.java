package mobileclientassetmanagement.src.entity.maintenancerequest;

public class MaintenanceRequestUtil {
    public static final Integer OPEN = 0;
    public static final Integer CLOSED = 1;

    public static final String OPEN_STRING = "Open";
    public static final String CLOSED_STRING = "Closed";

    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("requestID", "requestDate", "requestStatus", "requesterName", "requesterAssignee", "commentList", "requestClosedDate");
    public static Integer generateMaintenanceRequestID() {
        Integer requestID = 1;
        Map<Integer, MaintenanceRequest> MaintenanceRequestDataMap = DataManager.getMaintenanceRequestData();
        Map.Entry<Integer, MaintenanceRequest> lastEntry = null;
        for(Map.Entry<Integer, MaintenanceRequest> maintenanceRequestEntry : MaintenanceRequestDataMap.entrySet()){
            lastEntry = maintenanceRequestEntry;
        }
        requestID = lastEntry == null ? requestID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return requestID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Maintenance Request Test Description\n1\n2\n-1\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
