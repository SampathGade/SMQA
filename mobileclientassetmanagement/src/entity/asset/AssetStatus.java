package mobileclientassetmanagement.src.entity.asset;
public enum AssetStatus {
    UNASSIGNED(AssetUtil.UNASSIGNED, AssetUtil.UNASSIGNED_STRING),
    ASSIGNED(AssetUtil.ASSIGNED, AssetUtil.ASSIGNED_STRING),
    EXPIRED(AssetUtil.EXPIRED, AssetUtil.EXPIRED_STRING),
    DECOMMISSIONED(AssetUtil.DECOMMISSIONED, AssetUtil.DECOMMISSIONED_STRING);

    private int statusCode;
    private String statusName;

    AssetStatus(int statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusName() {
        return this.statusName;
    }

    public static String getStatusName(int statusCode){
        for(AssetStatus assetStatus : AssetStatus.values()){
            if(assetStatus.getStatusCode() == statusCode) {
                return assetStatus.getStatusName();
            }
        }throw new IllegalArgumentException("No StatusName for the statuscode");
    }
}
