package com.sm.storagemanager.SignUp.dto;

import com.sm.storagemanager.appuser.dto.AppUserDto;
import com.sm.storagemanager.businessentity.dto.BusinessEntityDto;
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
public class SignUpDto {

    private BusinessEntityDto business;
    private AppUserDto appUser;
}
