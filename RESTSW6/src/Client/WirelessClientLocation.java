
package Client;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class WirelessClientLocation {

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

    public WirelessClientLocation withMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
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

    public WirelessClientLocation withCurrentlyTracked(Boolean currentlyTracked) {
        this.currentlyTracked = currentlyTracked;
        return this;
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

    public WirelessClientLocation withConfidenceFactor(Float confidenceFactor) {
        this.confidenceFactor = confidenceFactor;
        return this;
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

    public WirelessClientLocation withIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
        return this;
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

    public WirelessClientLocation withUserName(String userName) {
        this.userName = userName;
        return this;
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

    public WirelessClientLocation withSsId(String ssId) {
        this.ssId = ssId;
        return this;
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

    public WirelessClientLocation withBand(String band) {
        this.band = band;
        return this;
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

    public WirelessClientLocation withApMacAddress(String apMacAddress) {
        this.apMacAddress = apMacAddress;
        return this;
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

    public WirelessClientLocation withIsGuestUser(Boolean isGuestUser) {
        this.isGuestUser = isGuestUser;
        return this;
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

    public WirelessClientLocation withDot11Status(String dot11Status) {
        this.dot11Status = dot11Status;
        return this;
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

    public WirelessClientLocation withMapInfo(MapInfo MapInfo) {
        this.MapInfo = MapInfo;
        return this;
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

    public WirelessClientLocation withMapCoordinate(MapCoordinate MapCoordinate) {
        this.MapCoordinate = MapCoordinate;
        return this;
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

    public WirelessClientLocation withStatistics(Statistics Statistics) {
        this.Statistics = Statistics;
        return this;
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

    public WirelessClientLocation withGeoCoordinate(GeoCoordinate GeoCoordinate) {
        this.GeoCoordinate = GeoCoordinate;
        return this;
    }

}
