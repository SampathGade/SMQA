package mobileclientassetmanagement.src.entity.asset;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import java.util.Map;

public class AssetImpl implements AssetInterface{

    private Map<Integer, Asset> assetData;

    AssetImpl() {
        this.assetData = DataManager.getAssetData();
    }

    @Override
    public void add(Asset asset) {
        assetData.put(asset.getAssetID(), asset);
    }

    @Override
    public void update(Integer assetID, Asset asset) {
        assetData.put(assetID, asset);
    }

    @Override
    public void delete(Integer assetID) {
        assetData.remove(assetID);
    }
}
