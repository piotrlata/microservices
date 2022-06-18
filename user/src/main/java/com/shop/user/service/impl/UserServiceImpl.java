package com.shop.user.service.impl;

import com.shop.common.model.dto.EmailDto;
import com.shop.user.client.NotificationClient;
import com.shop.user.model.dao.Address;
import com.shop.user.model.dao.User;
import com.shop.user.model.dto.ConfirmRegistrationDto;
import com.shop.user.repositiory.AddressRepository;
import com.shop.user.repositiory.RoleRepository;
import com.shop.user.repositiory.UserRepository;
import com.shop.security.SecurityUtils;
import com.shop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;
    private final NotificationClient notificationClient;

    @Override
    public User save(User user, Address address) {
        roleRepository.findByName("ROLE_USER").ifPresent(role -> user.setRoles(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAddress(address);
        user.setActivatedCode(UUID.randomUUID().toString());
        addressRepository.save(address);
        userRepository.save(user);
        notificationClient.sendEmail(new EmailDto<>(user.getEmail(), "confirmRegistration",
                new ConfirmRegistrationDto(user.getFirstName(), user.getLastName(),
                        "http://localhost:8182/api/users/confirm?activatedCode=" + user.getActivatedCode())));
        log.info("sending email");
        return user;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User update(User user, Address address, Long id) {
        var userDb = findById(id);
        userDb.setFirstName(user.getFirstName());
        userDb.setLastName(user.getLastName());
        userDb.setEmail(user.getEmail());
        userDb.setAddress(address);
        return userDb;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("user not logged"));
    }

    @Override
    @Transactional
    public void authorizeRegistration(String activatedCode) {
        Optional<User> optionalUser = userRepository.findByActivatedCode(activatedCode);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException();
        }
        User user = optionalUser.get();
        user.setActivatedCode(null);
    }
}
