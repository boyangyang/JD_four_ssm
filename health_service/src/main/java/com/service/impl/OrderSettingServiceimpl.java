package com.service.impl;

import com.dao.OrderSettingDao;
import com.pojo.OrderSetting;
import com.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderSettingServiceimpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao os;

    @Override
    public int add(List<OrderSetting> ordersettinglist) {
        int row1 = 0;
        int row2 = 0;

        for (OrderSetting orderSetting : ordersettinglist) {
            Date date = orderSetting.getOrderDate();

            int number = orderSetting.getNumber();

            OrderSetting osIDB = os.findOrderSettingByDate(date);
            if (osIDB != null) {
                if (number < osIDB.getReservations()) {
                    throw new RuntimeException("可预约数量必须大于已经预约数量");
                } else {
                    row1 += os.update(orderSetting);
                }
            } else {
                row2 += os.add(orderSetting);
            }
        }
        return (row1 + row2 == ordersettinglist.size()) ? 1 : 0;
    }

    @Override
    public List<OrderSetting> findorderbymonth(String date) {

        String begin = date + "-1";
        String end = date + "-31";

        return os.findorderbymonth(begin, end);
    }

    @Override
    public int updateordernumber(String date, int number) {

        return os.updateordernumber(date, number);
    }
}
