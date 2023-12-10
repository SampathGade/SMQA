package mobileclientassetmanagement.src.entity.assetrequest;

import java.util.List;

public interface AssetRequestInterface {
    void add(AssetRequest assetRequest);
    void update(Integer reqID, AssetRequest assetRequest);
    void delete(Integer requestID);
}
