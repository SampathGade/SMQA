package mobileclientassetmanagement.src.entity.useraccount;

import mobileclientassetmanagement.src.entity.role.UserRole;

public class User
{
    private Integer userID;
    private String name;
    private String emailID;
    private UserRole userRole;
    private String jobTitle;
    private String description;

    public User() {

    }

    public User(Integer userID, String name, String emailID, UserRole userRole, String jobTitle, String description) {
        this.userID = userID;
        this.name = name;
        this.emailID = emailID;
        this.userRole = userRole;
        this.jobTitle = jobTitle;
        this.description = description;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
