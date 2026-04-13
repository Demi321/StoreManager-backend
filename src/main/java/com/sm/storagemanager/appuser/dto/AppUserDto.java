package com.sm.storagemanager.appuser.dto;

import com.sm.storagemanager.shared.enums.UserStatus;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUserDto {

    private Long id;
    private Long branchId;
    private Long roleId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String email;
    private String password;
    private String phone;
    private UserStatus status;
    private OffsetDateTime lastLoginAt;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
