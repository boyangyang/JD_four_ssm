package com.controller;

import com.common.MessageConstant;
import com.common.QiNiuUtils;
import com.pojo.Result;
import com.pojo.Setmeal;
import com.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService ss;

    @RequestMapping("/upload")
    public Result upload(MultipartFile imgFile) {

        try {
            String oldName = imgFile.getOriginalFilename();
            String newNmae = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf('.'));

            QiNiuUtils.uploadFile(imgFile.getBytes(), newNmae);

            HashMap<String, String> map = new HashMap<>();
            map.put("domain", QiNiuUtils.DOMAIN);
            map.put("imgName", newNmae);

            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, map);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }


    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, int[] checkgroupIds) {
        int row = ss.add(setmeal, checkgroupIds);

        Result result = null;
        if (row > 0) {
            result = new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } else {
            result = new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return result;
    }

}
