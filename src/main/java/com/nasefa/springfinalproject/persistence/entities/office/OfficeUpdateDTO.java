package com.nasefa.springfinalproject.persistence.entities.office;

public class OfficeUpdateDTO {

    private Office updatedOffice;
    private int cityId;
    
    public Office getUpdatedOffice() {
        return updatedOffice;
    }
    public void setUpdatedOffice(Office updatedOffice) {
        this.updatedOffice = updatedOffice;
    }
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    
}
