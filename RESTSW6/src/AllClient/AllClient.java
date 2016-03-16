
package AllClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;


@Generated("org.jsonschema2pojo")
public class AllClient {

    public Locations Locations;/**An instance of the class {@link Locations} */
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Locations getLocations() {
        return Locations;
    }

    public void setLocations(Locations Locations) {
        this.Locations = Locations;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
