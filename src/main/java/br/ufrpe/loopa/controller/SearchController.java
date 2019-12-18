package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.model.bean.Item;
import br.ufrpe.loopa.model.dao.ItemDAO;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class SearchController implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private TableView<Item> tvItems;

    @FXML
    private TableColumn<Item, String> tcCode;

    @FXML
    private TableColumn<Item, String> tcName;

    @FXML
    private TableColumn<Item, String> tcDescription;

    @FXML
    private TableColumn<Item, String> tcCategory;

    @FXML
    private TableColumn<Item, String> tcPlaceFound;

    @FXML
    private TableColumn<Item, String> tcPlaceLeft;

    @FXML
    private Label lbMessage;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void searchItem() {
        ItemDAO itemDAO = new ItemDAO();
        List<Item> items;
        ObservableList<Item> observableListItems;

        if (tfName.getText().isEmpty()) {
            items = itemDAO.findAll();
        } else {
            items = itemDAO.find(tfName.getText());
        }

        tcCode.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tcPlaceFound.setCellValueFactory(new PropertyValueFactory<>("placeFound"));
        tcPlaceLeft.setCellValueFactory(new PropertyValueFactory<>("placeLeft"));
        observableListItems = FXCollections.observableArrayList(items);
        tvItems.setItems(observableListItems);
    }

    public void removeItem() {
        Item item = tvItems.getSelectionModel().getSelectedItem();

        if (item != null) {
            ItemDAO itemDAO = new ItemDAO();
            itemDAO.delete(item);

            if (itemDAO.find(item.getId()) == null) {
                lbMessage.setText("Objeto removido com sucesso!");
            } else {
                lbMessage.setText("Erro ao remover objeto!");
            }

            searchItem();
        }
    }

}
