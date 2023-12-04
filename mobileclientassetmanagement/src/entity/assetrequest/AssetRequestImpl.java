package entity.assetrequest;

import dbmanager.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AssetRequestImpl implements AssetRequestInterface {

    private Map<Integer, AssetRequest> assetRequestData;

    AssetRequestImpl() {
        this.assetRequestData = DataManager.getAssetRequestData();
    }
    @Override
    public AssetRequest get(Integer requestID) {
        return assetRequestData.get(requestID);
    }

    @Override
    public void add(AssetRequest assetRequest) {
        assetRequestData.put(assetRequest.getRequestID(), assetRequest);
    }

    @Override
    public void update(Integer reqID, AssetRequest assetRequest) {
       assetRequestData.put(reqID, assetRequest);
    }

    @Override
    public void delete(Integer requestID) {
      assetRequestData.remove(requestID);
    }

    @Override
    public List<AssetRequest> getAll() {
        return new ArrayList<>(assetRequestData.values());
    }
}
