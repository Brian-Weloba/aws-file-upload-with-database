package com.saturdev.awsdbfileupload.profile;

import com.saturdev.awsdbfileupload.datastore.FakeUserProfileDataStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserProfileDataAccessService {

    private final FakeUserProfileDataStore fakeUserProfileDataStore;

    @Autowired
    public UserProfileDataAccessService(FakeUserProfileDataStore fakeUserProfileDataStore) {
        this.fakeUserProfileDataStore = fakeUserProfileDataStore;
    }

    List<UserProfile> getAllUserProfiles() {
        return fakeUserProfileDataStore.getUserProfiles();
    }

    public UserProfile getUserProfile(UUID userProfileId) {
        return fakeUserProfileDataStore.getUserProfile(userProfileId);
    }
}
