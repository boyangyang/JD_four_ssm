package com.dao;

import com.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetmealDao {

    int add(Setmeal setmeal);

    int addCheckGroup(@Param("id") Integer id, @Param("checkgroupId") int checkgroupId);

//    List<String> getImgs();

}
