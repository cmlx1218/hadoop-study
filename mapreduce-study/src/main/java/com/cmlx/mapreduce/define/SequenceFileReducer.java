package com.cmlx.mapreduce.define;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-3 0003 18:31
 */
public class SequenceFileReducer extends Reducer<Text, BytesWritable, Text, BytesWritable> {

    @Override
    protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {

        context.write(key, values.iterator().next());
    }
}
