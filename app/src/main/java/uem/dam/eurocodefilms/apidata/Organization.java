
package uem.dam.eurocodefilms.apidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Organization {

    @SerializedName("organization-desc")
    @Expose
    private String organizationDesc;
    @SerializedName("accesibility")
    @Expose
    private String accesibility;
    @SerializedName("schedule")
    @Expose
    private String schedule;
    @SerializedName("services")
    @Expose
    private String services;
    @SerializedName("organization-name")
    @Expose
    private String organizationName;

    public String getOrganizationDesc() {
        return organizationDesc;
    }

    public void setOrganizationDesc(String organizationDesc) {
        this.organizationDesc = organizationDesc;
    }

    public String getAccesibility() {
        return accesibility;
    }

    public void setAccesibility(String accesibility) {
        this.accesibility = accesibility;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

}
