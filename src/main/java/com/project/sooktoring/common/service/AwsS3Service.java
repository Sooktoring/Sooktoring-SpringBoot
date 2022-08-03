package com.project.sooktoring.common.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.project.sooktoring.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.project.sooktoring.common.exception.ErrorCode.FAILED_FILE_UPLOAD;

@Slf4j
@RequiredArgsConstructor
@Service
public class AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3 amazonS3;

    //이미지 업로드
    public String uploadImg(MultipartFile file, String category) {
        String fileName = category + UUID.randomUUID();
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try(InputStream inputStream = file.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch(IOException e) {
            throw new CustomException(FAILED_FILE_UPLOAD);
        }

        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    //이미지 삭제
    public void deleteImg(String originImgUrl) {
        if (originImgUrl.equals("")) return;

        try {
            amazonS3.deleteObject(bucketName, originImgUrl.split("/")[3]);
        } catch (AmazonServiceException e) {
            log.info("error = ", e);
        }
    }

}
