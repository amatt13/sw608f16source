
package AllClient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class MapCoordinate {

    @SerializedName("x")
    @Expose
    private Float x;
    @SerializedName("y")
    @Expose
    private Float y;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * No args constructor for use in serialization
     * 
     */
    public MapCoordinate() {
    }

    /**
     * 
     * @param unit
     * @param y
     * @param x
     */
    public MapCoordinate(Float x, Float y, String unit) {
        this.x = x;
        this.y = y;
        this.unit = unit;
    }

    /**
     * 
     * @return
     *     The x
     */
    public Float getX() {
        return x;
    }

    /**
     * 
     * @param x
     *     The x
     */
    public void setX(Float x) {
        this.x = x;
    }

    /**
     * 
     * @return
     *     The y
     */
    public Float getY() {
        return y;
    }

    /**
     * 
     * @param y
     *     The y
     */
    public void setY(Float y) {
        this.y = y;
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
