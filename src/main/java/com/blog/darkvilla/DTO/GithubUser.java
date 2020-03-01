package com.blog.darkvilla.DTO;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;
}
