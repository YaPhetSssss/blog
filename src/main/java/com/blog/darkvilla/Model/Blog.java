package com.blog.darkvilla.Model;

import lombok.Data;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private String creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tags;
}
