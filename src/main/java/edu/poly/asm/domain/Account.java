package edu.poly.asm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
    @Id
    @Column(length = 30)
    private String username;
    @Column(length = 60, nullable = false)
    private String password;
}
