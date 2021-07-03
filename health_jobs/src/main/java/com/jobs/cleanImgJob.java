package com.jobs;


import com.common.QiNiuUtils;
import com.dao.SetmealDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class cleanImgJob {
    private static final Logger log = (Logger) LoggerFactory.getLogger(cleanImgJob.class);

    @Autowired
    private SetmealDao sl;

    public void cleanImg() {
        List<String> qiniulist = QiNiuUtils.listFile();
        System.out.println("七牛上面的图片是：" + qiniulist);

        /*List<String> dblist = sl.findAllImg();
        System.out.println("数据库里面的图片是：" + dblist);

        qiniulist.removeAll(dblist);
        System.out.println("剩下的要删除的图片是：" + qiniulist);

        QiNiuUtils.removeFiles(qiniulist.toArray(new String[]{}));*/
    }

    /*public void clean7NiuJob() {
        log.info("开始执行清理7牛上垃圾图片任务!!!!!!!!");

        List<String> imgIn7Niu = QiNiuUtils.listFile();
        log.info("七牛上有{}张图片", imgIn7Niu.size());

       *//* List<String> imgInDB = sl.getImgs();
        log.info("数据库上有{}张图片",imgInDB==null?0:imgInDB.size());

        imgIn7Niu.removeAll(imgInDB);
        log.info("需要清理的垃圾图片共有{}张",imgIn7Niu.size());

        imgIn7Niu.removeAll(imgInDB);
        log.info("需要清理的垃圾图片共有{}张",imgIn7Niu.size());

        String[] imgNeed2Delete = imgIn7Niu.toArray(new String[]{});
        QiNiuUtils.removeFiles(imgNeed2Delete);
        log.info("清理7牛上垃圾图片任务执行完毕....");*//*
    }*/
}
