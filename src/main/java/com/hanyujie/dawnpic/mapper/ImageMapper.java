package com.hanyujie.dawnpic.mapper;

import com.hanyujie.dawnpic.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;


@Mapper
public interface ImageMapper {
    @Insert("Insert into images (name) values (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPic(Image image);
}
