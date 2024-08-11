package com.nasefa.springfinalproject.persistence.entities.order;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.nasefa.springfinalproject.persistence.entities.OrdersDetail;
import com.nasefa.springfinalproject.persistence.entities.Status;
import com.nasefa.springfinalproject.persistence.entities.client.Client;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @JoinColumn(name = "order_code")
    private String orderCode;

    @JoinColumn(name = "order_date")
    private Timestamp orderDate;

    @JoinColumn(name = "waited_date")
    private Date waitedDate;

    @JoinColumn(name = "deliver_date")
    private Date deliverDate;

    private String commentary;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrdersDetail> ordersDetails;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public Order(String orderCode, Timestamp orderDate, Date waitedDate, Date deliverDate, String commentary,
            List<OrdersDetail> ordersDetails, Status status, Client client) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.waitedDate = waitedDate;
        this.deliverDate = deliverDate;
        this.commentary = commentary;
        this.ordersDetails = ordersDetails;
        this.status = status;
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order() {
        ordersDetails = new ArrayList<>();
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Date getWaitedDate() {
        return waitedDate;
    }

    public void setWaitedDate(Date waitedDate) {
        this.waitedDate = waitedDate;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public List<OrdersDetail> getOrdersDetails() {
        return ordersDetails;
    }

    public void setOrdersDetails(List<OrdersDetail> ordersDetails) {
        this.ordersDetails = ordersDetails;
    }

    


    


}
