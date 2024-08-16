package com.hanyujie.dawnpic;

import com.hanyujie.dawnpic.mapper.ImageMapper;
import com.hanyujie.dawnpic.mapper.UserMapper;
import com.hanyujie.dawnpic.service.ImageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DawnPicApplicationTests {
    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
    }

}
