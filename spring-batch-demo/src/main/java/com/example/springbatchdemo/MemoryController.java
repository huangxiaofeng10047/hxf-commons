package com.example.springbatchdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.springbatchdemo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: spring-batch-demo
 * @Package: com.example.springbatchdemo
 * @ClassName: MemoryController
 * @Description: []
 * @Author: [xf huang]
 * @Date: 9/27/2021 11:34 AM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@RestController
public class MemoryController {

    private List<User> userList = new ArrayList<User>();
    private List<Class<?>>  classList = new ArrayList<Class<?>>();

    /**
     * -Xmx32M -Xms32M
     * */
    @GetMapping("/heap")
    public String heap() {
        int i=0;
        while(true) {
            userList.add(new User(i++, UUID.randomUUID().toString()));
        }
    }
    /**
     * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
     * */
    //@GetMapping("/nonheap")
    //public String nonheap() {
    //    while(true) {
    //        classList.addAll(Metaspace.createClasses());
    //    }
    //}
}

