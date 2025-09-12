package org.accountinglib.dto;

import java.util.List;

/**
 * Order data transfer object for API usage
 */
public class OrderDTO {

    public OrderDTO() {

    }

    private Long id;

    private Long customerId;

    private List<OrderLineDTO> orderLines;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderLineDTO> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineDTO> orderLines) {
        this.orderLines = orderLines;
    }



}
