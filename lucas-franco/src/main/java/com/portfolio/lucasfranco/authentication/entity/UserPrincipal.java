package com.portfolio.lucasfranco.authentication.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class UserPrincipal implements UserDetails {

    private String name;
    private String nameUser;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String name, String nameUser, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal build(UserEntity userEntity){
        List<GrantedAuthority> authorities =
                userEntity.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getRoles().name())).collect(Collectors.toList());
        return new UserPrincipal(userEntity.getName(), userEntity.getNameUser(), userEntity.getEmail(), userEntity.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String getUsername() {
        return nameUser;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
