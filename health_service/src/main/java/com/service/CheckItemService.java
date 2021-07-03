package com.service;

import com.pojo.CheckItem;
import com.pojo.PageResult;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void delete(Integer id);

    int update(CheckItem checkItem);

    List<CheckItem> findAll();

}
