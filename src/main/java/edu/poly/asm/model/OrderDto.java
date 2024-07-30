package edu.poly.asm.model;

import edu.poly.asm.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {
    private Long orderId;

    private Date orderDate;

    private double amount;

    private short status;

    private Customer customer;

    private Long customerId;

    private Boolean isEdit = false;
}
