
package uem.dam.eurocodefilms.apidata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("area")
    @Expose
    private Area area;
    @SerializedName("locality")
    @Expose
    private String locality;
    @SerializedName("postal-code")
    @Expose
    private String postalCode;
    @SerializedName("street-address")
    @Expose
    private String streetAddress;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

}
