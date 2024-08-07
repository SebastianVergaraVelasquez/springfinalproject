package com.nasefa.springfinalproject.persistence.entities.client;


public class ClientDTO {
    private Client client;
    private String AddressDesc;
    private int cityId;
    private int salesRepId;
    
    public Client getClient() {
        return client;
    }
    public String getAddressDesc() {
        return AddressDesc;
    }
    public void setAddressDesc(String addressDesc) {
        AddressDesc = addressDesc;
    }
    public int getCityId() {
        return cityId;
    }
    public void setCityId(int cityId) {
        this.cityId = cityId;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public int getSalesRepId() {
        return salesRepId;
    }
    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
    }
}
