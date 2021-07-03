package com.service;

import com.pojo.CheckGroup;
import com.pojo.CheckItem;
import com.pojo.PageResult;
import com.pojo.QueryPageBean;

import java.util.List;

public interface CheckGroupService {
    int add(CheckGroup checkGroup, int[] checkitemIds);

//    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);


    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findByID(int id);


    List<Integer> findcheckitemIDsBycheckgroupIDs(int id);


    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    List<CheckGroup> findAll();

}
