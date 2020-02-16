package com.zhanghc.aliyunoss.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.PutObjectResult;
import com.zhanghc.aliyunoss.config.AliyunOSSConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Component
public class AliyunOSSUtil {

    private AliyunOSSConfig aliyunOSSConfig;

    private OSS ossClient;



    public AliyunOSSUtil(AliyunOSSConfig aliyunOSSConfig){
        this.aliyunOSSConfig = aliyunOSSConfig;
        ossClient = new OSSClientBuilder().build(aliyunOSSConfig.getEndpoint(), aliyunOSSConfig.getAccessKeyId(), aliyunOSSConfig.getAccessKeySecret());
    }

    public void createOSSBucket(String bucketName){
        // 创建存储空间。
        ossClient.createBucket(bucketName);
    }

    public List<Bucket> listBuckets(){
        List<Bucket> buckets = ossClient.listBuckets();
        return buckets;
    }

    public void putObject(String objectName, byte[] bytes){
        ossClient.putObject(aliyunOSSConfig.getBucketName(), objectName, new ByteArrayInputStream(bytes));
    }

    public void putObject(String objectName, InputStream inputStream){
        String name = UUID.randomUUID().toString().replaceAll("-", "") + objectName;
        PutObjectResult putObjectResult = ossClient.putObject(aliyunOSSConfig.getBucketName(), name, inputStream);
    }
    public void shutDown(){
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
