package com.hanyujie.dawnpic.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.hanyujie.dawnpic.entity.User;
import com.hanyujie.dawnpic.entity.UserImage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.UUID;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT u.username, i.image_uuid, i.name, i.extension, i.description, i.alt_info, i.upload_date, i.size_width, i.size_height FROM image i left join user u on i.user_id = u.id ${ew.customSqlSegment}")
    List<UserImage> selectUserImageByUsername(IPage<UserImage> page, @Param(Constants.WRAPPER) QueryWrapper<UserImage> wrapper);

    @Select("SELECT COUNT(1) FROM image i left join user u on i.user_id = u.id WHERE username = #{username}")
    int selectUserImageCount(String username);

    @Select("SELECT u.username FROM image i left join user u on i.user_id = u.id WHERE image_uuid=#{imageUuid}")
    String selectOwnerNameByImageUuid(UUID imageUuid);
}
