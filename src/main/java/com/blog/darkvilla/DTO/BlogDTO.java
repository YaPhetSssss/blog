package com.blog.darkvilla.DTO;

import com.blog.darkvilla.Model.Blog;
import lombok.Data;

@Data
public class BlogDTO extends Blog{
    private String avatarUrl;
}
