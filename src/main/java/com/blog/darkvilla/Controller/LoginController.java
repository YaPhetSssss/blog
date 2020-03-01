package com.blog.darkvilla.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @RequestMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String url = "https://github.com/login/oauth/authorize";
        String param = "client_id=" + client_id +
                "&redirect_uri=" + redirect_uri +
                "&scope=user" +
                "&state=1" ;
        response.sendRedirect(url + "?" + param);
    }
}
