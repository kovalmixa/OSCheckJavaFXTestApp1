package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafxtest.handlers.pc_check_handlers.cpu.CpuDynamicData;
import javafxtest.handlers.pc_check_handlers.IStaticData;
import javafxtest.handlers.pc_check_handlers.cpu.CpuCheckHandler;

public class CpuLoadController extends PageUIController<IStaticData, CpuDynamicData>{
    @FXML 
    private Label cpuUsagePercLabel;
    @FXML 
    private ProgressBar cpuUsageProgressBar;

    @FXML 
    public void initialize() throws Exception {
        setupPageDynamicSetter(() -> {
            try {
                return CpuCheckHandler.getInstance().getDynamicData();
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
    protected void setDynamicData(CpuDynamicData data){
        cpuUsageProgressBar.setProgress(data.cpuLoadTicksPerc / 100);
        cpuUsagePercLabel.setText(String.format("%.2f", data.cpuLoadTicksPerc) + "%");
    }
}
