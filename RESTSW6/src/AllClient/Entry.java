
package AllClient;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Entry {

    @SerializedName("macAddress")
    @Expose
    private String macAddress;
    @SerializedName("currentlyTracked")
    @Expose
    private Boolean currentlyTracked;
    @SerializedName("confidenceFactor")
    @Expose
    private Float confidenceFactor;
    @SerializedName("ipAddress")
    @Expose
    private List<String> ipAddress = new ArrayList<String>();
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("ssId")
    @Expose
    private String ssId;
    @SerializedName("band")
    @Expose
    private String band;
    @SerializedName("apMacAddress")
    @Expose
    private String apMacAddress;
    @SerializedName("isGuestUser")
    @Expose
    private Boolean isGuestUser;
    @SerializedName("dot11Status")
    @Expose
    private String dot11Status;
    @SerializedName("MapInfo")
    @Expose
    private MapInfo MapInfo;
    @SerializedName("MapCoordinate")
    @Expose
    private MapCoordinate MapCoordinate;
    @SerializedName("Statistics")
    @Expose
    private Statistics Statistics;
    @SerializedName("GeoCoordinate")
    @Expose
    private GeoCoordinate GeoCoordinate;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entry() {
    }

    /**
     * 
     * @param Statistics
     * @param GeoCoordinate
     * @param confidenceFactor
     * @param apMacAddress
     * @param macAddress
     * @param MapInfo
     * @param ssId
     * @param band
     * @param isGuestUser
     * @param MapCoordinate
     * @param currentlyTracked
     * @param dot11Status
     * @param userName
     * @param ipAddress
     */
    public Entry(String macAddress, Boolean currentlyTracked, Float confidenceFactor, List<String> ipAddress, String userName, String ssId, String band, String apMacAddress, Boolean isGuestUser, String dot11Status, MapInfo MapInfo, MapCoordinate MapCoordinate, Statistics Statistics, GeoCoordinate GeoCoordinate) {
        this.macAddress = macAddress;
        this.currentlyTracked = currentlyTracked;
        this.confidenceFactor = confidenceFactor;
        this.ipAddress = ipAddress;
        this.userName = userName;
        this.ssId = ssId;
        this.band = band;
        this.apMacAddress = apMacAddress;
        this.isGuestUser = isGuestUser;
        this.dot11Status = dot11Status;
        this.MapInfo = MapInfo;
        this.MapCoordinate = MapCoordinate;
        this.Statistics = Statistics;
        this.GeoCoordinate = GeoCoordinate;
    }

    /**
     * 
     * @return
     *     The macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * 
     * @param macAddress
     *     The macAddress
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * 
     * @return
     *     The currentlyTracked
     */
    public Boolean getCurrentlyTracked() {
        return currentlyTracked;
    }

    /**
     * 
     * @param currentlyTracked
     *     The currentlyTracked
     */
    public void setCurrentlyTracked(Boolean currentlyTracked) {
        this.currentlyTracked = currentlyTracked;
    }

    /**
     * 
     * @return
     *     The confidenceFactor
     */
    public Float getConfidenceFactor() {
        return confidenceFactor;
    }

    /**
     * 
     * @param confidenceFactor
     *     The confidenceFactor
     */
    public void setConfidenceFactor(Float confidenceFactor) {
        this.confidenceFactor = confidenceFactor;
    }

    /**
     * 
     * @return
     *     The ipAddress
     */
    public List<String> getIpAddress() {
        return ipAddress;
    }

    /**
     * 
     * @param ipAddress
     *     The ipAddress
     */
    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * 
     * @return
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The ssId
     */
    public String getSsId() {
        return ssId;
    }

    /**
     * 
     * @param ssId
     *     The ssId
     */
    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    /**
     * 
     * @return
     *     The band
     */
    public String getBand() {
        return band;
    }

    /**
     * 
     * @param band
     *     The band
     */
    public void setBand(String band) {
        this.band = band;
    }

    /**
     * 
     * @return
     *     The apMacAddress
     */
    public String getApMacAddress() {
        return apMacAddress;
    }

    /**
     * 
     * @param apMacAddress
     *     The apMacAddress
     */
    public void setApMacAddress(String apMacAddress) {
        this.apMacAddress = apMacAddress;
    }

    /**
     * 
     * @return
     *     The isGuestUser
     */
    public Boolean getIsGuestUser() {
        return isGuestUser;
    }

    /**
     * 
     * @param isGuestUser
     *     The isGuestUser
     */
    public void setIsGuestUser(Boolean isGuestUser) {
        this.isGuestUser = isGuestUser;
    }

    /**
     * 
     * @return
     *     The dot11Status
     */
    public String getDot11Status() {
        return dot11Status;
    }

    /**
     * 
     * @param dot11Status
     *     The dot11Status
     */
    public void setDot11Status(String dot11Status) {
        this.dot11Status = dot11Status;
    }

    /**
     * 
     * @return
     *     The MapInfo
     */
    public MapInfo getMapInfo() {
        return MapInfo;
    }

    /**
     * 
     * @param MapInfo
     *     The MapInfo
     */
    public void setMapInfo(MapInfo MapInfo) {
        this.MapInfo = MapInfo;
    }

    /**
     * 
     * @return
     *     The MapCoordinate
     */
    public MapCoordinate getMapCoordinate() {
        return MapCoordinate;
    }

    /**
     * 
     * @param MapCoordinate
     *     The MapCoordinate
     */
    public void setMapCoordinate(MapCoordinate MapCoordinate) {
        this.MapCoordinate = MapCoordinate;
    }

    /**
     * 
     * @return
     *     The Statistics
     */
    public Statistics getStatistics() {
        return Statistics;
    }

    /**
     * 
     * @param Statistics
     *     The Statistics
     */
    public void setStatistics(Statistics Statistics) {
        this.Statistics = Statistics;
    }

    /**
     * 
     * @return
     *     The GeoCoordinate
     */
    public GeoCoordinate getGeoCoordinate() {
        return GeoCoordinate;
    }

    /**
     * 
     * @param GeoCoordinate
     *     The GeoCoordinate
     */
    public void setGeoCoordinate(GeoCoordinate GeoCoordinate) {
        this.GeoCoordinate = GeoCoordinate;
    }

}
