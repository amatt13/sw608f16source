
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MapInfo {

    private String mapHierarchyString;
    private String floorRefId;
    private SingleClient.Dimension Dimension;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The mapHierarchyString
     */
    public String getMapHierarchyString() {
        return mapHierarchyString;
    }

    /**
     * 
     * @param mapHierarchyString
     *     The mapHierarchyString
     */
    public void setMapHierarchyString(String mapHierarchyString) {
        this.mapHierarchyString = mapHierarchyString;
    }

    /**
     * 
     * @return
     *     The floorRefId
     */
    public String getFloorRefId() {
        return floorRefId;
    }

    /**
     * 
     * @param floorRefId
     *     The floorRefId
     */
    public void setFloorRefId(String floorRefId) {
        this.floorRefId = floorRefId;
    }

    /**
     * 
     * @return
     *     The Dimension
     */
    public SingleClient.Dimension getDimension() {
        return Dimension;
    }

    /**
     * 
     * @param Dimension
     *     The Dimension
     */
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
