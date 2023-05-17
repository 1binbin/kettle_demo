package com.kettle.demo;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.trans.Trans;
import org.pentaho.di.trans.TransMeta;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws KettleException {
        long begin = System.currentTimeMillis();


        File file = FileUtil.file(
                "D:\\桌面\\test.ktr");


        String path = file.getPath();
        // 初始化
        KettleEnvironment.init();
        // 加载文件
        TransMeta transMeta = new TransMeta(path);
        Trans trans = new Trans(transMeta);
        // 放入参数，这里其实可以从数据库中取到
        // 如果没有参数，可以把这步忽略
        trans.prepareExecution(null);
        trans.startThreads();
        // 等待执行完毕
        trans.waitUntilFinished();
        long end = System.currentTimeMillis();
        log.info("{}执行完毕，用时:{}ms", file.getName(), end - begin);
    }

}
