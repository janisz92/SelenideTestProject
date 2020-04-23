package enums;

public enum UrlEnum {

    HOME_PAGE("https://www.monsterworksdemo.com/home/"),
    CREATE_ACCOUNT_PAGE("https://www.monsterworksdemo.com/account/account-lite "),
    DASHBOARD_PAGE("https://www.monsterworksdemo.com/dashboard/");

    private String ulr;

    UrlEnum(String url) {
        this.ulr = url;
    }

    public String getUrl() {
        return ulr;
    }
}
