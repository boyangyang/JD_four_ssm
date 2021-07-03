package com.dao;

import com.github.pagehelper.Page;
import com.pojo.CheckGroup;
import com.pojo.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckGroupDao {

    int add(CheckGroup checkGroup);

    int addItem(@Param("checkgroupId") Integer checkgroupId, @Param("checkitemId") int checkitemId);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findByID(int id);

    List<Integer> findcheckitemIDsBycheckgroupIDs(int id);

    void update(CheckGroup checkGroup);

    void deletecheckgroupcheckitem(Integer checkgroupID);

    List<CheckGroup> findAll();

}
