package com.hanyujie.dawnpic.web;

import com.hanyujie.dawnpic.entity.DatabaseConfigDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired
    private DataSource dataSource;

    @PostMapping("/database")
    public ResponseEntity<String> updateDatabaseConfig(@RequestBody DatabaseConfigDTO newConfig) {
        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) dataSource;

        driverManagerDataSource.setUrl(newConfig.getUrl());
        driverManagerDataSource.setUsername(newConfig.getUsername());
        driverManagerDataSource.setPassword(newConfig.getPassword());
        driverManagerDataSource.setDriverClassName(newConfig.getDriverClassName());
        return ResponseEntity.ok("Database configuration updated successfully");
    }
}
