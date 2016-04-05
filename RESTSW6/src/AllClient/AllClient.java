
package AllClient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class AllClient {

    @SerializedName("Locations")
    @Expose
    private Locations Locations;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AllClient() {
    }

    /**
     * 
     * @param Locations
     */
    public AllClient(Locations Locations) {
        this.Locations = Locations;
    }

    /**
     * 
     * @return
     *     The Locations
     */
    public Locations getLocations() {
        return Locations;
    }

    /**
     * 
     * @param Locations
     *     The Locations
     */
    public void setLocations(Locations Locations) {
        this.Locations = Locations;
    }

}
