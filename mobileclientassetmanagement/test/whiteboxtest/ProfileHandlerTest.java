package whiteboxtest;


import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.handler.ProfileHandler;
import mobileclientassetmanagement.src.util.AppUtil;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileHandlerTest {
    @Before
    public void setUp() {
        AppUtil.setIsFromTest(true);
        UserUtil.handleUserImportForFirstLogin();
    }

    @Test
    public void handleExecuteForTestExecute() {
        try {
            ProfileHandler profileHandler = new ProfileHandler();
            AppUtil.setCurrentUser(DataManager.getUserData().get(1));
            profileHandler.execute();
            assertTrue("Profile displayed Successfully",true);
        }
        catch (Exception e) {
            assertFalse(true);
        }
    }
}