package mobileclientassetmanagement.src.entity.useraccount;

public class UserFactoryImpl implements UserFactory{
    @Override
    public UserInterface createUser() {
        return new UserImpl();
    }
}
