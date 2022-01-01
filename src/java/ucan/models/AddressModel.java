package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class AddressModel {

    private int addressId, districtId, houseNum;
    private String street;
    private LocalDateTime creationDate;

    public AddressModel() {
    }

    public AddressModel(int addressId, int districtId, int houseNum, String street, LocalDateTime creationDate) {
        this.addressId = addressId;
        this.districtId = districtId;
        this.houseNum = houseNum;
        this.street = street;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
 
 

}
