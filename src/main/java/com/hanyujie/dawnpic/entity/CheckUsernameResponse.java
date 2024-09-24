package com.hanyujie.dawnpic.entity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckUsernameResponse {
    private Boolean isExist;
}
