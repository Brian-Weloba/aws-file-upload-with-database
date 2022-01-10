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
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "John", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Jane", null));
        USER_PROFILES.add(new UserProfile(UUID.randomUUID(), "Bob", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

    public UserProfile getUserProfile(UUID userProfileId) {
        return USER_PROFILES.stream().filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId)).findFirst().orElse(null);
    }
}
