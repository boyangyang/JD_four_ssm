package com.controller;

import com.common.MessageConstant;
import com.pojo.*;
import com.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Autowired
    private CheckItemService cs;

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            System.out.println("checkItem = " + checkItem.getAge());
            cs.add(checkItem);
        } catch (Exception e) {
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/fingPage")
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")
    public PageResult fingPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = cs.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE23498723482h3k4hkahdfkh234')")
    public Result delete(Integer id) {

        try {
            cs.delete(id);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @RequestMapping("/update")
//    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")
    public Result update(@RequestBody CheckItem checkItem) {
        int row = cs.update(checkItem);

        Result result = null;

        if (row > 0) {
            result = new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } else {
            result = new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return result;
    }

    @RequestMapping("/findAll")
    public Result findAll() {
        Result result = null;

        try {
            List<CheckItem> list = cs.findAll();

            result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, list);
        } catch (Exception e) {
            result = new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return result;
    }

}
