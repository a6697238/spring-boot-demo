package com.util;

import com.alibaba.fastjson.JSON;
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
        read();
    }

    @SneakyThrows
    public static void read() {

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
        for(Integer res : accountSet){
            System.out.println(res);
        }
    }
}
