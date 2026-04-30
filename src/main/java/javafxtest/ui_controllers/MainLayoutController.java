package javafxtest.ui_controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafxtest.handlers.ResourcesHandler;

public class MainLayoutController {
    @FXML 
    private AnchorPane contentArea;

    @FXML 
    public void initialize() {
        showOSInformation();
    }

    @FXML 
    private void showOSInformation() {
        openTab("OSInformation.fxml");
    }

    @FXML 
    private void showCpuInformation() {
        openTab("CpuInformation.fxml");
    }

    @FXML 
    private void showCpuLoad() {
        openTab("CpuLoad.fxml");
    }
    
    @FXML 
    private void showMemoryInformation() {
        openTab("MemoryInformation.fxml");
    }

    @FXML
    private void showProcessesInformation(){
        openTab("ProcessesInformation.fxml");
    }

    public void openTab(String fxmlFile) {
        ResourcesHandler resourcesHandler = ResourcesHandler.getInstance();
        Parent root = resourcesHandler.loadFXML(fxmlFile);
        resourcesHandler.setupRootToAnchorPane(contentArea, root);
    }

    @FXML 
    private void handleSwitchButtonClick() {
        System.out.println("Checking OS...");
    }
}