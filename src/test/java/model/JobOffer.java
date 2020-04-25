package model;

public class JobOffer {

    private String jobTitle;
    private String company;
    private String location;

    public JobOffer(String jobTitle, String company, String location) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
    }

    public String getJobTitle(){
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany(){
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
