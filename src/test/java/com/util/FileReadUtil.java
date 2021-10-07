package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Set;
import lombok.SneakyThrows;
import org.assertj.core.util.Sets;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/7/7 4:31 下午
 */
public class FileReadUtil {


    public static void main(String[] args) {
        readEsTask();
    }

    @SneakyThrows
    public static void readEsTask() {

        Set<Integer> accountSet = Sets.newHashSet();

        File cfgFile = ResourceUtils.getFile("/Users/kira6697238/Downloads/task.json");
        FileReader fileReader = new FileReader(cfgFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        StringBuilder sb = new StringBuilder();
        while (line != null) {
            sb.append(line);
            line = bufferedReader.readLine();
        }
        JSONObject jsonObject = JSON.parseObject(sb.toString());
        JSONObject nodes = jsonObject.getJSONObject("nodes");
        for(String node:nodes.keySet()){
            JSONObject nodeJson = nodes.getJSONObject(node);
            JSONObject tasks = nodeJson.getJSONObject("tasks");
            for(String task: tasks.keySet()){
                JSONObject taskNode = tasks.getJSONObject(task);
                String action = (String) taskNode.get("action");
                if(action.contains("search")){
                    long time = Long.parseLong(String.valueOf(taskNode.get("running_time_in_nanos")));
                    time = time/1000000000;
                    if(time>10){
                        System.out.println(task);
                        System.out.println(time);

                    }
                }

            }


        }
    }


    @SneakyThrows
    public static void readAdvertiser() {

        Set<Integer> accountSet = Sets.newHashSet();

        File cfgFile = ResourceUtils.getFile("/Users/kira6697238/Downloads/advertiser.data");
        FileReader fileReader = new FileReader(cfgFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();

        while (line != null) {
            JSONObject json = JSON.parseObject(line);
            Integer accountId = (Integer) json.get("accountId");
            accountSet.add(accountId);
            line = bufferedReader.readLine();
        }
        System.out.println(accountSet.size());
        for (Integer res : accountSet) {
            System.out.println(res);
        }
    }
}
