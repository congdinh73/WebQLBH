package edu.poly.asm.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginDto {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    private Boolean rememberMe = false;
}
