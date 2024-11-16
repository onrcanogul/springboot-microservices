package com.bankapp.accounts.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CustomerDto {
    @NotEmpty
    @Size(min = 5, max = 30)
    private String name;
    @NotEmpty
    @Email
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;
}
