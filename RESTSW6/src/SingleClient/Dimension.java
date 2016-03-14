
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Dimension {
    /** This is the length of the map e.g. 500feet*/
    private Double length;
    /** This is the width of the map e.g. 200feet*/
    private Double width;
    /** This is the hight of the map, this is relevant for multi floor buildings*/
    private Double height;
    /** The x-coordinate of the point the relative coordinates are calculated from, this is
     per standard {0;0} but can be made a real geographical location */
    private Double offsetX;
    /** The y-coordinate of the point the relative coordinates are calculated from, this is
     per standard {0;0} but can be made a real geographical location */
    private Double offsetY;
    /**What unit the locations is in e.g. feet or meters. meaning for feet {1;1} is one feet away
     form the offset both out the x and the y aksis */
    private String unit;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(Double offsetX) {
        this.offsetX = offsetX;
    }

    public Double getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(Double offsetY) {
        this.offsetY = offsetY;
    }

    public String getUnit() {
        return unit;
    }

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
