package com.itmayiedu.Controller;

import com.itmayiedu.Util.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Created by YJJ on 2019/11/3.
 */
@RestController
public class LoginContoller {
    @PostMapping("login")
    public Object login(String username, String password,HttpServletResponse response) throws Exception {
        //默认登陆正确，实际项目采用数据库进行验证
        HashMap claims=new HashMap<String,Integer>();
        claims.put("name",username);
        claims.put("password",password);
        String subject=username;
        String token=JWTUtils.createJWT(claims,subject,1000*60);
        HashMap<String,String> result=new HashMap<>();
        result.put("code","200");
        result.put("token",token);
        Cookie c=new Cookie("token",token);
        response.addCookie(c);
        return result;
    }

    @GetMapping("testlogin")
    public Object getLoginInfo(HttpServletRequest request) throws Exception {
        HashMap<String,String> result=new HashMap<>();
        String token=request.getHeader("token");
        if (token==null){
            result.put("code",400+"");
        }else{
            Claims c= null;
            try {
                c = JWTUtils.parseJWT(token);

                result.put("code",200+"");
                result.put("claim",c.toString());
            } catch (Exception e) {
                e.printStackTrace();
                result.put("code",401+"");
            }
        }
        return result;

    }
}
