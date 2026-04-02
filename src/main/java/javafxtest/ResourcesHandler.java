package javafxtest;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class ResourcesHandler {
    private static ResourcesHandler instance;

    private ResourcesHandler() {}

    public static ResourcesHandler getInstance() {
        if (instance == null) {
            instance = new ResourcesHandler();
        }
        return instance;
    }

   public Parent loadFXML(String fxmlFile) {
        try {
            String path = fxmlFile.startsWith("/") ? fxmlFile : "/" + fxmlFile;
            var resource = getClass().getResource(path);
            if (resource == null) {
                throw new IllegalArgumentException("Error: File not found at path: " + path);
            }
            FXMLLoader loader = new FXMLLoader(resource);
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setupRootToAnchorPane(AnchorPane contentArea, Parent root) {
        contentArea.getChildren().setAll(root);
        AnchorPane.setTopAnchor(root, 0.0);
        AnchorPane.setBottomAnchor(root, 0.0);
        AnchorPane.setLeftAnchor(root, 0.0);
        AnchorPane.setRightAnchor(root, 0.0);
    }
}
