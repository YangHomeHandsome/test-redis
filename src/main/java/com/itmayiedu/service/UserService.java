package com.itmayiedu.service;

import com.itmayiedu.entity.User;
import com.itmayiedu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YJJ on 2019/9/24.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    @Transactional
    public int insertUser(User user){
        System.out.println("插入前"+user.getId());
        int result=userMapper.insertUser(user);
        System.out.println(result+"条成功，插入的值是"+user.getId());
        //int i=1/0;
        return user.getId();
    }
}
