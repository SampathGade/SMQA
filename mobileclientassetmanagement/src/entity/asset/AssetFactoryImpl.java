package mobileclientassetmanagement.src.entity.asset;

public class AssetFactoryImpl implements AssetFactory{
    @Override
    public AssetInterface createAsset() {
        return new AssetImpl();
    }
}
