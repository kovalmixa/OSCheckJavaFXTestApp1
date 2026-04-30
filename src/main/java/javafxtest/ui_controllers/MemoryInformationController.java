package javafxtest.ui_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafxtest.handlers.os_check_controller.OSCheckHandler;
import javafxtest.handlers.os_check_controller.cpu_data.CpuDynamicData;
import javafxtest.handlers.os_check_controller.memory_data.MemoryDynamicData;

public class MemoryInformationController {
    @FXML
    private ProgressBar memoryUsageProgressBar;
    @FXML
    private Label spaceUsageLabel;
    @FXML
    private Label totalSpaceLabel;
    @FXML
    private Label freeSpaceLabel;

    private Thread thread;
    private static int GYGABYTE = (int)Math.pow(2, 30); 

    @FXML
    public void initialize() throws Exception {
         startThread();

        Platform.runLater(() -> {
            Stage stage = (Stage) memoryUsageProgressBar.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                stopThread();
                System.out.println("The thread was stopped.");
            });
        });
    }
    private void setDynamicData(MemoryDynamicData data){
        totalSpaceLabel.setText(String.valueOf(data.totalSpace) + "B");
        freeSpaceLabel.setText(String.valueOf(data.freeSpace) + "B");
        float usagePercentage =  (float)data.freeSpace / data.totalSpace;
        memoryUsageProgressBar.setProgress(usagePercentage);
        spaceUsageLabel.setText(String.format("%.2f", usagePercentage * 100) + "%");
    }
    //region Thead methods
    private void startThread() {
        thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    MemoryDynamicData data = OSCheckHandler.getInstance().getMemoryDynamicData();
                    Platform.runLater(() -> { 
                        setDynamicData(data);
                    });

                    Thread.sleep(1000); 
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                onThreadFinished();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    private void stopThread() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private void onThreadFinished() {
        System.out.println("Thread is fully stoped.");
    }
    //endregion
}
