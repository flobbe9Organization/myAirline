package com.example.myAirline.enums;

import static com.example.myAirline.enums.AppUserPermission.ADMIN_READ;
import static com.example.myAirline.enums.AppUserPermission.ADMIN_WRITE;
import static com.example.myAirline.enums.AppUserPermission.USER_READ;
import static com.example.myAirline.enums.AppUserPermission.USER_WRITE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;


/**
 * Defines the ROLE of an AppUser needed for a GrantedAuthority. Each role has multple permissions.
 * 
 * @see AppUserPermission
 * @since 1.0
 * @author Florin Schikarski
 */
@AllArgsConstructor
public enum AppUserRole {
    USER(new HashSet<>(Arrays.asList(USER_READ, USER_WRITE))),
    ADMIN(new HashSet<>(Arrays.asList(USER_READ, USER_WRITE, ADMIN_READ, ADMIN_WRITE)));


    private Set<AppUserPermission> permissions;


    /**
     * Converts every AppUserPermission and the AppUserRole into a SimpleGrantedAuthority. Adds them into a Set.
     * 
     * @return HashSet with all permissions and roles as SimpleGrantedAuthoritiy.
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {

        // converting and adding every AppUserPermission
        Set<SimpleGrantedAuthority> grantedAuthorities = permissions.stream()
                                                                    .map(permission -> new SimpleGrantedAuthority(permission.name()))
                                                                    .collect(Collectors.toSet());
        
        // adding the AppUserRole
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return grantedAuthorities;
    }
}