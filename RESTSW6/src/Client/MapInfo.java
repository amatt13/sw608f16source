
package Client;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class MapInfo {

    @SerializedName("mapHierarchyString")
    @Expose
    private String mapHierarchyString;
    @SerializedName("floorRefId")
    @Expose
    private String floorRefId;
    @SerializedName("Dimension")
    @Expose
    private Dimension Dimension;
    @SerializedName("Image")
    @Expose
    private Image Image;

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

    public MapInfo withMapHierarchyString(String mapHierarchyString) {
        this.mapHierarchyString = mapHierarchyString;
        return this;
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

    public MapInfo withFloorRefId(String floorRefId) {
        this.floorRefId = floorRefId;
        return this;
    }

    /**
     * 
     * @return
     *     The Dimension
     */
    public Dimension getDimension() {
        return Dimension;
    }

    /**
     * 
     * @param Dimension
     *     The Dimension
     */
    public void setDimension(Dimension Dimension) {
        this.Dimension = Dimension;
    }

    public MapInfo withDimension(Dimension Dimension) {
        this.Dimension = Dimension;
        return this;
    }

    /**
     * 
     * @return
     *     The Image
     */
    public Image getImage() {
        return Image;
    }

    /**
     * 
     * @param Image
     *     The Image
     */
    public void setImage(Image Image) {
        this.Image = Image;
    }

    public MapInfo withImage(Image Image) {
        this.Image = Image;
        return this;
    }

}
