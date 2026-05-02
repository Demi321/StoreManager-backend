package com.sm.storagemanager.security.service.impl;

import com.sm.storagemanager.appuser.entity.AppUser;
import com.sm.storagemanager.appuser.repository.AppUserRepository;
import com.sm.storagemanager.security.constants.AuthMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public CustomUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
        AppUser appUser = appUserRepository.findByUsernameOrEmail(username,username)
                                            .orElseThrow(() -> new UsernameNotFoundException(
                                            AuthMessage.USER_NOT_FOUND.getMessage().concat(username)));

        return User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPasswordHash())
                .roles(appUser.getRole().getName())
                .build();
    }
}
