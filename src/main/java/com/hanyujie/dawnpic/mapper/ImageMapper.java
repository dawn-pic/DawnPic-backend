package com.hanyujie.dawnpic.mapper;

import com.hanyujie.dawnpic.entity.Image;
import com.hanyujie.dawnpic.mapper.typeHandler.UuidTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.UUID;


@Mapper
public interface ImageMapper {
    @Insert("INSERT INTO images (image_uuid, name, extension, upload_date, size_width, size_height) VALUES (#{imageUuid} ,#{name}, #{extension}, #{uploadDate}, #{sizeWidth}, #{sizeHeight})")
    void insertPic(Image image);

    @Select("SELECT * FROM images WHERE image_uuid = #{imageUuid}")
//    @Results({
//            @Result(property = "imageUuid", column = "image_uuid", jdbcType = JdbcType.BINARY, typeHandler = UuidTypeHandler.class),
//    })
    Image getPic(@Param("imageUuid") UUID imageUuid);
}
