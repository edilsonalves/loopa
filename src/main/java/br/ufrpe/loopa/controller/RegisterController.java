package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.model.bean.Item;
import br.ufrpe.loopa.model.dao.ItemDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class RegisterController implements Initializable {

    @FXML
    private TextField tfName;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPlaceFound;

    @FXML
    private DatePicker dpDateFound;

    @FXML
    private ComboBox<String> cbPlaceLeft;

    @FXML
    private DatePicker dpDateReturned;

    @FXML
    private Label lbMessage;

    private final List<String> categories = new ArrayList<>();
    private final List<String> places = new ArrayList<>();
    private ObservableList<String> observableListCategories;
    private ObservableList<String> observableListPlaces;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dpDateFound.setValue(LocalDate.now());
        loadCbCategory();
        loadCbPlaceLeft();
    }

    @FXML
    public void loadCbCategory() {
        categories.add("Material escolar");
        categories.add("Eletrônico");
        categories.add("Outro");
        observableListCategories = FXCollections.observableList(categories);
        cbCategory.setItems(observableListCategories);
    }

    @FXML
    public void loadCbPlaceLeft() {
        places.add("Administração");
        places.add("Agronomia");
        places.add("Biologia");
        places.add("Ciência Florestal");
        places.add("Ciências Domésticas");
        places.add("Ciências Sociais");
        places.add("Computação");
        places.add("Economia");
        places.add("Educação");
        places.add("Educação Física");
        places.add("Engenharia Agrícola");
        places.add("Estatística e Informática");
        places.add("Física");
        places.add("História");
        places.add("Letras");
        places.add("Matemática");
        places.add("Medicina Veterinária");
        places.add("Morfologia e Fisiologia Animal");
        places.add("Pesca e Aquicultura");
        places.add("Química");
        places.add("Tecnologia Rural");
        places.add("Zootecnia");
        observableListPlaces = FXCollections.observableList(places);
        cbPlaceLeft.setItems(observableListPlaces);
    }

    @FXML
    public void clearFields() {
        tfName.clear();
        cbCategory.setValue("");
        tfDescription.clear();
        tfPlaceFound.clear();
        dpDateFound.setValue(LocalDate.now());
        cbPlaceLeft.setValue("");
    }

    @FXML
    public void registerItem() {
        if (isEmpty()) {
            lbMessage.setText("Campo(s) vazio(s)!");
        } else if (isInvalidDate()) {
            lbMessage.setText("Data inválida!");
        } else {
            ItemDAO itemDAO = new ItemDAO();
            Item item = new Item();
            item.setName(tfName.getText());
            item.setCategory(cbCategory.getValue());
            item.setDescription(tfDescription.getText());
            item.setPlaceFound(tfPlaceFound.getText());
            item.setDateFound(dpDateFound.getValue());
            item.setPlaceLeft(cbPlaceLeft.getValue());
            itemDAO.create(item);

            if (item.getId() != null) {
                lbMessage.setText("Objeto cadastrado: ID " + item.getId());
            } else {
                lbMessage.setText("Erro ao cadastrar objeto!");
            }
        }
    }

    public boolean isEmpty() {
        return (tfName.getText().isEmpty()
                || cbCategory.getValue().isEmpty()
                || tfDescription.getText().isEmpty()
                || tfPlaceFound.getText().isEmpty()
                || cbPlaceLeft.getValue().isEmpty());
    }

    public boolean isInvalidDate() {
        return (dpDateFound.getValue().isAfter(LocalDate.now()));
    }

}
