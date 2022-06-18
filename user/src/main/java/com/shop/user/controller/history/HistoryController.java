package com.shop.user.controller.history;

import com.shop.common.model.dto.UserDto;
import com.shop.user.mapper.UserRevisionMapper;
import com.shop.user.repositiory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history/user")
public class HistoryController {
    private final UserRepository userRepository;
    private final UserRevisionMapper userRevisionMapper;

    @GetMapping("/{id}")
    public Page<UserDto> getUserHistory(@PathVariable Long id, @RequestParam int page, @RequestParam int size) {
        return userRepository.findRevisions(id, PageRequest.of(page, size)).map(userRevisionMapper::toUserDto);
    }
}
