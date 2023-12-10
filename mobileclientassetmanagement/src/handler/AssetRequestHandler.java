package mobileclientassetmanagement.src.handler;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.Asset;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.entity.assetrequest.*;
import mobileclientassetmanagement.src.entity.useraccount.User;
import mobileclientassetmanagement.src.status.StatusFactory;
import mobileclientassetmanagement.src.util.AccessUtil;
import mobileclientassetmanagement.src.util.AppUtil;
import mobileclientassetmanagement.src.util.Constants;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import mobileclientassetmanagement.src.entity.assetrequest.AssetRequest;

public class AssetRequestHandler implements Handler {

    private Map<Integer, String> assetRequestHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;

    private User asseReqUser;
    public AssetRequestHandler() {
        this(null);
    }
    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }
    public AssetRequestHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        asseReqUser = currentUser;
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            assetRequestHandlerMap = AccessUtil.ADMIN_ASSET_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            assetRequestHandlerMap = AccessUtil.ASSET_MANAGER_ASSET_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_USER_STRING)) {
            assetRequestHandlerMap = AccessUtil.ASSET_USER_ASSET_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_TECHNICIAN_STRING)) {
            assetRequestHandlerMap = AccessUtil.TECHNICIAN_ASSET_REQUEST_MODULE_MAP;
        }
        this.scanner = scanner == null ? new Scanner(System.in) : scanner;
    }
    @Override
    public void execute() {
        do {
            try {
                System.out.println("\n");
                showOperations();
                int option = !AppUtil.isFromTest() ? scanner.nextInt() : Integer.parseInt(scanner.nextLine().trim());
                if (!assetRequestHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if (assetRequestHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if (assetRequestHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if (assetRequestHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if (assetRequestHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("View Open Asset Request")) {
                    showOpenAssetRequest();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("View All Asset Request")) {
                    handleViewAllAssetRequest();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("Approve Asset Request")) {
                    approveAssetRequest();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("Approve Asset Request")) {
                    rejectAssetRequest();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("Comment on Asset Request")) {
                    commentOnAssetRequest();
                }
                else if(assetRequestHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer");e.printStackTrace();}
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : assetRequestHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Request Creation....");
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData();
        AssetRequest assetRequest = new AssetRequest();
        handleFieldInput(assetRequest);
        assetRequest.setRequestID(AssetRequestUtil.generateAssetRequestID());
        assetRequest.setRequestStatus(AssetRequestStatus.OPEN.getStatusCode());
        assetRequest.setRequestDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        assetRequest.setRequesterName(AppUtil.getCurrentUser());
        Integer requestID = assetRequest.getRequestID();
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.add(assetRequest);
        System.out.println("Asset Request Created Successfully!!!!");
        displayAssetRequestDetailsVertical(assetRequestDataMap.get(requestID));
    }

    private AssetRequest handleRetrieve() {
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData(asseReqUser.getName());
        displayRequest(assetRequestDataMap);
        int providedID = scanner.nextInt();
        AssetRequest providedAssetRequest = assetRequestDataMap.get(providedID);
        System.out.println("You have chosen the following Request");
        displayAssetRequestDetailsVertical(providedAssetRequest);
        return providedAssetRequest;
    }

    private void handleUpdate() {
        AssetRequest requestToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = AssetRequestUtil.getTestInputForUpdate();
        }
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData(asseReqUser.getName());
        handleFieldInput(requestToBeUpdated);
        Integer requestID = requestToBeUpdated.getRequestID();
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.update(requestID, requestToBeUpdated);
        System.out.println("Request Updated Successfully!!!!");
        displayAssetRequestDetailsVertical(assetRequestDataMap.get(requestID));
    }

    private void handleDelete() {
        AssetRequest requestToBeDeleted = handleRetrieve();
        Integer requestID = requestToBeDeleted.getRequestID();
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.delete(requestID);
        System.out.println("Request Deleted Successfully!!!!");
    }

    private void displayRequest(Map<Integer, AssetRequest> assetRequestDataMap) {
        for(Map.Entry<Integer, AssetRequest> assetRequestEntry : assetRequestDataMap.entrySet()) {
            int reqID = assetRequestEntry.getKey();
            AssetRequest assetRequest = assetRequestEntry.getValue();
            System.out.print(reqID);
            System.out.print(":\t");
            displayAssetRequestDetailsHorizontal(assetRequest);
        }
    }

    private void handleFieldInput(AssetRequest assetRequest) {
        try {
            Field[] assetRequestFields = AssetRequest.class.getDeclaredFields();
            for(Field field : assetRequestFields) {
                if(AssetRequestUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
                    continue;
                }
                System.out.println("Enter " + field.getName() + ":");
                field.setAccessible(true);
                if(field.getType() == List.class) {
                    ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                    Class<?> classType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    if(classType == Asset.class) {
                        System.out.println("Select Asset to Add in the Request");
                        System.out.println("Press -1 to End");
                        List<Asset> assetList = getListInput(scanner);
                        field.set(assetRequest, assetList);
                    }
                }
                else{
                    String input = scanner.nextLine();
                    field.set(assetRequest, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input");}
    }

    private static List<Asset> getListInput(Scanner scanner) {
        List<Asset> assetList = new ArrayList<>();
        Integer assetID;
        do {
            for(Asset asset : DataManager.getAssetData().values()) {
                System.out.println(asset.getAssetID() + "." +asset.getAssetName());
            }
            assetID = scanner.nextInt();
            if(assetID != -1) {
                Map<Integer, Asset> assetDataMap = DataManager.getAssetData();
                Asset asset = assetDataMap.get(assetID);
                assetList.add(asset);
            }
        }
        while (assetID != -1);
        return assetList;
    }

    private void showOpenAssetRequest() {
        String roleName = AppUtil.getCurrentUser().getUserRole().getRoleName();
        Boolean isSelf = !(roleName.equals(Constants.ROLE_ADMIN_STRING) || roleName.equals(Constants.ROLE_ASSET_MANAGER_STRING));
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData(isSelf, AppUtil.getCurrentUser().getName(), AssetRequestStatus.OPEN.getStatusCode());
        for(AssetRequest assetRequest : assetRequestDataMap.values()) {
            displayAssetRequestDetailsHorizontal(assetRequest);
        }
    }

    private void handleViewAllAssetRequest() {
        System.out.println("Displaying Request:");
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData();
        displayRequest(assetRequestDataMap);
    }

    private AssetRequest getRequestForApproveOrReject() {
        Map<Integer, AssetRequest> assetRequestDataMap =  DataManager.getAssetRequestData(Boolean.FALSE, null, AssetRequestStatus.OPEN.getStatusCode());
        displayRequest(assetRequestDataMap);
        int providedID = scanner.nextInt();
        AssetRequest providedAssetRequest = assetRequestDataMap.get(providedID);
        return providedAssetRequest;
    }
    private void approveAssetRequest() {
        AssetRequest providedAssetRequest = getRequestForApproveOrReject();
        providedAssetRequest.setRequesterAssignee(AppUtil.getCurrentUser());
        StatusFactory.getObject(Constants.ASSET_REQUEST).approve(providedAssetRequest);
        System.out.println("Request Approved Successfully!!!");
    }
    
    private void rejectAssetRequest() {
        AssetRequest providedAssetRequest = getRequestForApproveOrReject();
        providedAssetRequest.setRequesterAssignee(AppUtil.getCurrentUser());
        StatusFactory.getObject(Constants.ASSET_REQUEST).reject(providedAssetRequest);
        System.out.println("Request Rejected!");
    }
    
    private void commentOnAssetRequest() {
        String roleName = AppUtil.getCurrentUser().getUserRole().getRoleName();
        Boolean isSelf = !(roleName.equals(Constants.ROLE_ADMIN_STRING) || roleName.equals(Constants.ROLE_ASSET_MANAGER_STRING));
        Map<Integer, AssetRequest> assetRequestDataMap = DataManager.getAssetRequestData(isSelf, AppUtil.getCurrentUser().getName());
        displayRequest(assetRequestDataMap);
        int providedID = scanner.nextInt();
        AssetRequest providedAssetRequest = assetRequestDataMap.get(providedID);
        System.out.println("\nEnter Comment");
        scanner.nextLine();
        String comment = scanner.nextLine();
        AssetRequest.Comment assetRequestComment = new AssetRequest.Comment(comment, AppUtil.getCurrentUser());
        if(providedAssetRequest.getCommentList() == null && providedAssetRequest.getCommentList().isEmpty()) {
            providedAssetRequest.setCommentList(new ArrayList<>());
        }
        List<AssetRequest.Comment> commentList = new ArrayList<>(providedAssetRequest.getCommentList());
        commentList.add(assetRequestComment);
        providedAssetRequest.setCommentList(commentList);
    }

    private static void displayAssetRequestDetailsVertical(AssetRequest assetRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.println("\nAssetRequest details:");
        System.out.println("Asset RequestID : " + assetRequest.getRequestID());
        System.out.println("Request Date: " + simpleDateFormat.format(assetRequest.getRequestDate()));
        System.out.println("Request Status: " + assetRequest.getRequestStatus());
        System.out.println("Request Status Formatted: " + AssetRequestStatus.getStatusName(assetRequest.getRequestStatus()));
        System.out.println("Request Description: " + assetRequest.getRequestDescription());
        System.out.println("Asset Info: ");
        System.out.print("[");
        for(Asset asset : assetRequest.getAssetList()) {
            System.out.println("{");
            System.out.println("AssetName: " +asset.getAssetName());
            System.out.println("AssetCategory: " +asset.getAssetCategory().getCategoryName());
            System.out.println("AssetModel: " + asset.getAssetModel());
            System.out.println("AssetDescription: " + asset.getAssetDescription());
            System.out.println("}");
        }
        System.out.print("]");
        System.out.println("\nRequester Name: " + assetRequest.getRequesterName().getName());
        if(assetRequest.getRequesterAssignee() != null)
        System.out.println("Requester Assignee: " + assetRequest.getRequesterAssignee().getName());
        if(assetRequest.getCommentList() != null) {
            System.out.println("Comment: ");
            System.out.println("[");
            for(AssetRequest.Comment assetReqComment: assetRequest.getCommentList()) {
                System.out.println("{");
                System.out.println(assetReqComment.getCommenterName().getName() + ":" + assetReqComment.getCommentString());
                System.out.println("}");
            }
            System.out.println("]");
        }

    }

    private static void displayAssetRequestDetailsHorizontal(AssetRequest assetRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.print("Asset RequestID : " + assetRequest.getRequestID() +", ");
        System.out.print("Request Date: " + simpleDateFormat.format(assetRequest.getRequestDate()) +", ");
        System.out.print("Request Status: " + assetRequest.getRequestStatus() +", ");
        System.out.print("Request Status Formatted: " + AssetRequestStatus.getStatusName(assetRequest.getRequestStatus())+", ");
        System.out.print("Request Description: " + assetRequest.getRequestDescription()+", ");
        System.out.print("Asset Info: ");
        System.out.print("[");
        for(Asset asset : assetRequest.getAssetList()) {
            System.out.print("{");
            System.out.print("AssetName: " +asset.getAssetName()+", ");
            System.out.print("AssetCategory: " +asset.getAssetCategory().getCategoryName()+", ");
            System.out.print("AssetModel: " + asset.getAssetModel()+", ");
            System.out.print("AssetDescription: " + asset.getAssetDescription());
            System.out.print("}");
        }
        System.out.print("]"+", ");
        if(assetRequest.getRequesterAssignee() != null)
        System.out.print("Requester Assignee: " + assetRequest.getRequesterAssignee().getName() +", ");
        if(assetRequest.getCommentList() != null) {
            System.out.print("Comment: ");
            System.out.print("[");
            for (AssetRequest.Comment assetReqComment : assetRequest.getCommentList()) {
                System.out.print("{");
                System.out.print(assetReqComment.getCommenterName().getName() + ":" + assetReqComment.getCommentString());
                System.out.print("}");
            }
            System.out.print("]");
        }
        System.out.print("Requester Name: " + assetRequest.getRequesterName().getName());
    }
}
