package mobileclientassetmanagement.src.dbmanager;

import mobileclientassetmanagement.src.entity.useraccount.User;

import java.util.HashMap;
import java.util.Map;

public class DataManager
{
    private static Map<Integer, User> userDataMap = new HashMap<>();
    public static Map<Integer, User> getUserData(){
        return userDataMap;
    }
}
