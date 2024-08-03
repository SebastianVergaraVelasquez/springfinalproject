package com.nasefa.springfinalproject.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrdersDetailId {
    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "product_code")
    private String productCode;

    public OrdersDetailId() {}

    public OrdersDetailId(String orderCode, String productCode) {
        this.orderCode = orderCode;
        this.productCode = productCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
