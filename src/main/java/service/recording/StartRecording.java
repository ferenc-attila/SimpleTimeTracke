package service.recording;

import datamanagement.data.activity.Activity;
import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;

import java.time.LocalDateTime;

public class StartRecording {

    public void startRecording(String description, ActivityList activityList, Activity activity) {
        if (activityList.numberOfActiveRecording() > 0) {
            throw new IllegalStateException("Active recording running! Try to stop it before start another!");
        }
        int identifier = createIdentifier(activityList);
        Recording recording = new Recording(identifier, description, activity, LocalDateTime.now());
        activity.addRecording(recording);
    }

    public StringBuilder printStartMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording started with parameters below:\n");
        message.append(recording.toString());
        return message;
    }

    private int createIdentifier(ActivityList activityList) {
        int maxIdentifier = -1;
        for (Activity actual : activityList.getActivities()) {
            if (actual.getMaxRecordingIdentifier() > maxIdentifier) {
                maxIdentifier = actual.getMaxRecordingIdentifier();
            }
        }
        return maxIdentifier + 1;
    }
}
