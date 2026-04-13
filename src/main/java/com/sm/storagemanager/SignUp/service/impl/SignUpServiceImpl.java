package com.sm.storagemanager.SignUp.service.impl;

import java.util.List;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sm.storagemanager.ResponseHttp.Response;
import com.sm.storagemanager.SignUp.dto.SignUpDto;
import com.sm.storagemanager.SignUp.service.SignUpService;
import com.sm.storagemanager.approle.constants.AppRole;
import com.sm.storagemanager.approle.dto.AppRoleDto;
import com.sm.storagemanager.approle.service.AppRoleService;
import com.sm.storagemanager.appuser.dto.AppUserDto;
import com.sm.storagemanager.appuser.service.AppUserService;
import com.sm.storagemanager.branch.dto.BranchDto;
import com.sm.storagemanager.branch.service.BranchService;
import com.sm.storagemanager.businessentity.dto.BusinessEntityDto;
import com.sm.storagemanager.businessentity.service.BusinessEntityService;
import com.sm.storagemanager.shared.enums.EntityStatus;
import com.sm.storagemanager.shared.enums.RoleStatus;
import com.sm.storagemanager.shared.enums.UserStatus; 
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private BusinessEntityService businessEntityService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private AppRoleService appRoleService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseEntity<Response<SignUpDto>> singUp(SignUpDto signUpDto) {

        Response<SignUpDto> response = new Response<SignUpDto>();

        try {
            BusinessEntityDto businessEntityDto = signUpDto.getBusiness();
            AppUserDto appUserDto = signUpDto.getAppUser();
            OffsetDateTime now = OffsetDateTime.now();

           
            businessEntityDto.setStatus(EntityStatus.ACTIVE);
            businessEntityDto.setCreatedAt(now);
            businessEntityDto.setUpdatedAt(now);
       

            boolean existName = businessEntityService.existsByName(businessEntityDto.getName());
            boolean existEmail = businessEntityService.existsByEmail(businessEntityDto.getEmail());
            boolean existUsername = appUserService.existsByUserName(appUserDto.getUsername());

            if (existName) {
                response.setMessage("El nombre del negocio o empresa ya está registrado.");
                return new ResponseEntity<Response<SignUpDto>>(response, HttpStatus.CONFLICT);
            }
            if (existEmail) {
                response.setMessage("El email del negocio o empresa ya está registrado.");
                return new ResponseEntity<Response<SignUpDto>>(response, HttpStatus.CONFLICT);

            }
            if (existUsername) {
                response.setMessage("El nombre de usuario ingresado ya está registrado.");
                return new ResponseEntity<Response<SignUpDto>>(response, HttpStatus.CONFLICT);

            }

            businessEntityDto = businessEntityService.create(businessEntityDto);

            BranchDto branchDto = new BranchDto();
            branchDto.setBusinessEntityId(businessEntityDto.getId());
            branchDto.setActive(true);
            branchDto.setName(businessEntityDto.getName());
            branchDto.setEmail(businessEntityDto.getEmail());
            branchDto.setCode("SUC-1");
            branchDto.setCreatedAt(now);
            branchDto.setUpdatedAt(now);

            branchDto = branchService.create(branchDto);

            AppRoleDto appRoleAdmin = new AppRoleDto();
            AppRoleDto appRoleUser = new AppRoleDto();

            appRoleAdmin.setBranchId(branchDto.getId());
            appRoleAdmin.setName(AppRole.ADMIN.getValue());
            appRoleAdmin.setDescription(AppRole.ADMIN.getValue());
            appRoleAdmin.setStatus(RoleStatus.ACTIVE);
            appRoleAdmin.setCreatedAt(now);
            appRoleAdmin.setUpdatedAt(now);

            appRoleUser.setBranchId(branchDto.getId());
            appRoleUser.setName(AppRole.USER.getValue());
            appRoleUser.setDescription(AppRole.USER.getValue());
            appRoleUser.setStatus(RoleStatus.ACTIVE);
            appRoleUser.setCreatedAt(now);
            appRoleUser.setUpdatedAt(now);

            List<AppRoleDto> roleList = List.of(appRoleAdmin, appRoleUser);

            roleList = appRoleService.createAll(roleList);

            AppRoleDto defaultRole = roleList
                    .stream()
                    .filter(role -> role.getName().equals(AppRole.ADMIN.getValue()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No se pudo crear el rol ADMIN."));

            appUserDto.setBranchId(branchDto.getId());
            appUserDto.setRoleId(defaultRole.getId());
            appUserDto.setEmail(businessEntityDto.getEmail());
            appUserDto.setPhone(businessEntityDto.getPhone());
            appUserDto.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
            appUserDto.setFirstName("");
            appUserDto.setLastName("");
            appUserDto.setStatus(UserStatus.ACTIVE);
            appUserDto.setCreatedAt(now);
            appUserDto.setUpdatedAt(now);

            appUserDto = appUserService.create(appUserDto);

            signUpDto.setBusiness(businessEntityDto);
            signUpDto.setAppUser(appUserDto);

            response.setData(signUpDto);
            response.setMessage("Se registró de manera correcta");
            return new ResponseEntity<Response<SignUpDto>>(response, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<Response<SignUpDto>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
