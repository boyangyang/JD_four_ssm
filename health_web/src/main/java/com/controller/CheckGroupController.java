package com.controller;

import com.common.MessageConstant;
import com.pojo.*;
import com.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Autowired
    private CheckGroupService cs;

    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, int[] checkitemIds) {
        System.out.println(checkGroup);
        System.out.println(Arrays.toString(checkitemIds));

        int row = cs.add(checkGroup, checkitemIds);

        Result result = null;
        if (row > 0) {
            result = new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } else {
            result = new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return result;
    }

    /*@RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<CheckGroup> pageResult = cs.findPage(queryPageBean);

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
    }*/

    @RequestMapping("/findPage")
    public PageResult fingPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = cs.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    @GetMapping("/findByID")
    public Result findByID(int id) {
        CheckGroup checkGroup = cs.findByID(id);

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
    }

    @GetMapping("/findcheckitemIDsBycheckgroupIDs")
    public Result findcheckitemIDsBycheckgroupIDs(int id) {
        List<Integer> list = cs.findcheckitemIDsBycheckgroupIDs(id);

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
    }

    @PostMapping("/update")

    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        cs.update(checkGroup, checkitemIds);
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS);
    }

    @GetMapping("/findAll")
    public Result findAll() {
        List<CheckGroup> list = cs.findAll();

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
    }
}

