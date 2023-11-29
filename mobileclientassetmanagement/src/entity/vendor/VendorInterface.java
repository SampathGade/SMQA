package mobileclientassetmanagement.src.entity.vendor;


import java.util.List;

public interface VendorInterface {
    Vendor get(Integer vendorID);
    void add(Vendor vendor);
    void update(Integer vendorID, Vendor vendor);
    void delete(Integer vendorID);
    List<Vendor> getAll();
}
