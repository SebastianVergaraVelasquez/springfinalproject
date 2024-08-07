package com.nasefa.springfinalproject.persistence.entities.order;

import java.util.List;

import com.nasefa.springfinalproject.persistence.entities.OrdersDetail;

public class OrderDTO {
    private Order order;
    private int clientId;
    private int statusId;
    private List<OrdersDetail> details;
    
    public List<OrdersDetail> getDetails() {
        return details;
    }
    public void setDetails(List<OrdersDetail> details) {
        this.details = details;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getStatusId() {
        return statusId;
    }
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    

}
