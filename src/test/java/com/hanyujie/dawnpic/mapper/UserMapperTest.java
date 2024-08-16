package com.hanyujie.dawnpic.mapper;

import com.hanyujie.dawnpic.entity.UserImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    void selectUserImageByUserId() {
        List<UserImage> userImages = userMapper.selectUserImageByUsername("anonymousUser");
        for (UserImage userImage : userImages) {
            System.out.println(userImage);
        }
    }
}