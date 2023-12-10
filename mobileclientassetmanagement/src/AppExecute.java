package mobileclientassetmanagement.src;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import mobileclientassetmanagement.src.entity.asset.AssetUtil;
import mobileclientassetmanagement.src.entity.useraccount.UserUtil;
import mobileclientassetmanagement.src.util.AppUtil;

public class AppExecute {
    static {
        UserUtil.handleUserImportForFirstLogin(); AssetUtil.handleAssetImportForFirstLogin();
    }
    public static void main(String[] args) {
        System.out.println("Mobile Client Asset Management"); System.out.println("Welcome!"); if(!isFirstUser()) { AppUtil.handleLoginView();}  else { AppUtil.handleSignUp(); }
    }
    private static Boolean isFirstUser() {
        return DataManager.getUserData().isEmpty();
    }
}
