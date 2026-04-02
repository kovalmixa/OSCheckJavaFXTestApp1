package javafxtest.ui_controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafxtest.handlers.os_check_controller.OSCheckHandler;
import javafxtest.handlers.os_check_controller.cpu_data.CpuDynamicData;

public class CpuLoadController {
    @FXML
    private Label cpuUsagePercLabel;
    @FXML
    private ProgressBar cpuUsageProgressBar;

    private Thread thread;

    @FXML
    public void initialize() throws Exception {
         startThread();

        Platform.runLater(() -> {
            Stage stage = (Stage) cpuUsageProgressBar.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                stopThread();
                System.out.println("The thread was stopped.");
            });
        });
    }
    private void setCpuDynamicData(CpuDynamicData data){
        cpuUsageProgressBar.setProgress(data.cpuLoadTicksPerc / 100);
        cpuUsagePercLabel.setText(String.format("%.2f", data.cpuLoadTicksPerc) + "%");
    }
    //region Thead methods
    private void startThread() {
        thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    CpuDynamicData data = OSCheckHandler.getInstance().getCpuDynamicData();
                    Platform.runLater(() -> { 
                        setCpuDynamicData(data);
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
