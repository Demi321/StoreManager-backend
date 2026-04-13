package com.sm.storagemanager.appuser.service;

import com.sm.storagemanager.appuser.dto.AppUserDto;
import com.sm.storagemanager.shared.crud.service.CrudService;

public interface AppUserService extends CrudService<AppUserDto, Long> {
    boolean existsByUserName(String userName);
}
