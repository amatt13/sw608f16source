
package AllClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Entry {
    /**See {@link SingleClient.WirelessClientLocation#macAddress} as it has the same propperties*/
    protected String macAddress;
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

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Boolean getCurrentlyTracked() {
        return currentlyTracked;
    }

    public void setCurrentlyTracked(Boolean currentlyTracked) {
        this.currentlyTracked = currentlyTracked;
    }

    public Double getConfidenceFactor() {
        return confidenceFactor;
    }

    public void setConfidenceFactor(Double confidenceFactor) {
        this.confidenceFactor = confidenceFactor;
    }

    public List<String> getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(List<String> ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getSsId() {
        return ssId;
    }

    public void setSsId(String ssId) {
        this.ssId = ssId;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getApMacAddress() {
        return apMacAddress;
    }

    public void setApMacAddress(String apMacAddress) {
        this.apMacAddress = apMacAddress;
    }

    public Boolean getIsGuestUser() {
        return isGuestUser;
    }

    public void setIsGuestUser(Boolean isGuestUser) {
        this.isGuestUser = isGuestUser;
    }

    public String getDot11Status() {
        return dot11Status;
    }

    public void setDot11Status(String dot11Status) {
        this.dot11Status = dot11Status;
    }

    public MapInfo getMapInfo() {
        return MapInfo;
    }

    public void setMapInfo(MapInfo MapInfo) {
        this.MapInfo = MapInfo;
    }

    public MapCoordinate getMapCoordinate() {
        return MapCoordinate;
    }

    public void setMapCoordinate(MapCoordinate MapCoordinate) {
        this.MapCoordinate = MapCoordinate;
    }

    public Statistics getStatistics() {
        return Statistics;
    }

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
