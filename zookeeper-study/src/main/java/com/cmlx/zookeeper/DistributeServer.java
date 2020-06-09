package com.cmlx.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-9 0009 18:45
 */
public class DistributeServer {

    private static String connectString = "zq201:2181,zq202:2181,zq203:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zooKeeper = null;
    private String parentNode = "/servers";

    //创建zk的客户端连接
    private void getConnection() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    //注册服务器
    public void registerServer(String hostname) throws KeeperException, InterruptedException {
        String create = zooKeeper.create(parentNode + "server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostname + " is on line " + create);
    }

    //业务功能
    public void business(String hostname) throws InterruptedException {
        System.out.println(hostname + "is working ...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //1 获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnection();

        //2 利用zk连接注册服务器信息
        server.registerServer(args[0]);

        //3 启动业务功能
        server.business(args[0]);
    }

}
