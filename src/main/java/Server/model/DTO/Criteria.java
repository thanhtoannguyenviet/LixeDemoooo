package Server.model.DTO;

public class Criteria {

    private boolean ascending;
    private int itemPerPage;
    private int keyword;
    private int currentPage;

    public Criteria(boolean ascending, int itemPerPage, int keyword,int currentPage) {
        this.ascending = ascending;
        this.itemPerPage = itemPerPage;
        this.keyword = keyword;
        this.currentPage = currentPage;
    }
    public Criteria() {
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getKeyword() {
        return keyword;
    }

    public void setKeyword(int keyword) {
        this.keyword = keyword;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
