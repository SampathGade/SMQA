package entity.assetrequest;

public class AssetRequestFactoryImpl implements AssetRequestFactory{
    @Override
    public AssetRequestInterface createAssetRequest() {
        return new AssetRequestImpl();
    }
}
