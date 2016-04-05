
package AllClient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Statistics {

    @SerializedName("currentServerTime")
    @Expose
    private String currentServerTime;
    @SerializedName("firstLocatedTime")
    @Expose
    private String firstLocatedTime;
    @SerializedName("lastLocatedTime")
    @Expose
    private String lastLocatedTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Statistics() {
    }

    /**
     * 
     * @param firstLocatedTime
     * @param lastLocatedTime
     * @param currentServerTime
     */
    public Statistics(String currentServerTime, String firstLocatedTime, String lastLocatedTime) {
        this.currentServerTime = currentServerTime;
        this.firstLocatedTime = firstLocatedTime;
        this.lastLocatedTime = lastLocatedTime;
    }

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

}
