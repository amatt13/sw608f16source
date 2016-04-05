
package AllClient;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Image {

    @SerializedName("imageName")
    @Expose
    private String imageName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Image() {
    }

    /**
     * 
     * @param imageName
     */
    public Image(String imageName) {
        this.imageName = imageName;
    }

    /**
     * 
     * @return
     *     The imageName
     */
    public String getImageName() {
        return imageName;
    }

    /**
     * 
     * @param imageName
     *     The imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

}
