package com.blog.darkvilla.Controller;

import com.blog.darkvilla.DTO.AccesstokenDTO;
import com.blog.darkvilla.DTO.GithubUser;
import com.blog.darkvilla.Map.UserMapper;
import com.blog.darkvilla.Model.User;
import com.blog.darkvilla.Provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;
    @Value("${github.client_id}")
    private String client_id;
    @Value("${github.client_serect}")
    private String client_serect;
    @Value("${github.redict_uri}")
    private String redict_uri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code")String code,
                           @RequestParam(name="state")String state,
                           HttpServletRequest request){

        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setClient_id(client_id);
        accesstokenDTO.setClient_serect(client_serect);
        accesstokenDTO.setCode(code);
        accesstokenDTO.setRedict_uri(redict_uri);
        accesstokenDTO.setState(state);
        String token = githubProvider.getAccesstoken(accesstokenDTO);
        GithubUser githubUser = githubProvider.githubUser(token);
        if(githubUser != null){
            //登陆成功
            request.getSession().setAttribute("user", githubUser);
            User user = new User();
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);

            return "redirect:/";
        }
        else{
            //登陆失败
        }
        return "redirect:/";
    }

}
