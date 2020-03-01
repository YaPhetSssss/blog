package com.blog.darkvilla.Map;

import com.blog.darkvilla.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into users (account_id, name, token, gmt_create, gmt_modified, avatar_url) values (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);
    @Select("select * from users where token = #{token}")
    User verifyCookieByToken(@Param("token") String token);
    @Select("select * from users where account_id = #{accountId}")
    User findById(@Param("accountId") String accountId);
}
