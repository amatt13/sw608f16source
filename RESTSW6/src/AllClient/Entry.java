
package AllClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Entry {

    private String macAddress;
    private Boolean currentlyTracked;
    private Double confidenceFactor;
    private List<String> ipAddress = new ArrayList<String>();
    private String ssId;
    private String band;
    private String apMacAddress;
    private Boolean isGuestUser;
    private String dot11Status;
    private MapInfo MapInfo;
    private MapCoordinate MapCoordinate;
    private Statistics Statistics;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public Double getConfidenceFactor() {
        return confidenceFactor;
    }

    /**
     * 
     * @param confidenceFactor
     *     The confidenceFactor
     */
    public void setConfidenceFactor(Double confidenceFactor) {
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
