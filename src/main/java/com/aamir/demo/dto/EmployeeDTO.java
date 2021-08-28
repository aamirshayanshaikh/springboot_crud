package com.aamir.demo.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    private long id;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    @Email
    private String email;
}
