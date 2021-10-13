package com.springboot.zk;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.listen.Listenable;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCache.Options;
import org.apache.curator.framework.recipes.cache.CuratorCacheBuilder;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By xfhuang
 *
 * @ProjectName: springboot-zk
 * @Package: com.springboot.zk
 * @ClassName: DisTributedLockByCutator
 * @Description: []
 * @Author: [xf huang]
 * @Date: 10/8/2021 5:23 PM
 * @Version: V1.0
 * @TODO: 注意, 本文件xf huang所作,如果转载或使用请标明具体出处!
 **/
//@Service
public class DisTributedLockByCutator  implements InitializingBean {
    private static Logger logger= LoggerFactory.getLogger(DisTributedLockByCutator.class);
    private final static String ROOT_PATH_LOCK="zklock";
    private CountDownLatch countDownLatch=new CountDownLatch(1);
    @Autowired
    private CuratorFramework curatorFramework;
    public void getLock(String path){
        String keyPath="/"+ROOT_PATH_LOCK+"/"+path;
        while (true){
            try{
                curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).withACL(
                    Ids.OPEN_ACL_UNSAFE).forPath(keyPath);
                logger.info("success to acquire lock for path:{}",keyPath);
                break;
            }catch (Exception e){
                logger.info("fail to acquire locker path ：{}",keyPath);
                try {
                    if (countDownLatch.getCount()<=0){
                        countDownLatch=new CountDownLatch(1);
                    }
                    countDownLatch.await();

                }catch (Exception e1){
                    e1.printStackTrace();
                }finally {
                }
            }
        }
    }
    public boolean release(String path) {
        String keyPath="/"+ROOT_PATH_LOCK+"/"+path;
        try {
            if (curatorFramework.checkExists().forPath(keyPath)!=null){
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }
        return true;
    }
    private void addWatcher(String path){
        String keyPath;
        if (path.equals(ROOT_PATH_LOCK)){
            keyPath="/"+path;
        }else {
            keyPath="/"+ROOT_PATH_LOCK+"/"+path;
        }
        final CuratorCache cacheEvent= CuratorCache.build(curatorFramework,keyPath, Options.SINGLE_NODE_CACHE);
        cacheEvent.start();
        cacheEvent.listenable().addListener(new CuratorCacheListener() {
            @Override
            public void event(Type type, ChildData oldData, ChildData data) {
                if (type.name().equals("NODE_CREATED")){

                }else if (type.name().equals("NODE_CHANGED")){

                }else{
                    String oldPath=oldData.getPath();
                    if (oldPath.contains(path)){
                        countDownLatch.countDown();
                    }
                }
            }
        });

    }
    @Override
    public void afterPropertiesSet() throws Exception {
        curatorFramework=curatorFramework.usingNamespace("lock-namespace");
        String path="/"+ROOT_PATH_LOCK;
        if (curatorFramework.checkExists().forPath(path)==null){
            curatorFramework.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).withACL(Ids.OPEN_ACL_UNSAFE).forPath(path);

        }
        addWatcher(ROOT_PATH_LOCK);
    }
}
