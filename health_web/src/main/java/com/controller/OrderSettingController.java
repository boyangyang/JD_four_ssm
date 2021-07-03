package com.controller;

import com.common.MessageConstant;
import com.common.POIUtils;
import com.pojo.OrderSetting;
import com.pojo.Result;
import com.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Autowired
    private OrderSettingService os;

    @RequestMapping("/upload")
    public Result upload(MultipartFile excelFile) {

        try {

            List<String[]> strlist = POIUtils.readExcel(excelFile);

            List<OrderSetting> ordersettinglist = new ArrayList<>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");

            for (String[] strings : strlist) {
                OrderSetting os = new OrderSetting(sf.parse(strings[0]), Integer.parseInt(strings[1]));
                ordersettinglist.add(os);
            }

            int row = os.add(ordersettinglist);

            Result result = null;

            if (row > 0) {
                result = new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
            } else {
                result = new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
            }
            return result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }


    @RequestMapping("/findorderbymonth")
    public Result findorderbymonth(String date) {
        List<OrderSetting> list = os.findorderbymonth(date);

        List<Map> mapArrayList = new ArrayList<Map>();

        for (OrderSetting orderSetting : list) {
            Map map = new HashMap();

            map.put("date", orderSetting.getOrderDate().getDate());
            map.put("number", orderSetting.getNumber());
            map.put("reservations", orderSetting.getReservations());

            mapArrayList.add(map);
        }
        return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, mapArrayList);
    }


    @RequestMapping("/updateordernumber")
    public Result updateordernumber(String date, int number) {
        int row = os.updateordernumber(date, number);

        Result result = null;

        if (row > 0) {
            return new Result(true, "设置预约数量成功");
        } else {
            return new Result(false, "设置预约数量失败");
        }
    }

}
