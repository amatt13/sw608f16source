
package AllClient;

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
     * No args constructor for use in serialization
     * 
     */
    public MapInfo() {
    }

    /**
     * 
     * @param mapHierarchyString
     * @param Image
     * @param floorRefId
     * @param Dimension
     */
    public MapInfo(String mapHierarchyString, String floorRefId, Dimension Dimension, Image Image) {
        this.mapHierarchyString = mapHierarchyString;
        this.floorRefId = floorRefId;
        this.Dimension = Dimension;
        this.Image = Image;
    }

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

}
