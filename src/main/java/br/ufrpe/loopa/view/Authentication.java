package br.ufrpe.loopa.view;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author edilson
 */
public class Authentication extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AuthenticationView.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Loopa UFRPE - Achados e Perdidos");
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Authentication.stage = stage;
    }

}
