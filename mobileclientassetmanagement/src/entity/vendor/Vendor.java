package mobileclientassetmanagement.src.entity.vendor;

import java.util.List;

import mobileclientassetmanagement.src.entity.product.Product;
public class Vendor {
	 private Integer vendorID;
	    private String vendorName;
	    private String vendorEmail;

	    private String vendorAddress;
	    private List<Product> associatedVendorProduct;

	    public Vendor() {

	    }

	    public Vendor(Integer vendorID, String vendorName, String vendorEmail, String vendorAddress, List<Product> associatedVendorProduct) {
	        this.vendorID = vendorID;
	        this.vendorName = vendorName;
	        this.vendorEmail = vendorEmail;
	        this.vendorAddress = vendorAddress;
	        this.associatedVendorProduct = associatedVendorProduct;
	    }
	    public void setVendorID(Integer vendorID) {
	        this.vendorID = vendorID;
	    }

	    public Integer getVendorID() {
	        return vendorID;
	    }

	    public void setVendorName(String vendorName) {
	        this.vendorName = vendorName;
	    }

	    public String getVendorName() {
	        return vendorName;
	    }

	    public void setVendorEmail(String vendorEmail) {
	        this.vendorEmail = vendorEmail;
	    }

	    public String getVendorEmail() {
	        return vendorEmail;
	    }

	    public void setVendorAddress(String vendorAddress) {
	        this.vendorAddress = vendorAddress;
	    }

	    public String getVendorAddress() {
	        return vendorAddress;
	    }
	    public List<Product> getAssociatedVendorProduct() {
	        return associatedVendorProduct;
	    }

	    public void setAssociatedVendorProduct(List<Product> associatedVendorProduct) {
	        this.associatedVendorProduct = associatedVendorProduct;
	    }
}
