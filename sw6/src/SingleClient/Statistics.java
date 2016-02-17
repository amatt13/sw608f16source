
package SingleClient;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Statistics {

    private String currentServerTime;
    private String firstLocatedTime;
    private String lastLocatedTime;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The currentServerTime
     */
    public String getCurrentServerTime() {
        return currentServerTime;
    }

    /**
     * 
     * @param currentServerTime
     *     The currentServerTime
     */
    public void setCurrentServerTime(String currentServerTime) {
        this.currentServerTime = currentServerTime;
    }

    /**
     * 
     * @return
     *     The firstLocatedTime
     */
    public String getFirstLocatedTime() {
        return firstLocatedTime;
    }

    /**
     * 
     * @param firstLocatedTime
     *     The firstLocatedTime
     */
    public void setFirstLocatedTime(String firstLocatedTime) {
        this.firstLocatedTime = firstLocatedTime;
    }

    /**
     * 
     * @return
     *     The lastLocatedTime
     */
    public String getLastLocatedTime() {
        return lastLocatedTime;
    }

    /**
     * 
     * @param lastLocatedTime
     *     The lastLocatedTime
     */
    public void setLastLocatedTime(String lastLocatedTime) {
        this.lastLocatedTime = lastLocatedTime;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
