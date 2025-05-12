package com.medspace.infrastructure.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EditUserDTO {
    @Size(max = 30, message = "Name must not exceed 30 characters")
    private String fullName;

    @Email
    private String email;

    private String phoneNumber;

    private String pfpPath;

    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

    private Long tenantSpecialtyId;

    private String tenantLicensePath;
}
