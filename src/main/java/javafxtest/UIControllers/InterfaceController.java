package javafxtest.UIControllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafxtest.OSCheckControllers.OSCheckHandler;
import javafxtest.OSCheckControllers.OSDynamicData;
import javafxtest.OSCheckControllers.OSStaticData;

public class InterfaceController {
    @FXML
    private Button switchButton;
    @FXML
    private AnchorPane dataPane;
    @FXML
    private Label bitnessLabel;
    @FXML
    private Label domainNameLabel;
    @FXML
    private Label familyNameLabel;
    @FXML
    private Label totalSpaceLabel;
    @FXML
    private Label freeSpaceLabel;
    @FXML
    private Label cpuNameLabel;
    @FXML
    private Label cpuUsagePercLabel;

    private OSCheckHandler osCheckHandler;
    private Boolean isInProcess = false;
    private Thread thread;

    @FXML
    public void initialize() {
        dataPane.setVisible(false);
        osCheckHandler = new OSCheckHandler();
    }

    @FXML
    void handleSwitchButtonClick(ActionEvent event) {
        isInProcess = !isInProcess;
        if (isInProcess){
            dataPane.setVisible(true);
            setOSStaticData(osCheckHandler.getOSStaticData());
            switchButton.setText("Stop process");
            startThread();
        }
        else{
            switchButton.setText("Start OS check");
            stopThread();
        } 
    }
    
    void setOSStaticData(OSStaticData data){
        bitnessLabel.setText(String.valueOf(data.bitness));
        domainNameLabel.setText(data.domainName);
        familyNameLabel.setText(data.familyName);
        totalSpaceLabel.setText(String.valueOf(data.totalSpace) + "GB");
        freeSpaceLabel.setText(String.valueOf(data.freeSpace) + "GB");
        cpuNameLabel.setText(data.cpuName);
    }

    void setOSDynamicData(OSDynamicData data){
        cpuUsagePercLabel.setText(String.format("%.2f", data.cpuLoadTicksPerc) + "%");
    }

    //region Thead methods
    private void startThread() {
        thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    OSDynamicData data = osCheckHandler.getOSDynamicData();
                    Platform.runLater(() -> { 
                        setOSDynamicData(data); 
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