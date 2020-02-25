package com.blog.darkvilla.Provider;

import com.alibaba.fastjson.JSON;
import com.blog.darkvilla.DTO.AccesstokenDTO;
import com.blog.darkvilla.DTO.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccesstoken(AccesstokenDTO accesstokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accesstokenDTO));
        okhttp3.Request request = request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String s = response.body().string();
            System.out.println(s);
            String token = s.substring(13).split("&")[0];
            System.out.println(token);
            return token;
        } catch (IOException e) {
        }
        return null;
    }
    public GithubUser githubUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + access_token)
                .build();
        try  {
            Response response = client.newCall(request).execute();
            String user = response.body().string();
            GithubUser githubUser = JSON.parseObject(user, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return null;
    }
}
