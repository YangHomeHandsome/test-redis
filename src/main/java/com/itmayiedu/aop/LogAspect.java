package com.itmayiedu.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;

/**
 * Created by YJJ on 2019/9/25.
 */
//标识这个一个切面类
//@Aspect
//注入到ioc容器
//删了日志的配置文件了  先不着急注入IOC做切面
//@Component
public class LogAspect {
    //配置日志变量
    static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    //按通配表达式配置切点
    @Pointcut("execution(* com.itmayiedu.Controller.UserController.*(..))") // 切点表达式UserController用.*替代标识所有controller  （..）前的*表示任意一个方法 ..表示0个或多个参数
    private void patten() {} // 切点前面

    //也可以不用value，直接使用execution：@Before("execution(* com.itmayiedu.Controller.UserController.*(name,..))")  同样可以达到下面的配置
    @Before(value = "patten()  &&  args(name,..)")//切点和第一个参数为name的方法也可以不用后面的&& args(name) 参数信息也可以用(*,name,..)来匹配多个参数，第二个是name的方法
    public void BeforeControllerHandle(JoinPoint point, String name) {//JoinPoint为所切方法信息
        logger.info("controller: "+point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：模拟权限检查...");
        System.out.println("@Before：目标方法为：" +
                point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName());
        System.out.println("@Before：参数为：" + Arrays.toString(point.getArgs()));
        System.out.println("@Before：被织入的目标对象为：" + point.getTarget());
    }

    @Pointcut("@annotation(com.itmayiedu.annocation.AopTest)")//被指定注解类注解的方法会匹配
    private void annotation(){}

    @Before("annotation()")
    public void BeforeAnnotationMethodHandle(JoinPoint point){
        logger.info("controller: "+point.getSignature().getDeclaringTypeName() +
                "." + point.getSignature().getName()+ "  method = @AopTest");
        Signature o=point.getSignature();
        System.out.println(o.getDeclaringTypeName());
        System.out.println(o.getName());
    }

}
