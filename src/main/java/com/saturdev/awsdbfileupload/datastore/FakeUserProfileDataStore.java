package com.saturdev.awsdbfileupload.datastore;

import com.saturdev.awsdbfileupload.profile.UserProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
}
