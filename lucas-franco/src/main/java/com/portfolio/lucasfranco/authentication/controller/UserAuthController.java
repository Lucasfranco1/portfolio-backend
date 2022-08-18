package com.portfolio.lucasfranco.authentication.controller;

import com.portfolio.lucasfranco.authentication.dto.AuthRequestDTO;
import com.portfolio.lucasfranco.authentication.dto.JwtDTO;
import com.portfolio.lucasfranco.authentication.dto.UserDTO;
import com.portfolio.lucasfranco.authentication.entity.RolEntity;
import com.portfolio.lucasfranco.authentication.entity.UserEntity;
import com.portfolio.lucasfranco.authentication.service.*;
import com.portfolio.lucasfranco.enums.Roles;
import com.portfolio.lucasfranco.exceptions.ParamBadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static com.portfolio.lucasfranco.util.constants.ConstantsUtil.CROSS_ORIGIN;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = CROSS_ORIGIN)
public class UserAuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTO userDTO){
        UserEntity user =
                new UserEntity(userDTO.getName(), userDTO.getNameUser(), userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()));
        Set<RolEntity> roles = new HashSet<>();
        roles.add(roleService.getByRole(Roles.ROLE_USER).get());
        if(userDTO.getRoles().contains("admin"))
            roles.add(roleService.getByRole(Roles.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<UserEntity>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO>login(@RequestBody @Valid AuthRequestDTO authRequest){
        if(authRequest.getNameUser() == null || authRequest.getNameUser().isEmpty()){
            throw new ParamBadRequest("El campo nombre de usuario está vacío");
        }
        if(authRequest.getPassword() == null || authRequest.getPassword().isEmpty()){
            throw new ParamBadRequest("El campo password está vacío");
        }
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getNameUser(), authRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDTO jwtDto = new JwtDTO(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
    @PostMapping("/refresh")
    public ResponseEntity<JwtDTO> refresh(@RequestBody JwtDTO jwtDTO) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDTO);
        JwtDTO jwt = new JwtDTO((token));
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
