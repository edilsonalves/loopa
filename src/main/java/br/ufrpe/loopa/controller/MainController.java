package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.view.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class MainController implements Initializable {

    @FXML
    private AnchorPane apContainer;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadHomeView();
    }

    @FXML
    private void loadHomeView() {
        AnchorPane ap = null;
        apContainer.getChildren().clear();

        try {
            ap = FXMLLoader.load(getClass().getResource("/fxml/HomeView.fxml"));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        apContainer.getChildren().addAll(ap);
    }

    @FXML
    private void loadRegisterView() {
        AnchorPane ap = null;
        apContainer.getChildren().clear();

        try {
            ap = FXMLLoader.load(getClass().getResource("/fxml/RegisterView.fxml"));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        apContainer.getChildren().addAll(ap);
    }

    @FXML
    private void loadSearchView() {
        AnchorPane ap = null;
        apContainer.getChildren().clear();

        try {
            ap = FXMLLoader.load(getClass().getResource("/fxml/SearchView.fxml"));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        apContainer.getChildren().addAll(ap);
    }

    @FXML
    private void loadDevolutionView() {
        AnchorPane ap = null;
        apContainer.getChildren().clear();

        try {
            ap = FXMLLoader.load(getClass().getResource("/fxml/DevolutionView.fxml"));
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        apContainer.getChildren().addAll(ap);
    }

    @FXML
    public void exit() {
        Main.getStage().close();
    }

}
