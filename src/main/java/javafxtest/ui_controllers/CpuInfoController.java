package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafxtest.handlers.pc_check_handlers.cpu.CpuStaticData;
import javafxtest.handlers.pc_check_handlers.IDynamicData;
import javafxtest.handlers.pc_check_handlers.cpu.CpuCheckHandler;

public class CpuInfoController extends PageUIController<CpuStaticData, IDynamicData>  {
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
        setStaticData(CpuCheckHandler.getInstance().getStaticData());
    }

    @Override 
    protected void setStaticData(CpuStaticData data){
        cpuNameLabel.setText(data.cpuName);
        cpuPhCoreLabel.setText(String.valueOf(data.physicalCores));
        cpuLgCoreLabel.setText(String.valueOf(data.logicalCores));
    }

    @Override 
    protected void setDynamicData(IDynamicData data) {

    }
}