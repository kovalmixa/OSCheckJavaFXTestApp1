package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafxtest.handlers.os_check_controller.OSCheckHandler;
import javafxtest.handlers.os_check_controller.cpu_data.CpuStaticData;

public class CpuInfoController {
    @FXML
    private AnchorPane labelsPane;

    @FXML
    private Label cpuNameLabel;
    @FXML
    private Label cpuPhCoreLabel;
    @FXML
    private Label cpuLgCoreLabel;

    @FXML
    public void initialize() throws Exception {
        setCpuStaticData(OSCheckHandler.getInstance().getCpuStaticData());
    }
    
    void setCpuStaticData(CpuStaticData data){
        cpuNameLabel.setText(data.cpuName);
        cpuPhCoreLabel.setText(String.valueOf(data.physicalCores));
        cpuLgCoreLabel.setText(String.valueOf(data.logicalCores));
    }
}