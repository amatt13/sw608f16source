
package Client;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Client {

    @SerializedName("WirelessClientLocation")
    @Expose
    private WirelessClientLocation WirelessClientLocation;

    /**
     * 
     * @return
     *     The WirelessClientLocation
     */
    public WirelessClientLocation getWirelessClientLocation() {
        return WirelessClientLocation;
    }

    /**
     * 
     * @param WirelessClientLocation
     *     The WirelessClientLocation
     */
    public void setWirelessClientLocation(WirelessClientLocation WirelessClientLocation) {
        this.WirelessClientLocation = WirelessClientLocation;
    }

    public Client withWirelessClientLocation(WirelessClientLocation WirelessClientLocation) {
        this.WirelessClientLocation = WirelessClientLocation;
        return this;
    }

}
