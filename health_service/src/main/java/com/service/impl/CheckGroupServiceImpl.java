package com.service.impl;

import com.common.MessageConstant;
import com.dao.CheckGroupDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.pojo.CheckGroup;
import com.pojo.PageResult;
import com.pojo.QueryPageBean;
import com.pojo.Result;
import com.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao cg;

    @Override
    public int add(CheckGroup checkGroup, int[] checkitemIds) {
        int row = cg.add(checkGroup);

        int row2 = 0;
        if (row > 0) {
            for (int checkitemId : checkitemIds) {
                row2 += cg.addItem(checkGroup.getId(), checkitemId);

            }
        }
        return (row > 0 && row2 == checkitemIds.length) ? 1 : 0;
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        // 使用分页插件PageHelper，设置当前页，每页最多显示的记录数
        PageHelper.startPage(currentPage, pageSize);
        // 响应分页插件的Page对象
        Page<CheckGroup> page = cg.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public CheckGroup findByID(int id) {
        return cg.findByID(id);

    }

    @Override
    public List<Integer> findcheckitemIDsBycheckgroupIDs(int id) {
        return cg.findcheckitemIDsBycheckgroupIDs(id);
    }

    @Override
    public void update(CheckGroup checkGroup, Integer[] checkitemIds) {

        cg.update(checkGroup);

        Integer checkgroupID = checkGroup.getId();

        cg.deletecheckgroupcheckitem(checkgroupID);

        if (null != checkitemIds) {
            for (Integer checkitemId : checkitemIds) {
                cg.addItem(checkgroupID, checkitemId);
            }
        }
    }

    @Override
    public List<CheckGroup> findAll() {
        return cg.findAll();

    }

   /* @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        // 传递当前页，每页显示的记录数，查询条件
        // 响应PageResult，封装总记录数，结果集
        PageResult pageResult = cg.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;

    }*/


}
