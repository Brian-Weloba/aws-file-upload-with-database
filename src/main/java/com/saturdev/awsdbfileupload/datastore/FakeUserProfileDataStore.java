package com.saturdev.awsdbfileupload.datastore;

import com.saturdev.awsdbfileupload.profile.UserProfile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class FakeUserProfileDataStore {
    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("d95c0ffa-242c-4991-a15d-c5a209eb51ef"), "John", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("dde55e93-8867-4fce-9520-527f73f73feb"), "Jane", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("58a1a389-1c1d-461c-a620-cf62c0e4daf3"), "Bob", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public UserProfile getUserProfile(UUID userProfileId) {
        return USER_PROFILES.stream().filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId)).findFirst().orElse(null);
    }
}
