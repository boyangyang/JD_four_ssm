package com.service;


import com.pojo.OrderSetting;

import java.util.ArrayList;
import java.util.List;

public interface OrderSettingService {

    int add(List<OrderSetting> ordersettinglist);

    List<OrderSetting> findorderbymonth(String date);

    int updateordernumber(String date, int number);
}
