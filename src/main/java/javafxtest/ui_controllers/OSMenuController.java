package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafxtest.handlers.pc_check_handlers.os.*;
import javafxtest.handlers.pc_check_handlers.os.OSCheckHandler;
import javafxtest.handlers.pc_check_handlers.IDynamicData;

public class OSMenuController extends PageUIController<OSStaticData, IDynamicData> {
    @FXML 
    private Label bitnessLabel;
    @FXML 
    private Label domainNameLabel;
    @FXML 
    private Label familyNameLabel;

    @FXML 
    public void initialize() {
        setStaticData(OSCheckHandler.getInstance().getStaticData());
    }

    @Override 
    protected void setStaticData(OSStaticData data){
        bitnessLabel.setText(String.valueOf(data.bitness));
        domainNameLabel.setText(data.domainName);
        familyNameLabel.setText(data.familyName);
    }

    @Override 
    protected void setDynamicData(IDynamicData dynamicData){

    } 
}
