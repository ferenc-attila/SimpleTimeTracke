package service.recording;

import datamanagement.data.activity.ActivityList;
import datamanagement.data.recording.Recording;
import userlayer.menu.MainMenu;

import java.time.LocalDateTime;

public class StopRecording {

    public void runStopRecording(ActivityList activityList, MainMenu mainMenu) {
        System.out.println("\n\n*****              Stop recording                  *****\n\n");
        try {
            Recording finishedRecord = activityList.findActiveRecording();
            stopRecording(finishedRecord);
            System.out.println(printStopMessage(finishedRecord));
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        } finally {
            mainMenu.startFunctionSelector(activityList);
        }
    }

    public void stopRecording(Recording recording) {
        recording.finishRecording(LocalDateTime.now());
    }

    public StringBuilder printStopMessage(Recording recording) {
        StringBuilder message = new StringBuilder();
        message.append("Recording ended with parameters below:\n");
        message.append(recording.toString());
        return message;
    }
}
