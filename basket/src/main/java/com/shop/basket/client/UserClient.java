package com.shop.basket.client;

import com.shop.common.model.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "USER-SERVICE")
@RequestMapping("/api/users")
public interface UserClient {
    @GetMapping("/current")
    UserDto getCurrentUser();
}
