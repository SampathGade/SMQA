package mobileclientassetmanagement.src.entity.purchaseorder;

import mobileclientassetmanagement.src.entity.vendor.Vendor;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PurchaseOrder {
    private Integer orderID;
    private Date orderDate;
    private String billingAddress;
    private Vendor orderVendor;

    private BigDecimal totalCost;

    private Integer status;

    private List<Item> orderItems;

    public static final class Item {
        private String itemName;
        private Integer quantity;
        private BigDecimal price;

        public Item() {

        }
        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }

    public PurchaseOrder() {

    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Vendor getOrderVendor() {
        return orderVendor;
    }

    public void setOrderVendor(Vendor orderVendor) {
        this.orderVendor = orderVendor;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public List<Item> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<Item> orderItems) {
        this.orderItems = orderItems;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
