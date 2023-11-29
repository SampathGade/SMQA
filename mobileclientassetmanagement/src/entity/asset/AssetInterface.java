package mobileclientassetmanagement.src.entity.asset;

import java.util.List;

public interface AssetInterface {
    Asset get(Integer assetID);
    void add(Asset asset);
    void update(Integer assetID, Asset asset);
    void delete(Integer assetID);
    List<Asset> getAll();
}
