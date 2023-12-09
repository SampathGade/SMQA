package mobileclientassetmanagement.src.entity.useraccount;


public class UserUtil {
    public static final List<String> SKIPPED_INPUT_FIELDS = Arrays.asList("userID");

    public static final String[] EXPORT_HEADER = {"UserID", "UserName", "UserEmail", "UserRole", "JobTitle", "Description"};
    public static Integer generateUserID() {
        Integer userID= 1;
        Map<Integer, User> userDataMap = DataManager.getUserData();
        Map.Entry<Integer, User> lastEntry = null;
        for(Map.Entry<Integer, User> userEntry : userDataMap.entrySet()){
            lastEntry = userEntry;
        }
        userID = lastEntry == null ? userID : lastEntry.getKey() + Constants.INTEGER_ONE;
        return userID;
    }

    public static Scanner getTestInputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        if(AppUtil.isFromTest()) {
            String testInput = "Leo\nleo@email.com\n1\nmanager\ndescription test\n";
            System.setIn(new ByteArrayInputStream(testInput.getBytes()));
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static String getPathForUserData() {
        String resourcesFolder = "src/resources";
        String currentWorkingDirectory = System.getProperty("user.dir");
        return Paths.get(currentWorkingDirectory, resourcesFolder, Constants.USER_DATA_CSV).toAbsolutePath().toString();
    }

    public static void handleUserImportForFirstLogin() {
        String resourcePath = getPathForUserData();
        ImportFactory.getHandler(Constants.USER).handleImport(resourcePath);
    }
}
