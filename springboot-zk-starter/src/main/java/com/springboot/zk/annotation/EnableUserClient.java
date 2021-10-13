package com.springboot.zk.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.springboot.zk.ZkAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk.annotation
 * @ClassName: EnableUserClient
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/12/2021 10:26 AM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ZkAutoConfiguration.class})
public @interface EnableUserClient {

}
