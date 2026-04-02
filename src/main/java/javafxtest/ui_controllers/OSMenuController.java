package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafxtest.handlers.os_check_controller.OSCheckHandler;
import javafxtest.handlers.os_check_controller.os_data.OSStaticData;

public class OSMenuController {
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
    public void initialize() {
        System.out.println("osdetailes instanitated");
       setOSStaticData(OSCheckHandler.getInstance().getOSStaticData());
    }
    
    private void setOSStaticData(OSStaticData data){
        bitnessLabel.setText(String.valueOf(data.bitness));
        domainNameLabel.setText(data.domainName);
        familyNameLabel.setText(data.familyName);
        totalSpaceLabel.setText(String.valueOf(data.totalSpace) + "GB");
        freeSpaceLabel.setText(String.valueOf(data.freeSpace) + "GB");
    }
}
