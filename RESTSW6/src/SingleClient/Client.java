
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Client {

    private SingleClient.WirelessClientLocation WirelessClientLocation;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The WirelessClientLocation
     */
    public SingleClient.WirelessClientLocation getWirelessClientLocation() {
        return WirelessClientLocation;
    }

    /**
     * 
     * @param WirelessClientLocation
     *     The WirelessClientLocation
     */
    public void setWirelessClientLocation(SingleClient.WirelessClientLocation WirelessClientLocation) {
        this.WirelessClientLocation = WirelessClientLocation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}