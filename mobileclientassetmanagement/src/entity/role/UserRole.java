package mobileclientassetmanagement.src.entity.role;


import mobileclientassetmanagement.src.util.Constants;

public enum UserRole
{
    ADMIN(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN_STRING),
    ASSET_MANAGER(Constants.ROLE_ASSET_MANAGER, Constants.ROLE_ASSET_MANAGER_STRING),
    ASSET_USER(Constants.ROLE_ASSET_USER, Constants.ROLE_ASSET_USER_STRING),
    TECHNICIAN(Constants.ROLE_TECHNICIAN, Constants.ROLE_TECHNICIAN_STRING);

    private int code;
    private String roleName;

    UserRole(int code, String roleName) {
        this.code = code;
        this.roleName = roleName;
    }

    public int getCode() {
        return this.code;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public static UserRole getRoleName(int code){
        for(UserRole role : UserRole.values()){
            if(role.getCode() == code) {
                return role;
            }
        } throw new IllegalArgumentException("No UserName for the code");
    }

    public static UserRole getCode(String roleName) {
        for(UserRole role : UserRole.values()){
            if(role.getRoleName().equals(roleName)) {
                return role;
            }
        } throw new IllegalArgumentException("No UserCode for the rolename");
    }
}
