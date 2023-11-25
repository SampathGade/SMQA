package entity.product;

import java.math.BigDecimal;

public class Product {
    private Integer productID;
    private String productName;
    private String productDescription;
    private BigDecimal productCost;

    public Product() {

    }

    public Product(Integer productID, String productName, String productDescription, BigDecimal productCost) {
        this.productID = productID;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCost = productCost;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductCost() {
        return productCost;
    }

    public void setProductCost(BigDecimal productCost) {
        this.productCost = productCost;
    }
}
