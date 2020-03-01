package com.blog.darkvilla.Service;

import com.blog.darkvilla.DTO.BlogDTO;
import com.blog.darkvilla.Map.InnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("blogService")
public class BlogService {
    @Autowired
    private InnerMapper innerMapper;

    private List<BlogDTO> list;

    public List<BlogDTO> getBlogList(){
        list = innerMapper.findBlogWithUser();
        return list;
    }
}
