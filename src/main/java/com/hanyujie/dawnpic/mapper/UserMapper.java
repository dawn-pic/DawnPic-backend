package com.hanyujie.dawnpic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hanyujie.dawnpic.entity.User;
import com.hanyujie.dawnpic.entity.UserImage;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM image left join user on image.user_id = user.id WHERE user.username = #{username}")
    List<UserImage> selectUserImageByUsername(String username);
}
