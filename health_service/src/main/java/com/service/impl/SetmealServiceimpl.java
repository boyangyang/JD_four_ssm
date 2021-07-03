package com.service.impl;

import com.dao.SetmealDao;
import com.pojo.Setmeal;
import com.service.SetmealService;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetmealServiceimpl implements SetmealService {
    @Autowired
    private SetmealDao sl;

    @Override
    public int add(Setmeal setmeal, int[] checkgroupIds) {

        int row1 = sl.add(setmeal);

        int row2 = 0;
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (int checkgroupId : checkgroupIds) {
                row2 += sl.addCheckGroup(setmeal.getId(), checkgroupId);
            }
        }
        return (row1 > 0 && row2 == checkgroupIds.length) ? 1 : 0;
    }

}
