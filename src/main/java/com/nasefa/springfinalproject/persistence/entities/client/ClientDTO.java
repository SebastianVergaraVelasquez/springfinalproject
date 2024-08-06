package com.nasefa.springfinalproject.persistence.entities.client;

public class ClientDTO {
    private Client client;
    private int salesRepId;
    
    public Client getClient() {
        return client;
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
