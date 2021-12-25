package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class AddressModel {

    private int addressId, districtId, houseNum;
    private String street, createdAt;

    public AddressModel(int addressId, int districtId, int houseNum, String street, String createdAt) {
        this.addressId = addressId;
        this.districtId = districtId;
        this.houseNum = houseNum;
        this.street = street;
        this.createdAt = createdAt;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
 
 

}
