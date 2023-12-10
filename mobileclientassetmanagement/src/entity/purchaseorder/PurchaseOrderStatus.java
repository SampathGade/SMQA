package mobileclientassetmanagement.src.entity.purchaseorder;


public enum PurchaseOrderStatus {
    OPEN(PurchaseOrderUtil.OPEN, PurchaseOrderUtil.OPEN_STRING),
    PAID(PurchaseOrderUtil.PAID, PurchaseOrderUtil.PAID_STRING),
    PUSH(PurchaseOrderUtil.PUSH, PurchaseOrderUtil.PUSH_STRING);

    private int statusCode;

    private String statusName;

    PurchaseOrderStatus(int statusCode, String statusName) {
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
        for(PurchaseOrderStatus purchaseOrderStatus : PurchaseOrderStatus.values()){
            if(purchaseOrderStatus.getStatusCode() == statusCode) {
                return purchaseOrderStatus.getStatusName();
            }
        } throw new IllegalArgumentException("No StatusName for the statuscode");
    }

}
