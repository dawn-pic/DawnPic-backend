package com.hanyujie.dawnpic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class CurrentUserResponse {
    private String username;
    private List<String> roles;
}
