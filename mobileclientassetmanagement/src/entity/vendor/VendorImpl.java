package mobileclientassetmanagement.src.entity.vendor;



import java.util.Map;

import mobileclientassetmanagement.src.dbmanager.DataManager;

public class VendorImpl implements VendorInterface {
    private Map<Integer, Vendor> vendorData;

    VendorImpl() {
        this.vendorData = DataManager.getVendorData();
    }

    @Override
    public void add(Vendor vendor) {
        vendorData.put(vendor.getVendorID(), vendor);
    }

    @Override
    public void update(Integer vendorID, Vendor vendor) {
        vendorData.put(vendorID, vendor);
    }

    @Override
    public void delete(Integer vendorID) {
        vendorData.remove(vendorID);
    }
}