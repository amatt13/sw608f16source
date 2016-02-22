
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Client {

    private SingleClient.WirelessClientLocation WirelessClientLocation;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The WirelessClientLocation
     */
    public SingleClient.WirelessClientLocation getWirelessClientLocation() {
        return WirelessClientLocation;
    }

    /**
     * 
     * @param WirelessClientLocation
     *     The WirelessClientLocation
     */
    public void setWirelessClientLocation(SingleClient.WirelessClientLocation WirelessClientLocation) {
        this.WirelessClientLocation = WirelessClientLocation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder ipHelper = new StringBuilder();

        for(String s : this.WirelessClientLocation.getIpAddress()) ipHelper.append(s);

        sb.append("apMacAdress" + this.WirelessClientLocation.getApMacAddress());
        sb.append("macAddress" + this.WirelessClientLocation.getApMacAddress());
        sb.append("ipAdress" + ipHelper.toString());
        sb.append("confidenceFactor" + this.WirelessClientLocation.getConfidenceFactor().toString());
        sb.append("isGuestUser" + this.WirelessClientLocation.getIsGuestUser().toString());
        sb.append("currentlyTracked" + this.WirelessClientLocation.getCurrentlyTracked().toString());
        sb.append("mapHierarchyString" + this.WirelessClientLocation.getMapInfo().getMapHierarchyString());
        sb.append("floorRefID" + this.WirelessClientLocation.getMapInfo().getFloorRefId());
        sb.append("mapDimensionLenght" + this.WirelessClientLocation.getMapInfo().getDimension().getLength().toString());
        sb.append("mapDimensionWidth" + this.WirelessClientLocation.getMapInfo().getDimension().getWidth().toString());
        sb.append("mapDimensionHeight" + this.WirelessClientLocation.getMapInfo().getDimension().getHeight().toString());
        sb.append("mapDimensionOffsetX" + this.WirelessClientLocation.getMapInfo().getDimension().getOffsetX().toString());
        sb.append("mapDimensionOffsetY" + this.WirelessClientLocation.getMapInfo().getDimension().getOffsetY().toString());
        sb.append("mapDimensionUnit" + this.WirelessClientLocation.getMapInfo().getDimension().getUnit()); // Change to meters if otherwise implemented?
        sb.append("MapCoordinateX" + this.WirelessClientLocation.getMapCoordinate().getX().toString());
        sb.append("MapCoordinateY" + this.WirelessClientLocation.getMapCoordinate().getY().toString());
        sb.append("MapCoordinateUnit" + this.WirelessClientLocation.getMapCoordinate().getUnit());
        sb.append("currentServerTime" + this.WirelessClientLocation.getStatistics().getCurrentServerTime());
        sb.append("firstLocatedTime" + this.WirelessClientLocation.getStatistics().getFirstLocatedTime());
        sb.append("lastLocatedTime" + this.WirelessClientLocation.getStatistics().getLastLocatedTime());

        return sb.toString();
    }
}
