package com.nasefa.springfinalproject.persistence.entities.office;

public class OfficeDTO {
    private Office office;
    private int idCity;

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }
}
