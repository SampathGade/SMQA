package entity.location;

public class LocationFactoryImpl implements LocationFactory{
    @Override
    public LocationInterface createLocation() {
        return new LocationImpl();
    }
}
