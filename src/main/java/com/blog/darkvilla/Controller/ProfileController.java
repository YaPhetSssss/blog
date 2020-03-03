package com.blog.darkvilla.Controller;

import com.blog.darkvilla.DTO.PaginationDTO;
import com.blog.darkvilla.Model.User;
import com.blog.darkvilla.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private PageService pageService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action")String action,
                          @RequestParam(name = "currentPage", defaultValue = "1")String page,
                          HttpServletRequest request,
                          Model model){
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("myBlog".equals(action)){
            model.addAttribute("section", "myBlog");
            model.addAttribute("sectionName", "我的博客");
        }
        if("reply".equals(action)){
            model.addAttribute("section", "reply");
            model.addAttribute("sectionName", "最新回复");
        }
        PaginationDTO paginationDTO = pageService.getPaginationForUser(String.valueOf(user.getAccountId()), page);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
