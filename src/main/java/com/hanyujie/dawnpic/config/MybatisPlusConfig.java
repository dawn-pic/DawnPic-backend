package com.hanyujie.dawnpic.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.hanyujie.dawnpic.mapper")
public class MybatisPlusConfig {
}
