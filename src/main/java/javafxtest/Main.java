package javafxtest;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafxtest.handlers.ResourcesHandler;

public class Main extends Application{
    public static void main(String[] args){
        Main.launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception{
        ResourcesHandler resourcesHandler = ResourcesHandler.getInstance();
        Parent root = resourcesHandler.loadFXML("/MainLayout.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("OS check App");
        stage.show();
    }
}
