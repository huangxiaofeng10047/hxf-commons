package com.springboot.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk
 * @ClassName: ZkAutoConfiguration
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/9/2021 2:51 PM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@Configuration
@EnableConfigurationProperties(TianProperties.class)
@ConditionalOnClass(ZkService.class)
@ConditionalOnProperty(prefix = "spring.tian", value = "enabled", matchIfMissing = true)
public class ZkAutoConfiguration {
    @Autowired
    private TianProperties properties;
    @Bean(initMethod = "start")
    public CuratorFramework curatorFramework(){

        return CuratorFrameworkFactory.newClient(
            properties.getConnectString(),
            properties.getSessionTimeoutMs(),
            properties.getConnectTimeoutMs(),
            new RetryNTimes(properties.getRetryCount(), properties.getElapseTimeMs())
        );
    }
    @Bean
    @ConditionalOnMissingBean(ZkService.class)
    @ConditionalOnProperty(prefix = "spring.tian",value = "enabled",havingValue = "true")
    public ZkService tianService() throws Exception {
        ZkService zkService= new ZkService(curatorFramework());
        zkService.afterPropertiesSet();
        return zkService;
    }


}
