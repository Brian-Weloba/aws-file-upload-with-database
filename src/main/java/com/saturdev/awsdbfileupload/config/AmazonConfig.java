package com.saturdev.awsdbfileupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class AmazonConfig {

    @Value("${AWSAccessKeyId}")
    private String AWSAccessKeyId;

    @Value("${AWSSecretKey}")
    private String AWSSecretKey;

    @Bean
    public AmazonS3 s3() {
        AWSCredentials credentials = new BasicAWSCredentials(
                AWSAccessKeyId, AWSSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("af-south-1")
                .build();
    }
}
