package enums;

import pages.SavedJobsPage;

public enum PageWaitElementEnum {

    CREATE_ACCOUNT_PAGE("//*[@id='c_elem_0']"),
    DASHBOARD_PAGE("//*[contains(text(),'My Checklist')]"),
    SEARCH_PAGE("//div[@class='mux-search-results']"),
    SAVED_JOBS_PAGE_FULL("//div[contains(@class,'main')]//div[@class='list-body']");

    private String xPathElement;

    PageWaitElementEnum(String xPathElement) {
        this.xPathElement = xPathElement;
    }

    public String getXPathElement() {
        return xPathElement;
    }
}
