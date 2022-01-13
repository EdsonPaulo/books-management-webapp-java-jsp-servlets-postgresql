package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class AddressModel {

    private int addressId, houseNum;
    private String street, district;
    private LocalDateTime creationDate;

    public AddressModel() {
    }

    public AddressModel(int addressId, String district, int houseNum, String street, LocalDateTime creationDate) {
        this.addressId = addressId;
        this.district = district;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
