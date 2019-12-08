package com.itmayiedu.mapper;

import com.itmayiedu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * Created by YJJ on 2019/9/24.
 */
public interface UserMapper {

    @Insert("insert into users values(null,#{name},#{password})")
    @Options(useGeneratedKeys = true,keyColumn="id",keyProperty = "id")
    public int insertUser(User user);

}
