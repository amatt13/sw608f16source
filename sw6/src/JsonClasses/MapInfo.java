
package JsonClasses;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class MapInfo {

    private String mapHierarchyString;
    private Integer floorRefId;
    private JsonClasses.Dimension Dimension;
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
    public Integer getFloorRefId() {
        return floorRefId;
    }

    /**
     * 
     * @param floorRefId
     *     The floorRefId
     */
    public void setFloorRefId(Integer floorRefId) {
        this.floorRefId = floorRefId;
    }

    /**
     * 
     * @return
     *     The Dimension
     */
    public JsonClasses.Dimension getDimension() {
        return Dimension;
    }

    /**
     * 
     * @param Dimension
     *     The Dimension
     */
    public void setDimension(JsonClasses.Dimension Dimension) {
        this.Dimension = Dimension;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
