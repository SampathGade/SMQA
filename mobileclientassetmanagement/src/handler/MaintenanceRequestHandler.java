package handler;

import dbmanager.DataManager;
import entity.asset.Asset;
import entity.assetrequest.AssetRequest;
import entity.assetrequest.AssetRequestUtil;
import entity.maintenancerequest.*;
import entity.useraccount.User;
import status.StatusFactory;
import util.AccessUtil;
import util.AppUtil;
import util.Constants;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class MaintenanceRequestHandler implements Handler {
    private Map<Integer, String> maintenanceRequestHandlerMap;
    private Scanner scanner;
    private boolean CanRunAgain = true;
    private User reqUser;

    public void setCanRunAgain(boolean canRunAgain) {
        CanRunAgain = canRunAgain;
    }
    public MaintenanceRequestHandler() {
        this(null);
    }
    public MaintenanceRequestHandler(Scanner scanner) {
        User currentUser = AppUtil.getCurrentUser();
        reqUser = currentUser;
        String userRole = currentUser != null ? currentUser.getUserRole().getRoleName() : Constants.ROLE_ADMIN_STRING;
        if(userRole.equals(Constants.ROLE_ADMIN_STRING)) {
            maintenanceRequestHandlerMap = AccessUtil.ADMIN_MAINTENANCE_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_MANAGER_STRING)) {
            maintenanceRequestHandlerMap = AccessUtil.ASSET_MANAGER_MAINTENANCE_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_ASSET_USER_STRING)) {
            maintenanceRequestHandlerMap = AccessUtil.ASSET_USER_MAINTENANCE_REQUEST_MODULE_MAP;
        }
        else if(userRole.equals(Constants.ROLE_TECHNICIAN_STRING)) {
            maintenanceRequestHandlerMap = AccessUtil.TECHNICIAN_MAINTENANCE_REQUEST_MODULE_MAP;
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
                if (!maintenanceRequestHandlerMap.containsKey(option)) {
                    System.out.println("Please Select Valid Option");
                    continue;
                }
                if (maintenanceRequestHandlerMap.get(option).startsWith("Create")) {
                    handleCreate();
                }
                else if (maintenanceRequestHandlerMap.get(option).startsWith("Retrieve")) {
                    handleRetrieve();
                }
                else if (maintenanceRequestHandlerMap.get(option).startsWith("Update")) {
                    handleUpdate();
                }
                else if (maintenanceRequestHandlerMap.get(option).startsWith("Delete")) {
                    handleDelete();
                }
                else if(maintenanceRequestHandlerMap.get(option).startsWith("View Open Maintenance Request")) {
                    showOpenMaintenanceRequest();
                }
                else if(maintenanceRequestHandlerMap.get(option).startsWith("View All Maintenance Request")) {
                    handleViewAllMaintenanceRequest();
                }
                else if(maintenanceRequestHandlerMap.get(option).startsWith("Close Maintenance Request")) {
                    closeMaintenanceRequest();
                }
                else if(maintenanceRequestHandlerMap.get(option).startsWith("Comment on Maintenance Request")) {
                    commentOnMaintenanceRequest();
                }
                else if(maintenanceRequestHandlerMap.get(option).startsWith("Exit")) {
                    return;
                }
            }
            catch (Exception e) { System.out.println("Invalid input, Please provide valid Integer");}
        }
        while (CanRunAgain);
    }

    private void showOperations() {
        for (Map.Entry<Integer, String> entry : maintenanceRequestHandlerMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    private void handleCreate() {
        System.out.println("New Request Creation....");
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData();
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        handleFieldInput(maintenanceRequest);
        maintenanceRequest.setRequestID(MaintenanceRequestUtil.generateMaintenanceRequestID());
        maintenanceRequest.setRequestDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        maintenanceRequest.setRequestStatus(MaintenanceRequestStatus.OPEN.getStatusCode());
        maintenanceRequest.setRequesterName(AppUtil.getCurrentUser());
        Integer requestID = maintenanceRequest.getRequestID();
        MaintenanceRequestInterface maintenanceRequestInterface = new MaintenanceRequestFactoryImpl().createMaintenanceRequest();
        maintenanceRequestInterface.add(maintenanceRequest);
        System.out.println("Maintenance Request Created Successfully!!!!");
        displayMaintenanceRequestDetailsVertical(maintenanceRequestDataMap.get(requestID));
    }

    private MaintenanceRequest handleRetrieve() {
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData(reqUser.getName());
        displayRequest(maintenanceRequestDataMap);
        int providedID = scanner.nextInt();
        MaintenanceRequest providedMaintenanceRequest = maintenanceRequestDataMap.get(providedID);
        System.out.println("You have chosen the following Request");
        displayMaintenanceRequestDetailsVertical(providedMaintenanceRequest);
        return providedMaintenanceRequest;
    }

    private void handleUpdate() {
        MaintenanceRequest requestToBeUpdated = handleRetrieve();
        if(AppUtil.isFromTest()) {
            scanner = MaintenanceRequestUtil.getTestInputForUpdate();
        }
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData(reqUser.getName());
        handleFieldInput(requestToBeUpdated);
        Integer requestID = requestToBeUpdated.getRequestID();
        MaintenanceRequestInterface maintenanceRequestInterface = new MaintenanceRequestFactoryImpl().createMaintenanceRequest();
        maintenanceRequestInterface.update(requestID, requestToBeUpdated);
        System.out.println("Request Updated Successfully!!!!");
        displayMaintenanceRequestDetailsVertical(maintenanceRequestDataMap.get(requestID));
    }

    private void handleDelete() {
        MaintenanceRequest requestToBeDeleted = handleRetrieve();
        Integer requestID = requestToBeDeleted.getRequestID();
        MaintenanceRequestInterface maintenanceRequestInterface = new MaintenanceRequestFactoryImpl().createMaintenanceRequest();
        maintenanceRequestInterface.delete(requestID);
        System.out.println("Request Deleted Successfully!!!!");
    }

    private void displayRequest(Map<Integer, MaintenanceRequest> maintenanceRequestDataMap) {
        for(Map.Entry<Integer, MaintenanceRequest> maintenanceRequestEntry : maintenanceRequestDataMap.entrySet()) {
            int reqID = maintenanceRequestEntry.getKey();
            MaintenanceRequest maintenanceRequest = maintenanceRequestEntry.getValue();
            System.out.print(reqID);
            System.out.print(":\t");
            displayMaintenanceRequestDetailsHorizontal(maintenanceRequest);
        }
    }

    private void handleFieldInput(MaintenanceRequest maintenanceRequest) {
        try {
            Field[] assetRequestFields = MaintenanceRequest.class.getDeclaredFields();
            for(Field field : assetRequestFields) {
                if(MaintenanceRequestUtil.SKIPPED_INPUT_FIELDS.contains(field.getName())) {
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
                        field.set(maintenanceRequest, assetList);
                    }
                }
                else{
                    String input = scanner.nextLine();
                    field.set(maintenanceRequest, input);
                }
            }
        }
        catch (Exception e) { System.out.println("Please provide valid Input"); e.printStackTrace();}
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

    private void showOpenMaintenanceRequest() {
        String roleName = AppUtil.getCurrentUser().getUserRole().getRoleName();
        Boolean isSelf = !(roleName.equals(Constants.ROLE_ADMIN_STRING) || roleName.equals(Constants.ROLE_ASSET_MANAGER_STRING));
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData(isSelf, AppUtil.getCurrentUser().getName(), MaintenanceRequestStatus.OPEN.getStatusCode());
        for(MaintenanceRequest maintenanceRequest : maintenanceRequestDataMap.values()) {
            displayMaintenanceRequestDetailsHorizontal(maintenanceRequest);
        }
    }

    private void handleViewAllMaintenanceRequest() {
        System.out.println("Displaying Request:");
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData();
        displayRequest(maintenanceRequestDataMap);
    }

    private MaintenanceRequest getRequestForClose() {
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap =  DataManager.getMaintenanceRequestData(Boolean.FALSE, null, MaintenanceRequestStatus.OPEN.getStatusCode());
        displayRequest(maintenanceRequestDataMap);
        int providedID = scanner.nextInt();
        MaintenanceRequest providedMaintenanceRequest = maintenanceRequestDataMap.get(providedID);
        return providedMaintenanceRequest;
    }

    private void closeMaintenanceRequest() {
        MaintenanceRequest providedMaintenanceRequest = getRequestForClose();
        providedMaintenanceRequest.setRequesterAssignee(AppUtil.getCurrentUser());
        StatusFactory.getObject(Constants.MAINTENANCE_REQUEST).close(providedMaintenanceRequest);
        System.out.println("Request Closed !!!");
    }



    private void commentOnMaintenanceRequest() {
        String roleName = AppUtil.getCurrentUser().getUserRole().getRoleName();
        Boolean isSelf = !(roleName.equals(Constants.ROLE_ADMIN_STRING) || roleName.equals(Constants.ROLE_ASSET_MANAGER_STRING));
        Map<Integer, MaintenanceRequest> maintenanceRequestDataMap = DataManager.getMaintenanceRequestData(isSelf, AppUtil.getCurrentUser().getName());
        displayRequest(maintenanceRequestDataMap);
        int providedID = scanner.nextInt();
        MaintenanceRequest providedMaintenanceRequest = maintenanceRequestDataMap.get(providedID);
        System.out.println("\nEnter Comment");
        scanner.nextLine();
        String comment = scanner.nextLine();
        MaintenanceRequest.Comment maintenanceRequestComment = new MaintenanceRequest.Comment(comment, AppUtil.getCurrentUser());
        if(providedMaintenanceRequest.getCommentList() == null && providedMaintenanceRequest.getCommentList().isEmpty()) {
            providedMaintenanceRequest.setCommentList(new ArrayList<>());
        }
        List<MaintenanceRequest.Comment> commentList = new ArrayList<>(providedMaintenanceRequest.getCommentList());
        commentList.add(maintenanceRequestComment);
        providedMaintenanceRequest.setCommentList(commentList);
    }

    private static void displayMaintenanceRequestDetailsVertical(MaintenanceRequest maintenanceRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.println("\nMaintenanceRequest details:");
        System.out.println("Maintenance RequestID : " + maintenanceRequest.getRequestID());
        System.out.println("Request Date: " + simpleDateFormat.format(maintenanceRequest.getRequestDate()));
        System.out.println("Request Status: " + maintenanceRequest.getRequestStatus());
        System.out.println("Request Status Formatted: " + MaintenanceRequestStatus.getStatusName(maintenanceRequest.getRequestStatus()));
        System.out.println("Request Description: " + maintenanceRequest.getRequestDescription());
        System.out.println("Affected Asset Info: ");
        System.out.print("[");
        for(Asset asset : maintenanceRequest.getAssetAffected()) {
            System.out.println("{");
            System.out.println("AssetName: " +asset.getAssetName());
            System.out.println("AssetCategory: " +asset.getAssetCategory().getCategoryName());
            System.out.println("AssetModel: " + asset.getAssetModel());
            System.out.println("AssetDescription: " + asset.getAssetDescription());
            System.out.println("}");
        }
        System.out.print("]");
        System.out.println("\nRequester Name: " + maintenanceRequest.getRequesterName().getName());
        if(maintenanceRequest.getRequesterAssignee() != null)
            System.out.println("Requester Assignee: " + maintenanceRequest.getRequesterAssignee().getName());
        if(maintenanceRequest.getCommentList() != null) {
            System.out.println("Comment: ");
            System.out.println("[");
            for(MaintenanceRequest.Comment maintReqComment : maintenanceRequest.getCommentList()) {
                System.out.println("{");
                System.out.println(maintReqComment.getCommenterName().getName() + ":" + maintReqComment.getCommentString());
                System.out.println("}");
            }
            System.out.println("]");
        }

    }

    private static void displayMaintenanceRequestDetailsHorizontal(MaintenanceRequest maintenanceRequest) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(AppUtil.DATE_PATTERN);
        System.out.print("Maintenance RequestID : " + maintenanceRequest.getRequestID() +", ");
        System.out.print("Request Date: " + simpleDateFormat.format(maintenanceRequest.getRequestDate()) +", ");
        System.out.print("Request Status: " + maintenanceRequest.getRequestStatus() +", ");
        System.out.print("Request Status Formatted: " + MaintenanceRequestStatus.getStatusName(maintenanceRequest.getRequestStatus())+", ");
        System.out.print("Request Description: " + maintenanceRequest.getRequestDescription()+", ");
        System.out.print("Affected Asset Info: ");
        System.out.print("[");
        for(Asset asset : maintenanceRequest.getAssetAffected()) {
            System.out.print("{");
            System.out.print("AssetName: " +asset.getAssetName()+", ");
            System.out.print("AssetCategory: " +asset.getAssetCategory().getCategoryName()+", ");
            System.out.print("AssetModel: " + asset.getAssetModel()+", ");
            System.out.print("AssetDescription: " + asset.getAssetDescription());
            System.out.print("}");
        }
        System.out.print("]"+", ");
        if(maintenanceRequest.getRequesterAssignee() != null)
            System.out.print("Requester Assignee: " + maintenanceRequest.getRequesterAssignee().getName() +", ");
        if(maintenanceRequest.getCommentList() != null) {
            System.out.print("Comment: ");
            System.out.print("[");
            for (MaintenanceRequest.Comment maintReqComment : maintenanceRequest.getCommentList()) {
                System.out.print("{");
                System.out.print(maintReqComment.getCommenterName().getName() + ":" + maintReqComment.getCommentString());
                System.out.print("}");
            }
            System.out.print("]");
        }
        System.out.print("Requester Name: " + maintenanceRequest.getRequesterName().getName());
    }
}
