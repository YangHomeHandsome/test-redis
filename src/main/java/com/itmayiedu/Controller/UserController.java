package com.itmayiedu.Controller;

import com.itmayiedu.annocation.AopTest;
import com.itmayiedu.entity.User;
import com.itmayiedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by YJJ on 2019/9/25.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @AopTest
    @PostMapping("addUser")
    public int insertUser(User user){
        Integer t=userService.insertUser(user);
        return t;
    }

    @PostMapping("addUser2")
    public int insertUser(String name,String password){
        Integer t=userService.insertUser(new User(name,password));
        return t;
    }
    @RequestMapping("testInterceptor")
    public Object testInterceptor(){
        for (int i=0;i<5;i++){
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return "yes";
    }
}
