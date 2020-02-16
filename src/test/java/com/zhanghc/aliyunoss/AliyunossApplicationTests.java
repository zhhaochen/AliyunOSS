package com.zhanghc.aliyunoss;

import com.aliyun.oss.model.Bucket;
import com.zhanghc.aliyunoss.config.AliyunOSSConfig;
import com.zhanghc.aliyunoss.utils.AliyunOSSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AliyunossApplicationTests {

    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;

    @Test
    void contextLoads() {
        List<Bucket> buckets = aliyunOSSUtil.listBuckets();
        for (Bucket bucket:buckets
             ) {
            System.out.println(bucket.getName());
        }
    }

}
