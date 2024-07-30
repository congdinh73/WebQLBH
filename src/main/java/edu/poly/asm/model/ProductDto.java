package edu.poly.asm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    private Long productId;
    private String name;
    private int quantity;
    private double unitPrice;
    private String image;

    private MultipartFile imageFile;

    private String description;
    private Double discount;
    private Date enteredDate;
    private short status;
    private Long categoryId;

    private Boolean isEdit = false;


}
