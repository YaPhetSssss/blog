package com.blog.darkvilla.Controller;

import com.blog.darkvilla.DTO.AccesstokenDTO;
import com.blog.darkvilla.DTO.GithubUser;
import com.blog.darkvilla.Provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id("009145dd7fe604075289");
        accesstokenDTO.setClient_serect("dcdad05c5387ac69af8a3b5261447dee50d745fd");
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedict_uri("http://localhost:8080/callback");
        accesstokenDTO.setState(state);
        String token = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser githubUser = githubProvider.githubUser(token);
        System.out.println(githubUser.getId());
        return "index";
    }

}
