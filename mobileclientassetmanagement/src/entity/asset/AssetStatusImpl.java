package mobileclientassetmanagement.src.entity.asset;


import mobileclientassetmanagement.src.status.Status;

public class AssetStatusImpl extends Status {
    @Override
    public void assign(Object entity, Object owner) {
        Asset asset = (Asset) entity;
        Integer assetID = asset.getAssetID();
        Integer assignStatusCode = AssetStatus.ASSIGNED.getStatusCode();
        asset.setAssetStatus(assignStatusCode);
        asset.setAssetOwner(owner);
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.update(assetID, asset);
    }

    @Override
    public void markAsExpired(Object entity) {
        Asset asset = (Asset) entity;
        Integer assetID = asset.getAssetID();
        Integer expiredStatusCode = AssetStatus.EXPIRED.getStatusCode();
        asset.setAssetStatus(expiredStatusCode);
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.update(assetID, asset);
    }

    @Override
    public void decommision(Object entity) {
        Asset asset = (Asset) entity;
        Integer assetID = asset.getAssetID();
        Integer decommissionStatusCode = AssetStatus.DECOMMISSIONED.getStatusCode();
        asset.setAssetStatus(decommissionStatusCode);
        AssetInterface assetInterface = new AssetFactoryImpl().createAsset();
        assetInterface.update(assetID, asset);
    }
}
