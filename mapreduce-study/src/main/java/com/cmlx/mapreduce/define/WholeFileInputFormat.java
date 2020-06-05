package com.cmlx.mapreduce.define;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * @Desc 自定义FileInputFormat
 * @Author cmlx
 * @Date 2020-6-3 0003 18:04
 */
public class WholeFileInputFormat extends FileInputFormat<Text, BytesWritable> {

    // 返回false不可分割
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        return false;
    }

    // 采用IO流一次读取一个文件输出到value中，因为设置了不可切片，最终把所有文件都封装到了value中
    // 获取文件路径信息+名称，并设置key
    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {

        WholeRecordReader recordReader = new WholeRecordReader();
        recordReader.initialize(split, context);
        return recordReader;
    }


}
