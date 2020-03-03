package com.blog.darkvilla.Map;

import com.blog.darkvilla.DTO.BlogDTO;
import com.blog.darkvilla.Model.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Insert("insert into blog (title, description, gmt_create, gmt_modified, creator, tags) values (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tags})")
    void createBlog(Blog blog);
    @Select("select count(1) from blog")
    int getCount();
    @Select("select p.title, p.description, p.gmt_create,p.gmt_modified, p.creator, p.comment_count, p.view_count, p.like_count, p.tags, q.avatar_url from blog as p, users as q where p.creator=q.account_id limit #{start}, #{offset}")
    List<BlogDTO> findBlogWithUser(@Param("offset") Integer offset, @Param("start") Integer start);
    @Select("select p.title, p.description, p.gmt_create,p.gmt_modified, p.creator, p.comment_count, p.view_count, p.like_count, p.tags, q.avatar_url from blog as p, users as q where p.creator=q.account_id and p.creator=#{creator} limit #{start}, #{offset}")
    List<BlogDTO> findBlogByUser(@Param("creator") String creator,@Param("offset") Integer offset, @Param("start") Integer start);
    @Select("select count(1) from blog where creator=#{useId}")
    int getCountForUser(String userId);
}
