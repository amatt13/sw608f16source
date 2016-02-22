
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Dimension {

    private Double length;
    private Double width;
    private Double height;
    private Double offsetX;
    private Double offsetY;
    private String unit;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The length
     */
    public Double getLength() {
        return length;
    }

    /**
     * 
     * @param length
     *     The length
     */
    public void setLength(Double length) {
        this.length = length;
    }

    /**
     * 
     * @return
     *     The width
     */
    public Double getWidth() {
        return width;
    }

    /**
     * 
     * @param width
     *     The width
     */
    public void setWidth(Double width) {
        this.width = width;
    }

    /**
     * 
     * @return
     *     The height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 
     * @param height
     *     The height
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 
     * @return
     *     The offsetX
     */
    public Double getOffsetX() {
        return offsetX;
    }

    /**
     * 
     * @param offsetX
     *     The offsetX
     */
    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * 
     * @return
     *     The offsetY
     */
    public Double getOffsetY() {
        return offsetY;
    }

    /**
     * 
     * @param offsetY
     *     The offsetY
     */
    public void setOffsetY(Double offsetY) {
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
