
package Client;

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

    public Statistics withCurrentServerTime(String currentServerTime) {
        this.currentServerTime = currentServerTime;
        return this;
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

    public Statistics withFirstLocatedTime(String firstLocatedTime) {
        this.firstLocatedTime = firstLocatedTime;
        return this;
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

    public Statistics withLastLocatedTime(String lastLocatedTime) {
        this.lastLocatedTime = lastLocatedTime;
        return this;
    }

}
