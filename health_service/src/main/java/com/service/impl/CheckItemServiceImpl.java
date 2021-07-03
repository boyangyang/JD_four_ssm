package com.service.impl;

import com.dao.CheckItemDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pojo.CheckItem;
import com.pojo.PageResult;
import com.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao cd;


    @Override
    public void add(CheckItem checkItem) {
        cd.add(checkItem);
    }

    /*  @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {

        //采用mybatis插件
        PageHelper.startPage(currentPage, pageSize);

        List<CheckItem> list = cd.selectByCond(queryString);

        PageInfo<CheckItem> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(), pageInfo.getList());
    }*/

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //给予mybatis分页助手插件实现分页
        PageHelper.startPage(currentPage, pageSize);

        Page<CheckItem> page = cd.selectByCond(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void delete(Integer id) throws RuntimeException {

        Long count = cd.findCountByID(id);

        if (count > 0) {
            throw new RuntimeException("当前检查项被引用,不能删除");
        }

        cd.deleteByID(id);
    }

    @Override
    public int update(CheckItem checkItem) {

        return cd.update(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return cd.findAll();

    }


}
