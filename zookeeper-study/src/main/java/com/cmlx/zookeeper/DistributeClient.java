package com.cmlx.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-9 0009 18:54
 */
public class DistributeClient {

    private static String connectString = "zq201:2181,zq202:2181,zq203:2181";
    private static int sessionTimeout = 2000;
    private ZooKeeper zooKeeper = null;
    private String parentNode = "/servers";

    //创建zk的客户端连接
    private void getConnection() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            public void process(WatchedEvent watchedEvent) {

                // 再次启动监听
                try {
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }

    //获取服务器列表信息
    private void getServerList() throws KeeperException, InterruptedException {
        //1 获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zooKeeper.getChildren(parentNode, true);

        //2 存储服务器信息列表
        List<String> servers = new ArrayList<String>();

        //3 遍历所有节点，获取节点中的主机名称信息
        for (String child : children) {
            byte[] data = zooKeeper.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
        }

        //4 打印服务器列表信息
        System.out.println(servers);
    }

    // 业务功能

    public void business() throws Exception {
        System.out.println("client is working ...");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws Exception {
        //1 获取zk连接
        DistributeClient client = new DistributeClient();
        client.getConnection();

        //2 获取servers的子节点信息，从中获取服务器信息列表
        client.getServerList();

        //3 业务进程启动
        client.business();
    }


}
