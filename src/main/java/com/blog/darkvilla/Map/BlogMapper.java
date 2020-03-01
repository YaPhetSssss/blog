package com.blog.darkvilla.Map;

import com.blog.darkvilla.Model.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper {
    @Insert("insert into blog (title, description, gmt_create, gmt_modified, creator, tags) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tags})")
    void createBlog(Blog blog);
}
