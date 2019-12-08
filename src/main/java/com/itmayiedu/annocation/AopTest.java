package com.itmayiedu.annocation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by YJJ on 2019/9/25.
 */
//TYPE, METHOD, CONSTRUCTOR, FIELD等等
@Target(value = {ElementType.METHOD})
//生命周期：运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface AopTest {
}
