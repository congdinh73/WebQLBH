package edu.poly.asm.model;

import edu.poly.asm.domain.Order;
import edu.poly.asm.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDto {
    private Long orderDetailId;

    private int quantity;

    private double unitPrice;

    private Product product;

    private Long productId;

    private Order order;

    private Long orderId;

    private Boolean isEdit = false;
}
