package com.example.demo.service;

import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2021/10/7 12:44 下午
 */
@Service
public class EsService {

    private final RequestOptions options = RequestOptions.DEFAULT;

    @Autowired
    private RestHighLevelClient restHighLevelClient;


    public void testIndex(){
        if(queryIndex()){
            deleteIndex();
        }
        createIndex();
    }

    @SneakyThrows
    private boolean queryIndex(){
       return restHighLevelClient.indices().exists(new GetIndexRequest("ods_review_trace_log_20211012"),options);
    }

    @SneakyThrows
    private boolean createIndex(){
        CreateIndexRequest request = new CreateIndexRequest("ods_review_trace_log_20211012");
        CreateIndexResponse response = restHighLevelClient.indices().create(request,options);
        System.out.println(response.isAcknowledged());
        return true;

    }

    @SneakyThrows
    private boolean deleteIndex(){
        DeleteIndexRequest request = new DeleteIndexRequest("ods_review_trace_log_20211012");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request,options);
        return response.isAcknowledged();

    }

}
