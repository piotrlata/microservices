package com.shop.user.service;

import com.shop.user.model.dao.Address;
import com.shop.user.model.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User save(User user, Address address);

    void delete(Long id);

    User update(User user, Address address, Long id);

    User findById(Long id);

    Page<User> getPage(Pageable pageable);

    User getCurrentUser();

    void authorizeRegistration(String activatedCode);
}
