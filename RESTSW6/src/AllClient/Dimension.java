
package AllClient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Dimension {

    @SerializedName("length")
    @Expose
    private Float length;
    @SerializedName("width")
    @Expose
    private Float width;
    @SerializedName("height")
    @Expose
    private Float height;
    @SerializedName("offsetX")
    @Expose
    private Float offsetX;
    @SerializedName("offsetY")
    @Expose
    private Float offsetY;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Dimension() {
    }

    /**
     * 
     * @param unit
     * @param height
     * @param offsetY
     * @param offsetX
     * @param width
     * @param length
     */
    public Dimension(Float length, Float width, Float height, Float offsetX, Float offsetY, String unit) {
        this.length = length;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The length
     */
    public Float getLength() {
        return length;
    }

    /**
     * 
     * @param length
     *     The length
     */
    public void setLength(Float length) {
        this.length = length;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Float getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Float width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Float getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Float height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The offsetX
     */
    public Float getOffsetX() {
        return offsetX;
    }

    /**
     * 
     * @param offsetX
     *     The offsetX
     */
    public void setOffsetX(Float offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * 
     * @return
     *     The offsetY
     */
    public Float getOffsetY() {
        return offsetY;
    }

    /**
     * 
     * @param offsetY
     *     The offsetY
     */
    public void setOffsetY(Float offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * 
     * @return
     *     The unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 
     * @param unit
     *     The unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
