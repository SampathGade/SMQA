package mobileclientassetmanagement.src.entity.vendor;

public class VendorFactoryImpl implements VendorFactory{
    @Override
    public VendorInterface createVendor() {
        return new VendorImpl();
    }
}
