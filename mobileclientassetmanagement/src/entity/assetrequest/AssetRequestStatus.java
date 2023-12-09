package mobileclientassetmanagement.src.entity.assetrequest;

public enum AssetRequestStatus {
    OPEN(AssetRequestUtil.OPEN, AssetRequestUtil.OPEN_STRING),
    APPROVED(AssetRequestUtil.APPROVED, AssetRequestUtil.APPROVED_STRING),
    REJECTED(AssetRequestUtil.REJECTED, AssetRequestUtil.REJECTED_STRING);
    private int statusCode;
    private String statusName;

    AssetRequestStatus(int statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public static String getStatusName(int statusCode){
        for(AssetRequestStatus assetReqStatus : AssetRequestStatus.values()){
            if(assetReqStatus.getStatusCode() == statusCode) {
                return assetReqStatus.getStatusName();
            }
        }
        throw new IllegalArgumentException("No StatusName for the statuscode");
    }

    public static int getStatusCode(String statusName) {
        for(AssetRequestStatus assetReqStatus : AssetRequestStatus.values()){
            if(assetReqStatus.getStatusName().equals(statusName)) {
                return assetReqStatus.getStatusCode();
            }
        }
        throw new IllegalArgumentException("No StatusCode for the StatusName");
    }
}
