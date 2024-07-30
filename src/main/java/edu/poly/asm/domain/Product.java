package edu.poly.asm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(columnDefinition = "nvarchar(100) not null")
    private String name;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double unitPrice;
    @Column(length = 200)
    private String image;
    @Column(columnDefinition = "nvarchar(500) not null")
    private String description;
    @Column(nullable = false)
    private double discount;
    @Temporal(TemporalType.DATE)
    private Date enteredDate;
    @Column(nullable = false)
    private short status;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
