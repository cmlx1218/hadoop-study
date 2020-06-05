package com.cmlx.hdfs.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-1 0001 15:40
 */
public class HdfsClient {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {

        //1、获取文件系统
        Configuration configuration = new Configuration();
        //2、配置在集群上运行
        //configuration.set("fs.defaultFS","hdfs://192.168.253.201:9000");
        //FileSystem fs = FileSystem.get(configuration);
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.253.201:9000"), configuration, "root");
        //3、创建目录
        fs.mkdirs(new Path("/tokyo/cmlx/520"));
        //4、关闭资源
        fs.close();
        System.out.println("over");



    }

}
