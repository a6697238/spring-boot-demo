package com.example.demo.entity;

import lombok.Data;

/**
 * 功能描述：
 *
 * @Author: winghou
 * @Date: 2024/6/27 上午8:38
 */
@Data
public class BookEntity {

    private String name;

    private String content;

    public BookEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
