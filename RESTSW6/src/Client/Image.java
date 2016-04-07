
package Client;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Image {

    @SerializedName("imageName")
    @Expose
    private String imageName;

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

    public Image withImageName(String imageName) {
        this.imageName = imageName;
        return this;
    }

}
