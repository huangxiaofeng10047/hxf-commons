package com.springboot.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk
 * @ClassName: CuratorConfigure
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/8/2021 5:04 PM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
//@Configuration
public class CuratorConfigure {
    @Value("${curator.retryCount}")
    private int retryCount;
    @Value("${curator.elapsedTimeMs}")
    private int elapseTimeMs;
    @Value("${curator.connectString}")
    private String connectString;
    @Value("${curator.sessionTimeoutMs}")
    private int sessionTimeoutMs;
    @Value("${curator.connectTimeoutMs}")
    private int connectTimeoutMs;
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){
        return CuratorFrameworkFactory.newClient(
            connectString,
            sessionTimeoutMs,
            connectTimeoutMs,
            new RetryNTimes(retryCount,elapseTimeMs)
        );
    }
}
