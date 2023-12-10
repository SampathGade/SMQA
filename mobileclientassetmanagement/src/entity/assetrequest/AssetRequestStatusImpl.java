package mobileclientassetmanagement.src.entity.assetrequest;

import mobileclientassetmanagement.src.entity.assetrequest.AssetRequest;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestFactoryImpl;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestInterface;
import mobileclientassetmanagement.src.entity.assetrequest.AssetRequestStatus;
import mobileclientassetmanagement.src.status.Status;

public class AssetRequestStatusImpl extends Status{
	   @Override
	    public void approve(Object entity) {
	        AssetRequest assetRequest = (AssetRequest) entity;
	        Integer requestID = assetRequest.getRequestID();
	        Integer approveStatusCode = AssetRequestStatus.APPROVED.getStatusCode();
	        assetRequest.setRequestStatus(approveStatusCode);
	        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
	        assetRequestInterface.update(requestID, assetRequest);
	    }
	   @Override
	    public void reject(Object entity) {
	        AssetRequest assetRequest = (AssetRequest) entity;
	        Integer requestID = assetRequest.getRequestID();
	        Integer rejectedStatusCode = AssetRequestStatus.REJECTED.getStatusCode();
	        assetRequest.setRequestStatus(rejectedStatusCode);
	        AssetRequestInterface assetRequestInterface = new AssetRequestFactoryImpl().createAssetRequest();
	        assetRequestInterface.update(requestID, assetRequest);
	    }
}
