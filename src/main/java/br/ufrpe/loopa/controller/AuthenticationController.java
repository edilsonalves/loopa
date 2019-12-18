package br.ufrpe.loopa.controller;

import br.ufrpe.loopa.model.bean.Employee;
import br.ufrpe.loopa.model.dao.EmployeeDAO;
import br.ufrpe.loopa.view.Authentication;
import br.ufrpe.loopa.view.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author edilson
 */
public class AuthenticationController implements Initializable {

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

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

    public void exit() {
        Authentication.getStage().close();
    }

    public void enter() {
        if (tfEmail.getText().isEmpty() || tfPassword.getText().isEmpty()) {
            lbMessage.setText("Campo(s) vazio(s)!");
        } else {
            EmployeeDAO employeeDAO = new EmployeeDAO();
            Employee employee = employeeDAO.find(tfEmail.getText());

            if (employee == null || !employee.getPassword().equals(tfPassword.getText())) {
                lbMessage.setText("Dado(s) inválido(s)!");
            } else {
                try {
                    exit();
                    Main main = new Main();
                    main.start(new Stage());
                } catch (IOException e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            }
        }
    }

}
