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
    public void initialize() {
        System.out.println("osdetailes instanitated");
       setOSStaticData(OSCheckHandler.getInstance().getOSStaticData());
    }
    
    private void setOSStaticData(OSStaticData data){
        bitnessLabel.setText(String.valueOf(data.bitness));
        domainNameLabel.setText(data.domainName);
        familyNameLabel.setText(data.familyName);
    }
}
