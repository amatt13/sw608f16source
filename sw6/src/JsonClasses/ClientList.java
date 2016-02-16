package JsonClasses;

import java.util.List;

/**
 * Created by Simon on 16/02/2016.
 */
public class ClientList {

    private int totalPages;
    private int currentPage;
    private int pageSize;

    public List<Client> clientList;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
