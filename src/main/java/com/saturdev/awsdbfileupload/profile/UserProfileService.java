package com.saturdev.awsdbfileupload.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserProfileService {

    public final UserProfileDataAccessService userProfileDataAccessService;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService) {
        this.userProfileDataAccessService = userProfileDataAccessService;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getAllUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        //1. Check if image is not empty
        //2. Check if user profile exists in database
        //3. Check if file is an image
        //4. grab some metadata about the image
        //5. Save the image to S3 and update database (userProfileImageLink) with s3 image link
        //6. Update the user profile in our database
    }
}
