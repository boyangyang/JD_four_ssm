package com.dao;

import com.github.pagehelper.Page;
import com.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckItemDao {
    void add(CheckItem checkItem);

    Page<CheckItem> selectByCond(String queryString);

    Long findCountByID(Integer id);

    void deleteByID(Integer id);

    int update(CheckItem checkItem);

    List<CheckItem> findAll();

}
