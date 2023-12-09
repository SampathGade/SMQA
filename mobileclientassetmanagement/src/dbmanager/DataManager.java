package mobileclientassetmanagement.src.dbmanager;

public class DataManager
{
    private static Map<Integer, User> userDataMap = new HashMap<>();
    public static Map<Integer, User> getUserData(){
        return userDataMap;
    }
}
