package mobileclientassetmanagement.src.entity.vendor;


public interface VendorInterface {
    void add(Vendor vendor);
    void update(Integer vendorID, Vendor vendor);
    void delete(Integer vendorID);
}
