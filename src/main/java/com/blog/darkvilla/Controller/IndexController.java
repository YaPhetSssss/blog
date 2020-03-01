package com.blog.darkvilla.Controller;

import com.blog.darkvilla.Service.BlogService;
import com.blog.darkvilla.DTO.BlogDTO;
import com.blog.darkvilla.Map.UserMapper;
import com.blog.darkvilla.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogService blogService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) return "index";
        else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    User user = userMapper.verifyCookieByToken(cookie.getValue());
                    request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }
        List<BlogDTO> list =  blogService.getBlogList();
        model.addAttribute("list", list);
        return "index";
    }
}
