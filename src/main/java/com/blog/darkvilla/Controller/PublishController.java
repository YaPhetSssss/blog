package com.blog.darkvilla.Controller;

import com.blog.darkvilla.Map.BlogMapper;
import com.blog.darkvilla.Map.UserMapper;
import com.blog.darkvilla.Model.Blog;
import com.blog.darkvilla.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(name = "title",required=false) String title,
            @RequestParam(name = "description",required=false)  String description,
            @RequestParam(name = "tags",required=false) String tags,
            HttpServletRequest request,
            Model model
    ){
        if(title == null || title.equals("")){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if(description == null || description.equals("")){
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tags", tags);
        Cookie[] cookies = request.getCookies();
        User user = null;
        if (cookies == null || cookies.length == 0) model.addAttribute("error", "用户未登录");
        else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    user = userMapper.verifyCookieByToken(cookie.getValue());
                    model.addAttribute("user", user);
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error", "用户未登录");
        }
        else{
            Blog blog = new Blog();
            blog.setTitle(title);
            blog.setDescription(description);
            blog.setGmtCreate(System.currentTimeMillis());
            blog.setGmtModified(blog.getGmtCreate());
            blog.setCreator(String.valueOf(user.getAccountId()));
            blog.setTags(tags);
            blogMapper.createBlog(blog);
        }

        return "publish";
    }
}
