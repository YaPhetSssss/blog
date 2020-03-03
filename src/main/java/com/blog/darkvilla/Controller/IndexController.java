package com.blog.darkvilla.Controller;

import com.blog.darkvilla.DTO.PaginationDTO;
import com.blog.darkvilla.Map.UserMapper;
import com.blog.darkvilla.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PageService pageService;

    @GetMapping("/")
    public String index(@RequestParam(name = "currentPage", defaultValue = "1") String currentPage,
                        HttpServletRequest request,
                        Model model) {
        PaginationDTO pagination = pageService.getPaginationForIndex(currentPage);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
