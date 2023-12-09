package mobileclientassetmanagement.src.entity.useraccount;


import java.util.List;

public interface UserInterface {
    void add(User user);
    void update(Integer userID, User user);
    void delete(Integer userID);
}
