
package Client;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class GeoCoordinate {

    @SerializedName("latitude")
    @Expose
    private Float latitude;
    @SerializedName("longitude")
    @Expose
    private Float longitude;
    @SerializedName("unit")
    @Expose
    private String unit;

    /**
     * 
     * @return
     *     The latitude
     */
    public Float getLatitude() {
        return latitude;
    }

    /**
     * 
     * @param latitude
     *     The latitude
     */
    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public GeoCoordinate withLatitude(Float latitude) {
        this.latitude = latitude;
        return this;
    }

    /**
     * 
     * @return
     *     The longitude
     */
    public Float getLongitude() {
        return longitude;
    }

    /**
     * 
     * @param longitude
     *     The longitude
     */
    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public GeoCoordinate withLongitude(Float longitude) {
        this.longitude = longitude;
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

    public GeoCoordinate withUnit(String unit) {
        this.unit = unit;
        return this;
    }

}
