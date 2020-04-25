package enums;

public enum MyJobSearchOptionEnum {
    BROWSE_JOBS("Browse Jobs"),
    RESUMES("Resumes"),
    COVER_LETTERS("Cover Letters"),
    DOCUMENTS("Documents"),
    APPLIES_JOBS("Applies Jobs"),
    SAVED_JOBS("Saved Jobs"),
    JOB_ALERTS("Job Alerts");

    private String text;

    MyJobSearchOptionEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
