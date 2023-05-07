package com.baedal.monolithic.global.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3Util {

    private final AmazonS3 amazonS3Client;
    private final char CATEGORY_PREFIX = '/';
    private final char TIME_SEPARATOR = '_';
    private static final String FILE_EXTENSION_SEPARATOR = ".";


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    public String upload(MultipartFile multipartFile, String dirName) throws IOException {

        String fileName = getNewFileName(dirName, multipartFile.getOriginalFilename());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.IMAGE_PNG_VALUE);
        metadata.setContentLength(multipartFile.getSize());

        amazonS3Client.putObject(new PutObjectRequest(bucket, fileName, multipartFile.getInputStream(), metadata));

        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

    public void remove(String url, String dirName) {

        if (url == "" || url.isEmpty()) return;

        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket,
                    URLDecoder.decode(getKey(dirName, getFileName(url)), "utf-8")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getS3Url(String dirName, String fileName) {
        return amazonS3Client.getUrl(bucket, getKey(dirName, fileName)).toString();
    }

    private String getNewFileName(String dirName, String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        String fileExtension = originalFileName.substring(fileExtensionIndex);
        String fileName = originalFileName.substring(0, fileExtensionIndex);
        String now = String.valueOf(System.currentTimeMillis());

        String newFileName = fileName + TIME_SEPARATOR + now + fileExtension;

        return getKey(dirName, newFileName);
    }

    private String getFileName(String url) {
        return url.substring(url.lastIndexOf(CATEGORY_PREFIX)+1);
    }

    private String getKey(String dirName, String fileName) {
        return dirName + CATEGORY_PREFIX + fileName;
    }

}