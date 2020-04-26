package model;


import java.util.List;

public class JobOffer {

    private String jobTitle;
    private String company;
    private String location;

    public JobOffer(String jobTitle, String company, String location) {
        this.jobTitle = jobTitle;
        this.company = company;
        this.location = location;
    }

    public static boolean compareListOfJobOfferObjects(List<JobOffer> firstList, List<JobOffer> secondList) {
        if(firstList.size() != secondList.size())
            return false;

        for (int y = 0; y < firstList.size(); y++) {
            int finalY = y;
            if(!firstList.stream().anyMatch(x -> x.getCompany().equals(secondList.get(finalY).getCompany())))
                return false;
            if(!firstList.stream().anyMatch(x -> x.getJobTitle().equals(secondList.get(finalY).getJobTitle())))
                return false;
            if(!firstList.stream().anyMatch(x -> x.getLocation().equals(secondList.get(finalY).getLocation())))
                return false;
        }
        return true;
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
