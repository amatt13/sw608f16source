
package AllClient;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Locations {

    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("entries")
    @Expose
    private List<Entry> entries = new ArrayList<Entry>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Locations() {
    }

    /**
     * 
     * @param pageSize
     * @param entries
     * @param currentPage
     * @param totalPages
     */
    public Locations(Integer totalPages, Integer currentPage, Integer pageSize, List<Entry> entries) {
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.entries = entries;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The totalPages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The currentPage
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * @return
     *     The entries
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * 
     * @param entries
     *     The entries
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
