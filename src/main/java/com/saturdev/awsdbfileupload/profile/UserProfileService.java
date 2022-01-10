package com.saturdev.awsdbfileupload.profile;

import com.saturdev.awsdbfileupload.bucket.BucketName;
import com.saturdev.awsdbfileupload.filestore.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    public final UserProfileDataAccessService userProfileDataAccessService;
    private final FileStore fileStore;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, FileStore fileStore) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.fileStore = fileStore;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getAllUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //1. Check if image is not empty
        isEmpty(Objects.isNull(file) || file.isEmpty(), "File cannot be empty");

        //2. Check if user profile exists in database
        UserProfile user = getProfile(userProfileId);

        //3. Check if file is an image
        isEmpty(!Objects.requireNonNull(file.getContentType()).startsWith("image/"), "File is not an image");
        //4. grab some metadata about the image
        Map<String, String> metadata = getMetadata(file);
        //5. Save the image to S3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.S3_BUCKET_NAME.getBucketName(), user.getUserProfileId());
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, filename, Optional.of(metadata), file.getInputStream());
            user.setUserProfileImageLink(filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


        //6. Update the user profile in our database
    }

    private UserProfile getProfile(UUID userProfileId) {
        UserProfile user = userProfileDataAccessService
                .getAllUserProfiles()
                .stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User profile not found"));
        return user;
    }

    private UserProfile getUserProfile(UUID userProfileId) {
        UserProfile user = userProfileDataAccessService.getUserProfile(userProfileId);
        return user;
    }

    private Map<String, String> getMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void isEmpty(boolean file, String File_is_not_an_image) {
        isFileEmpty(file, File_is_not_an_image);
    }


    private void isFileEmpty(boolean file, String File_cannot_be_empty) {
        if (file) {
            throw new IllegalArgumentException(File_cannot_be_empty);
        }
    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getProfile(userProfileId);
        String path = String.format("%s/%s",
                BucketName.S3_BUCKET_NAME.getBucketName(),
                user.getUserProfileId());

        String link =  user.getUserProfileImageLink();
        return fileStore.download(path, link);
    }
}

