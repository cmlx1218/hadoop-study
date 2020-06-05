package com.cmlx.mapreduce.driver;

import com.cmlx.mapreduce.mapper.FlowMapper;
import com.cmlx.mapreduce.pojo.FlowBean;
import com.cmlx.mapreduce.reducer.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-3 0003 11:53
 */
public class FlowDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        //2 指定本程序的jar包所在的本地路径
        job.setJarByClass(FlowDriver.class);

        //3 指定本业务job要使用的mapper/reducer业务类
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        //4 指定mapper输出数据的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //5 最终输出的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //6 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //7 提交
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

}
