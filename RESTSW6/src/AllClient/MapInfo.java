
package AllClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MapInfo {
/**As they have the same propperties see {@link SingleClient.MapInfo}*/
    private String mapHierarchyString;
    private String floorRefId;
    private Dimension Dimension;
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

    public Dimension getDimension() {
        return Dimension;
    }

    public void setDimension(Dimension Dimension) {
        this.Dimension = Dimension;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
