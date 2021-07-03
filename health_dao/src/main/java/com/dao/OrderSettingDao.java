package com.dao;

import com.pojo.OrderSetting;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderSettingDao {

    OrderSetting findOrderSettingByDate(Date date);

    int update(OrderSetting orderSetting);

    int add(OrderSetting orderSetting);

    List<OrderSetting> findorderbymonth(@Param("begin") String begin,@Param("end") String end);

    int updateordernumber(String date, int number);

}
