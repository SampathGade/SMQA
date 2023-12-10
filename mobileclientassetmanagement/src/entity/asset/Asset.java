package mobileclientassetmanagement.src.entity.asset;

import mobileclientassetmanagement.src.entity.category.Category;
import mobileclientassetmanagement.src.entity.location.Location;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Asset {
    private Integer assetID;
    private String assetName;

    private Category assetCategory;
    private String assetModel;
    private String assetDescription;
    private Location assetLocation;

    private Integer assetStatus;
    private Date assetAcqisationDate;
    private Date expiryDate;

    private Object assetOwner;


    public Asset()
    {

    }

    public Asset(Integer assetID, String assetName) {
        this(assetID, assetName, null, "", "", null, AssetStatus.UNASSIGNED.getStatusCode(), Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()), null, null);
    }

    public Asset(Integer assetID, String assetName, Category assetCategory, String assetModel, String assetDescription, Location assetLocation, Integer assetStatus, Date assetAcqisationDate, Date expiryDate, Object assetOwner) {
        this.assetID = assetID;
        this.assetName = assetName;
        this.assetCategory = assetCategory;
        this.assetModel = assetModel;
        this.assetDescription = assetDescription;
        this.assetLocation = assetLocation;
        this.assetStatus = assetStatus;
        this.assetAcqisationDate = assetAcqisationDate;
        this.expiryDate = expiryDate;
        this.assetOwner = assetOwner;
    }

    public Integer getAssetID() {
        return assetID;
    }

    public void setAssetID(Integer assetID) {
        this.assetID = assetID;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public Category getAssetCategory() {
        return assetCategory;
    }

    public void setAssetCategory(Category assetCategory) {
        this.assetCategory = assetCategory;
    }

    public String getAssetModel() {
        return assetModel;
    }

    public void setAssetModel(String assetModel) {
        this.assetModel = assetModel;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public Location getAssetLocation() {
        return assetLocation;
    }

    public void setAssetLocation(Location assetLocation) {
        this.assetLocation = assetLocation;
    }

    public Integer getAssetStatus() {
        return assetStatus;
    }

    public void setAssetStatus(Integer assetStatus) {
        this.assetStatus = assetStatus;
    }

    public Date getAssetAcqisationDate() {
        return assetAcqisationDate;
    }

    public void setAssetAcqisationDate(Date assetAcqisationDate) {
        this.assetAcqisationDate = assetAcqisationDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    public Object getAssetOwner() {
        return assetOwner;
    }

    public void setAssetOwner(Object assetOwner) {
        this.assetOwner = assetOwner;
    }
}
