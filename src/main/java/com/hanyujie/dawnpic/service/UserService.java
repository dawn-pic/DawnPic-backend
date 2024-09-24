package com.hanyujie.dawnpic.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanyujie.dawnpic.entity.User;
import com.hanyujie.dawnpic.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    public User getUserFromUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        return userMapper.selectOne(queryWrapper);
    }

    public boolean isUsernameExist(String username) {
        return userMapper.selectCount(new QueryWrapper<User>().eq("username", username)) > 0;
    }
}
