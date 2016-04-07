
package Client;

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

    public Dimension withLength(Float length) {
        this.length = length;
        return this;
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

    public Dimension withWidth(Float width) {
        this.width = width;
        return this;
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

    public Dimension withHeight(Float height) {
        this.height = height;
        return this;
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

    public Dimension withOffsetX(Float offsetX) {
        this.offsetX = offsetX;
        return this;
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

    public Dimension withOffsetY(Float offsetY) {
        this.offsetY = offsetY;
        return this;
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

    public Dimension withUnit(String unit) {
        this.unit = unit;
        return this;
    }

}
