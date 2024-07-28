package com.hanyujie.dawnpic.mapper;

import com.hanyujie.dawnpic.entity.Image;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Mapper
public interface ImageMapper {
    @Insert("INSERT INTO images (name, extension, upload_date, size_width, size_height) VALUES (#{name}, #{extension}, #{uploadDate}, #{sizeWidth}, #{sizeHeight})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertPic(Image image);

    @Select("SELECT * FROM images WHERE id = #{id}")
    Image getPic(int id);
}
