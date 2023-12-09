package mobileclientassetmanagement.src.entity.useraccount;


import java.util.List;

public class UserImpl implements UserInterface {

    private Map<Integer, User> userData;
    UserImpl() {
        this.userData = DataManager.getUserData();
    }
    @Override
    public void add(User user) {
        userData.put(user.getUserID(), user);
    }

    @Override
    public void update(Integer userID, User user) {
        userData.put(userID, user);
    }

    @Override
    public void delete(Integer userID) {
        userData.remove(userID);
    }
}
