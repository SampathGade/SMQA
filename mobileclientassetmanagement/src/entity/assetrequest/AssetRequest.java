package mobileclientassetmanagement.src.entity.assetrequest;


import mobileclientassetmanagement.src.entity.asset.Asset;
import mobileclientassetmanagement.src.entity.useraccount.User;

import java.util.Date;
import java.util.List;

public class AssetRequest {
    private Integer requestID;
    private Date requestDate;
    private Integer requestStatus;
    private String requestDescription;
    private List<Asset> assetList;
    private User requesterName;
    private User requesterAssignee;

    private List<Comment> commentList;

    public AssetRequest() {

    }

    public static final class Comment {
        private String commentString;

        private User commenterName;

        public Comment() {

        }

        public Comment(String commentString, User commenterName) {
            this.commentString = commentString;
            this.commenterName = commenterName;
        }

        public String getCommentString() {
            return commentString;
        }

        public void setCommentString(String commentString) {
            this.commentString = commentString;
        }

        public User getCommenterName() {
            return commenterName;
        }

        public void setCommenterName(User commenterName) {
            this.commenterName = commenterName;
        }
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    public User getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(User requesterName) {
        this.requesterName = requesterName;
    }

    public User getRequesterAssignee() {
        return requesterAssignee;
    }

    public void setRequesterAssignee(User requesterAssignee) {
        this.requesterAssignee = requesterAssignee;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
