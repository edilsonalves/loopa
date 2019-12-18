package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.model.bean.Item;
import br.ufrpe.loopa.model.dao.ItemDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class HomeController implements Initializable {

    @FXML
    private Label lbTotal;

    @FXML
    private Label lbLost;

    @FXML
    private Label lbReturned;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateCounters();
    }

    public void updateCounters() {
        ItemDAO itemDAO = new ItemDAO();
        List<Item> items = itemDAO.findAll();
        int total = items.size();
        int lost = 0;
        int returned = 0;

        for (Item item : items) {
            if (item.getPerson() == null) {
                lost++;
            } else {
                returned++;
            }
        }

        lbTotal.setText(String.valueOf(total));
        lbLost.setText(String.valueOf(lost));
        lbReturned.setText(String.valueOf(returned));
    }

}
