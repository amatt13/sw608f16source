
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MapInfo {

    private String mapHierarchyString;
    private String floorRefId; /** The */
    private SingleClient.Dimension Dimension; /** {@link SingleClient.Dimension}*/
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public String getMapHierarchyString() {
        return mapHierarchyString;
    }

    public void setMapHierarchyString(String mapHierarchyString) {
        this.mapHierarchyString = mapHierarchyString;
    }

    public String getFloorRefId() {
        return floorRefId;
    }

    public void setFloorRefId(String floorRefId) {
        this.floorRefId = floorRefId;
    }

    public SingleClient.Dimension getDimension() {
        return Dimension;
    }

    public void setDimension(SingleClient.Dimension Dimension) {
        this.Dimension = Dimension;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
