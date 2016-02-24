
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

        sb.append("Jeg er så glad i dag \n");
        sb.append("apMacAdress" +
                this.WirelessClientLocation.getApMacAddress() + "\n");
        sb.append("macAddress" +
                this.WirelessClientLocation.getMacAddress() + "\n");
        sb.append("ipAdress" +
                ipHelper.toString() + "\n");
        sb.append("confidenceFactor" +
                this.WirelessClientLocation.getConfidenceFactor().toString() + "\n");
        sb.append("isGuestUser" +
                this.WirelessClientLocation.getIsGuestUser().toString() + "\n");
        sb.append("currentlyTracked" +
                this.WirelessClientLocation.getCurrentlyTracked().toString() + "\n");
        sb.append("mapHierarchyString" +
                this.WirelessClientLocation.getMapInfo().getMapHierarchyString() + "\n");
        sb.append("floorRefID" +
                this.WirelessClientLocation.getMapInfo().getFloorRefId() + "\n");
        sb.append("mapDimensionLenght" +
                this.WirelessClientLocation.getMapInfo().getDimension().getLength().toString() + "\n");
        sb.append("mapDimensionWidth" +
                this.WirelessClientLocation.getMapInfo().getDimension().getWidth().toString() + "\n");
        sb.append("mapDimensionHeight" +
                this.WirelessClientLocation.getMapInfo().getDimension().getHeight().toString() + "\n");
        sb.append("mapDimensionOffsetX" +
                this.WirelessClientLocation.getMapInfo().getDimension().getOffsetX().toString() + "\n");
        sb.append("mapDimensionOffsetY" +
                this.WirelessClientLocation.getMapInfo().getDimension().getOffsetY().toString() + "\n");
        sb.append("mapDimensionUnit" +
                this.WirelessClientLocation.getMapInfo().getDimension().getUnit() + "\n"); // Change to meters if otherwise implemented?
        sb.append("MapCoordinateX" +
                this.WirelessClientLocation.getMapCoordinate().getX().toString() + "\n");
        sb.append("MapCoordinateY" +
                this.WirelessClientLocation.getMapCoordinate().getY().toString() + "\n");
        sb.append("MapCoordinateUnit" +
                this.WirelessClientLocation.getMapCoordinate().getUnit() + "\n");
        sb.append("currentServerTime" +
                this.WirelessClientLocation.getStatistics().getCurrentServerTime() + "\n");
        sb.append("firstLocatedTime" +
                this.WirelessClientLocation.getStatistics().getFirstLocatedTime() + "\n");
        sb.append("lastLocatedTime" +
                this.WirelessClientLocation.getStatistics().getLastLocatedTime() + "\n");

        return sb.toString();
    }
}
