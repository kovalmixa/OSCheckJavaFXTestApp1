package javafxtest.UIControllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafxtest.ResourcesHandler;

public class MainLayoutController {
    @FXML
    private AnchorPane contentArea;

    @FXML
    public void initialize() {
        showOSDetails();
    }

    @FXML
    private void showOSDetails() {
        ResourcesHandler resourcesHandler = ResourcesHandler.getInstance();
        Parent root = resourcesHandler.loadFXML("OSDetails.fxml");
        resourcesHandler.setupRootToAnchorPane(contentArea, root);
    }

    @FXML
    private void showCPUDetails() {
        ResourcesHandler resourcesHandler = ResourcesHandler.getInstance();
        Parent root = resourcesHandler.loadFXML("CPUDetails.fxml");
        resourcesHandler.setupRootToAnchorPane(contentArea, root);
    }

    @FXML
    private void handleSwitchButtonClick() {
        System.out.println("Checking OS...");
    }
}