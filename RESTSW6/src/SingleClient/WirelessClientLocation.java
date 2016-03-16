
package SingleClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class WirelessClientLocation {

    /**MAC-Address of a user*/
    private String macAddress;
    /**Is the user currently being tracked?*/
    private Boolean currentlyTracked;
    /**within how large an area of the given location are the system sure the user is e.g. "the user is within 10 feet
     * of this location"*/
    private Double confidenceFactor;
    /**The Ip of the user being looked at*/
    private List<String> ipAddress = new ArrayList<String>();
    private String ssId;
    private String band;
    private String apMacAddress;
    private Boolean isGuestUser;
    private String dot11Status;
    /**What map is the user on {@link SingleClient.MapInfo}*/
    private SingleClient.MapInfo MapInfo;
    /**Where is the user on the map {@link SingleClient.MapCoordinate}*/
    private SingleClient.MapCoordinate MapCoordinate;
    private SingleClient.Statistics Statistics;
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
    public SingleClient.MapInfo getMapInfo() {
        return MapInfo;
    }

    /**
     * 
     * @param MapInfo
     *     The MapInfo
     */
    public void setMapInfo(SingleClient.MapInfo MapInfo) {
        this.MapInfo = MapInfo;
    }

    /**
     * 
     * @return
     *     The MapCoordinate
     */
    public SingleClient.MapCoordinate getMapCoordinate() {
        return MapCoordinate;
    }

    /**
     * 
     * @param MapCoordinate
     *     The MapCoordinate
     */
    public void setMapCoordinate(SingleClient.MapCoordinate MapCoordinate) {
        this.MapCoordinate = MapCoordinate;
    }

    /**
     * 
     * @return
     *     The Statistics
     */
    public SingleClient.Statistics getStatistics() {
        return Statistics;
    }

    /**
     * 
     * @param Statistics
     *     The Statistics
     */
    public void setStatistics(SingleClient.Statistics Statistics) {
        this.Statistics = Statistics;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
