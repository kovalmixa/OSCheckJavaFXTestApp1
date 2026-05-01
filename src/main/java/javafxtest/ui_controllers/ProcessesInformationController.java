package javafxtest.ui_controllers;

import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafxtest.handlers.pc_check_handlers.process.ProcessCheckHandler;
import javafxtest.handlers.pc_check_handlers.process.ProcessDynamicData;
import javafxtest.handlers.pc_check_handlers.process.ProcessUnit;
import javafxtest.handlers.pc_check_handlers.IStaticData;
import javafx.scene.control.TableCell;

public class ProcessesInformationController extends PageUIController<IStaticData, ProcessDynamicData>  {
    @FXML 
    private TableView<ProcessUnit> processTable;
    @FXML
    private TableColumn<ProcessUnit, Integer> processIdColumn; 
    @FXML
    private TableColumn<ProcessUnit, String> nameColumn;
    @FXML
    private TableColumn<ProcessUnit, Double> usageColumn;

    private final ObservableList<ProcessUnit> masterData = FXCollections.observableArrayList();

    @FXML 
    public void initialize() throws Exception {
        processIdColumn.setCellValueFactory(cellData -> cellData.getValue().processIdProperty().asObject());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        usageColumn.setCellValueFactory(cellData -> cellData.getValue().usagePercProperty().asObject());
        usageColumn.setCellFactory(tc -> new TableCell<ProcessUnit, Double>() {
            private final DecimalFormat df = new DecimalFormat("0.00"); 

            @Override
            protected void updateItem(Double value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) setText(null);
                else setText(df.format(value) + "%");
            }
        });
                
        SortedList<ProcessUnit> sortedData = new SortedList<>(masterData);
        sortedData.comparatorProperty().bind(processTable.comparatorProperty());
        processTable.setItems(sortedData);

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