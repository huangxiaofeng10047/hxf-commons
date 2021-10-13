package com.springboot.zk;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk
 * @ClassName: TianProperties
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/9/2021 2:55 PM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
@ConfigurationProperties(prefix = "spring.tian")
public class TianProperties {
    private int retryCount;
    private int elapseTimeMs;
    private String connectString;

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public int getElapseTimeMs() {
        return elapseTimeMs;
    }

    public void setElapseTimeMs(int elapseTimeMs) {
        this.elapseTimeMs = elapseTimeMs;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }

    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }

    public int getConnectTimeoutMs() {
        return connectTimeoutMs;
    }

    public void setConnectTimeoutMs(int connectTimeoutMs) {
        this.connectTimeoutMs = connectTimeoutMs;
    }

    private int sessionTimeoutMs;
    private int connectTimeoutMs;

}
