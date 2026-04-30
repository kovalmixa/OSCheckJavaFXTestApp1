package javafxtest.ui_controllers;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafxtest.handlers.pc_check_handlers.process.ProcessCheckHandler;
import javafxtest.handlers.pc_check_handlers.process.ProcessDynamicData;
import javafxtest.handlers.pc_check_handlers.process.ProcessUnit;
import javafxtest.handlers.pc_check_handlers.IStaticData;

public class ProcessesInformationController extends PageUIController<IStaticData, ProcessDynamicData>  {
    @FXML 
    private TableView<ProcessUnit> processTable;
    private final ObservableList<ProcessUnit> masterData = FXCollections.observableArrayList();
    @FXML 
    public void initialize() throws Exception {
        TableColumn<ProcessUnit, Integer> idCol = new TableColumn<>("ProcessID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("processId")); 
        TableColumn<ProcessUnit, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<ProcessUnit, Double> cpuCol = new TableColumn<>("Usage %");
        cpuCol.setCellValueFactory(new PropertyValueFactory<>("usagePerc"));
        processTable.setItems(masterData);

        setupPageDynamicSetter(() -> {
            try {
                return (ProcessDynamicData)ProcessCheckHandler.getInstance().getDynamicData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @Override 
    protected void setStaticData(IStaticData data){

    }

    @Override 
    protected void setDynamicData(ProcessDynamicData data) {
        if (data != null && data.processes != null) {
            masterData.setAll(data.processes);
        }
    }
}