package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.example.demo.entity.BookEntity;
import java.util.List;
import lombok.SneakyThrows;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
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


    public void testIndex() {
        if (queryIndex()) {
            deleteIndex();
        }
        createIndex();
    }

    @SneakyThrows
    private boolean queryIndex() {
        return restHighLevelClient.indices().exists(new GetIndexRequest("ods_review_trace_log_20211012"), options);
    }

    @SneakyThrows
    private boolean createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("ods_review_trace_log_20211012");
        CreateIndexResponse response = restHighLevelClient.indices().create(request, options);
        System.out.println(response.isAcknowledged());
        return true;

    }

    @SneakyThrows
    private boolean deleteIndex() {
        DeleteIndexRequest request = new DeleteIndexRequest("ods_review_trace_log_20211012");
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, options);
        return response.isAcknowledged();

    }


    @SneakyThrows
    public boolean addVal() {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(new IndexRequest().index("book").id("1")
                .source(JSON.toJSONString(new BookEntity("汉语言", "锄禾日当午。汗滴禾下土")), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("book").id("2")
                .source(JSON.toJSONString(new BookEntity("汉语言", "中国人民万岁")), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("book").id("3")
                .source(JSON.toJSONString(new BookEntity("汉语言", "世界人民万岁")), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("book").id("4")
                .source(JSON.toJSONString(new BookEntity("汉语言", "我爱北京天安门")), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("book").id("5")
                .source(JSON.toJSONString(new BookEntity("汉语言", "我爱中国")), XContentType.JSON));
        bulkRequest.add(new IndexRequest().index("book").id("6")
                .source(JSON.toJSONString(new BookEntity("汉语言", "我爱中国北京天安门")), XContentType.JSON));

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        return bulkResponse.hasFailures();

    }


    @SneakyThrows
    public boolean search(String val) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("book")
                .source(new SearchSourceBuilder().query(
                        QueryBuilders.boolQuery()
                                .should(QueryBuilders.matchQuery("content", val))
                ));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : searchResponse.getHits()) {
            System.out.println(hit.getSourceAsString());
        }

        return true;

    }


}
