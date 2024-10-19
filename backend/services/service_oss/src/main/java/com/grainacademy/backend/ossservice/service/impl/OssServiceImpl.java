package com.grainacademy.backend.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.grainacademy.backend.ossservice.service.OssService;
import com.grainacademy.backend.ossservice.utils.ConstantProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {


    @Override
    public String uploadProfileAvatar(MultipartFile file, String type) {
        String endpoint = ConstantProperties.END_POINT;
        String keyId = ConstantProperties.ACCESS_KEY_ID;
        String keySecret = ConstantProperties.ACCESS_KEY_SECRET;
        String bucketName  = ConstantProperties.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);

        try{
            InputStream inputStream = file.getInputStream();

            String fileName = file.getOriginalFilename();

            //Add UUID to ensure the uniqueness of the file
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid+fileName;

            //Add date to folder structure
            ZonedDateTime gmtDate = ZonedDateTime.now(ZoneId.of("GMT"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String date = gmtDate.format(formatter);
            String objectPath = type + "/avatar/" + date + "/";



            PutObjectResult result = ossClient.putObject(bucketName, objectPath+fileName, inputStream);

            ossClient.shutdown();

            String url = "https://" + bucketName + "." + endpoint + "/" + objectPath + fileName;;

            return url;

        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteOssFile(String url) {
        String endpoint = ConstantProperties.END_POINT;
        String keyId = ConstantProperties.ACCESS_KEY_ID;
        String keySecret = ConstantProperties.ACCESS_KEY_SECRET;
        String bucketName  = ConstantProperties.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endpoint, keyId, keySecret);

        try{

            URI uri = URI.create(url);
            String path = uri.getPath();
            path = path.startsWith("/") ? path.substring(1) : path;

            System.out.println(path);

            ossClient.deleteObject(bucketName, path);

            ossClient.shutdown();

            return true;

        }catch(Exception e) {
            e.printStackTrace();

            return false;
        }
    }
}
