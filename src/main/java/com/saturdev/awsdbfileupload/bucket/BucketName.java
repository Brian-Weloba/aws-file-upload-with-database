package com.saturdev.awsdbfileupload.bucket;

public enum BucketName {
    S3_BUCKET_NAME("image-upload-db");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
