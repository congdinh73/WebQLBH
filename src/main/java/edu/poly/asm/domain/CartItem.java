package edu.poly.asm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem implements Serializable {
    private Long productId;
    private String name;
    private int quantity;
    private double unitPrice;
}