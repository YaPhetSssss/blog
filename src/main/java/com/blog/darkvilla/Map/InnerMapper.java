package com.blog.darkvilla.Map;

import com.blog.darkvilla.DTO.BlogDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InnerMapper {
    @Select("select p.title, p.description, p.gmt_create,p.gmt_modified, p.creator, p.comment_count, p.view_count, p.like_count, p.tags, q.avatar_url from blog as p, users as q where p.creator=q.account_id")
    List<BlogDTO> findBlogWithUser();
}
