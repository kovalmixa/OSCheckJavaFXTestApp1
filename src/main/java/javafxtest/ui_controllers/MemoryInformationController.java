package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafxtest.handlers.pc_check_handlers.memory.*;
import javafxtest.handlers.pc_check_handlers.IStaticData;

public class MemoryInformationController extends PageUIController<IStaticData, MemoryDynamicData>{
    @FXML 
    private ProgressBar memoryUsageProgressBar;
    @FXML 
    private Label spaceUsageLabel;
    @FXML 
    private Label totalSpaceLabel;
    @FXML 
    private Label freeSpaceLabel;

    private static int GYGABYTE = (int)Math.pow(2, 30); 

    @FXML 
    public void initialize() throws Exception {
        setupPageDynamicSetter(() -> {
            try {
                return MemoryCheckHandler.getInstance().getDynamicData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override 
    protected void setStaticData(IStaticData data) {

    }

    @Override 
    protected void setDynamicData(MemoryDynamicData data){
        totalSpaceLabel.setText(String.valueOf(data.totalSpace) + "B");
        freeSpaceLabel.setText(String.valueOf(data.freeSpace) + "B");
        float usagePercentage =  (float)data.freeSpace / data.totalSpace;
        memoryUsageProgressBar.setProgress(usagePercentage);
        spaceUsageLabel.setText(String.format("%.2f", usagePercentage * 100) + "%");
    }
}
