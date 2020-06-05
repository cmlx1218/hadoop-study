package com.cmlx.mapreduce.reducer;

import com.cmlx.mapreduce.pojo.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-3 0003 11:53
 */
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long sumUpFlow = 0;
        long sumDownFlow = 0;

        //1 遍历所有bean，将其中上行流量和下行流量分别累加
        for (FlowBean flowBean : values) {
            sumUpFlow += flowBean.getUpFlow();
            sumDownFlow += flowBean.getDownFlow();
        }

        //2 封装对象
        FlowBean flowBean = new FlowBean(sumUpFlow, sumDownFlow);

        //3 写出
        context.write(key, flowBean);
    }
}
