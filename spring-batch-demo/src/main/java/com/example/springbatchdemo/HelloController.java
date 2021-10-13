package com.example.springbatchdemo;

import com.springboot.zk.DisTributedLockByCutator;
import com.springboot.zk.ZkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk
 * @ClassName: HelloController
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/9/2021 9:06 AM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@RestController
@RequestMapping("/ZK")
public class HelloController {
    @Autowired
    ZkService disTributedLockByCutator;
    @RequestMapping(value = "curatorlock1",method = RequestMethod.POST)
    public Object curatorlock1(){
        String path="test";
        Boolean flag;
          disTributedLockByCutator.getLock(path);
            try {
                System.out.println("curator1 begin work");
                Thread.sleep(20000);
            }catch (InterruptedException e){
                e.printStackTrace();
                flag=disTributedLockByCutator.release(path);
            }
            flag=disTributedLockByCutator.release(path);

        return flag;
    }
    @RequestMapping(value = "curatorlock2",method = RequestMethod.POST)
    public Object curatorlock2(){
        String path="test";
        Boolean flag;
        disTributedLockByCutator.getLock(path);
            try {
                System.out.println("curator2 begin work");
                Thread.sleep(20000);
            }catch (InterruptedException e){
                e.printStackTrace();
                flag=disTributedLockByCutator.release(path);
            }
            flag=disTributedLockByCutator.release(path);
        return flag;
    }
}
