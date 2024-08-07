package com.nasefa.springfinalproject.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nasefa.springfinalproject.persistence.entities.order.Order;
import com.nasefa.springfinalproject.persistence.entities.product.Product;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders_details")
public class OrdersDetail {

    @EmbeddedId
    private OrdersDetailId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderCode")
    @JsonIgnore
    @JoinColumn(name = "order_code")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productCode")
    @JsonIgnore
    @JoinColumn(name = "product_code")
    private Product product;

    private int quantity;
    private double price;
    
    public OrdersDetail() {
    }

    public OrdersDetail(Order order, Product product, int quantity, double price) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.id = new OrdersDetailId(order.getOrderCode(), product.getProductCode());
    }

    public OrdersDetailId getId() {
        return id;
    }

    public void setId(OrdersDetailId id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
