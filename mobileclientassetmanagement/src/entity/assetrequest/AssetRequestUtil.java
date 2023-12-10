package mobileclientassetmanagement.src.entity.assetrequest;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;


public class AssetRequestUtil {
    public static final Integer OPEN = 0;
    public static final Integer APPROVED = 1;
    public static final Integer REJECTED = 3;

    public static final String OPEN_STRING = "Open";
    public static final String APPROVED_STRING = "Approved";
    public static final String REJECTED_STRING = "Rejected";
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("requestID", "requestDate", "requestStatus", "requesterName", "requesterAssignee", "commentList");
    public static Integer generateAssetRequestID() {
        Integer requestID = 1;
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData();
        Map.Entry<Integer, AssetRequest> lastEntry = null;
        for(Map.Entry<Integer, AssetRequest> assetRequestEntry: assetRequestDataMap.entrySet()){
            lastEntry = assetRequestEntry;
        }
        requestID = lastEntry == null ? requestID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return requestID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Asset Request Description Update Test\n1\n2\n-1\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
