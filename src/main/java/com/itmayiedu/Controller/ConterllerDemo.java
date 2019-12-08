package com.itmayiedu.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by YJJ on 2019/9/23.
 */
//@Controller：普遍不用了，开始使用Rest风格的，更适用于微服务
@RestController
//Rest风格的Controller：返回的结果都是JSON格式的，就仿佛给每个方法都加了一个@ResponseBody
public class ConterllerDemo {

    //@RequestMapping("demo/get")==@RequestMapping(value={"demo/get"})
    //value和method都是数组形式
    //@PathVariable是路径参数形式（注意：下面demo/get取不到id会报错）
    @RequestMapping(value = {"demo/get","{id}/get2"},method = {RequestMethod.GET,RequestMethod.POST})
    public Object get(@PathVariable("id") Integer whatever){
        System.out.println(whatever);
        HashMap q=new HashMap();
        q.put("data",whatever);
        return q;
    }

    @RequestMapping("exception")
    public Object exceptionDeal(){
        int i=1/0;
        System.out.println("没报异常");
        return null;
    }
}
