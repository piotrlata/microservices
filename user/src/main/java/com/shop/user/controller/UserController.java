package com.shop.user.controller;

import com.shop.common.model.dto.UserDto;
import com.shop.user.mapper.AddressMapper;
import com.shop.user.mapper.UserMapper;
import com.shop.user.service.UserService;
import com.shop.common.validator.group.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    @PostMapping
    @Validated(CreateUser.class)
    @PreAuthorize("isAnonymous()")
    public UserDto saveUser(@RequestBody @Valid UserDto user) {
        return userMapper.daoToDto(userService.save(userMapper.dtoToDao(user), addressMapper.dtoToDao(user.getAddressDto())));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto findUserById(@PathVariable Long id) {
        return userMapper.daoToDto(userService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (hasRole('ADMIN') || @securityService.hasAccessToUser(#id))")
    public UserDto updateUser(@RequestBody UserDto user, @PathVariable Long id) {
        return userMapper.daoToDto(userService.update(userMapper.dtoToDao(user), addressMapper.dtoToDao(user.getAddressDto()), id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> userPage(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size)).map(userMapper::daoToDto);
    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        return userMapper.daoToDto(userService.getCurrentUser());
    }

    @GetMapping("/confirm")
    public void authorizeRegistration(@RequestParam String activatedCode) {
        userService.authorizeRegistration(activatedCode);
    }
}
