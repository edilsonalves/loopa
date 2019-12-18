package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.model.bean.Item;
import br.ufrpe.loopa.model.bean.Person;
import br.ufrpe.loopa.model.dao.ItemDAO;
import br.ufrpe.loopa.model.dao.PersonDAO;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class DevolutionController implements Initializable {

    @FXML
    private TextField tfItemName;

    @FXML
    private TextField tfCategory;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfPlaceFound;

    @FXML
    private DatePicker dpDateFound;

    @FXML
    private TextField tfPlaceLeft;

    @FXML
    private DatePicker dpDateReturned;

    @FXML
    private TextField tfId;

    @FXML
    private Label lbItemMessage;

    @FXML
    private TextField tfPersonName;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfAddress;

    @FXML
    private TextField tfNeighborhood;

    @FXML
    private TextField tfCity;

    @FXML
    private TextField tfTelephone;

    @FXML
    private TextField tfRg;

    @FXML
    private TextField tfCpf;

    @FXML
    private Label lbPersonMessage;

    private ItemDAO itemDAO = new ItemDAO();
    private Item item = new Item();
    private PersonDAO personDAO = new PersonDAO();
    private Person person = new Person();

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
        if (isEmpty(1)) {
            lbItemMessage.setText("Campo vazio!");
        } else {
            itemDAO = new ItemDAO();
            item = itemDAO.find(Long.valueOf(tfId.getText()));

            if (item == null) {
                lbItemMessage.setText("Objeto não encontrado!");
            } else {
                tfItemName.setText(item.getName());
                tfCategory.setText(item.getCategory());
                tfDescription.setText(item.getDescription());
                tfPlaceFound.setText(item.getPlaceFound());
                dpDateFound.setValue(item.getDateFound());
                tfPlaceLeft.setText(item.getPlaceLeft());

                if (item.getPerson() == null) {
                    dpDateReturned.setValue(LocalDate.now());
                    enableFields();
                } else {
                    person = personDAO.find(item.getPerson().getId());
                    tfPersonName.setText(person.getName());
                    tfEmail.setText(person.getEmail());
                    tfAddress.setText(person.getAddress());
                    tfNeighborhood.setText(person.getNeighborhood());
                    tfCity.setText(person.getCity());
                    tfTelephone.setText(person.getTelephone());
                    tfRg.setText(person.getRg());
                    tfCpf.setText(person.getCpf());
                }
            }
        }
    }

    @FXML
    public void registerPerson() {
        if (isEmpty(2)) {
            lbPersonMessage.setText("Campo(s) vazio(s)!");
        } else {
            personDAO = new PersonDAO();
            person = new Person();
            person.setName(tfPersonName.getText());
            person.setEmail(tfEmail.getText());
            person.setAddress(tfAddress.getText());
            person.setNeighborhood(tfNeighborhood.getText());
            person.setCity(tfCity.getText());
            person.setTelephone(tfTelephone.getText());
            person.setRg(tfRg.getText());
            person.setCpf(tfCpf.getText());
            personDAO.create(person);
            item.setDateReturned(LocalDate.now());
            item.setPerson(person);
            itemDAO.update(item);
        }
    }

    public void enableFields() {
        tfPersonName.setDisable(false);
        tfEmail.setDisable(false);
        tfAddress.setDisable(false);
        tfNeighborhood.setDisable(false);
        tfCity.setDisable(false);
        tfTelephone.setDisable(false);
        tfRg.setDisable(false);
        tfCpf.setDisable(false);
    }

    public boolean isEmpty(int flag) {
        boolean result;

        if (flag == 1) {
            result = tfId.getText().isEmpty();
        } else {
            result = tfPersonName.getText().isEmpty()
                    || tfEmail.getText().isEmpty()
                    || tfAddress.getText().isEmpty()
                    || tfNeighborhood.getText().isEmpty()
                    || tfCity.getText().isEmpty()
                    || tfTelephone.getText().isEmpty()
                    || tfRg.getText().isEmpty()
                    || tfCpf.getText().isEmpty();
        }

        return result;
    }

    @FXML
    public void clearFields() {
        tfPersonName.clear();
        tfEmail.clear();
        tfAddress.clear();
        tfNeighborhood.clear();
        tfCity.clear();
        tfTelephone.clear();
        tfRg.clear();
        tfCpf.clear();
    }

}
