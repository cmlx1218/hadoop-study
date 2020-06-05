package com.cmlx.mapreduce.define;


import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc
 * @Author cmlx
 * @Date 2020-6-4 0004 15:54
 */
public class test {

    public static void main(String[] args) {
        GroupFeedEntity entity = new GroupFeedEntity();
        entity.setName("你好");

        GroupFeedEntity entity1 = new GroupFeedEntity();
        entity1.setAddress("xxxxx");

        List<GroupFeedEntity> list = new ArrayList<GroupFeedEntity>();
        list.add(entity);
        list.add(entity1);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("name", "xxx");
        map.put("count", 111);

        String s = JSON.toJSONString(map);

        Object parse = JSON.parse(s);
        Map<String, Object> map1 = (Map<String, Object>) parse;
        Object o = map1.get("list");

        List<GroupFeedEntity> list1 = (List<GroupFeedEntity>) o;


        System.out.println(list);
        System.out.println(list1);


    }

}
