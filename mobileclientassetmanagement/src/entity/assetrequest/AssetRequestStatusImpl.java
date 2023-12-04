package entity.assetrequest;
import status.Status;

public class AssetRequestStatusImpl extends Status {

    @Override
    public void approve(Object entity) {
        if(!(entity instanceof AssetRequest)){
            throw new RuntimeException();
        }
        AssetRequest assetRequest = (AssetRequest) entity;
        Integer requestID = assetRequest.getRequestID();
        Integer approveStatusCode = AssetRequestStatus.APPROVED.getStatusCode();
        assetRequest.setRequestStatus(approveStatusCode);
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.update(requestID, assetRequest);
    }

    @Override
    public void reject(Object entity) {
        if(!(entity instanceof AssetRequest)){
            throw new RuntimeException();
        }
        AssetRequest assetRequest = (AssetRequest) entity;
        Integer requestID = assetRequest.getRequestID();
        Integer rejectedStatusCode = AssetRequestStatus.REJECTED.getStatusCode();
        assetRequest.setRequestStatus(rejectedStatusCode);
        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
        assetRequestInterface.update(requestID, assetRequest);
    }
}
