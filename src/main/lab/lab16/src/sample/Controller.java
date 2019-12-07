package main.lab.lab16.src.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.util.CryptographyConstants;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea taClients;

    @FXML
    private TextField tfClientName;

    @FXML
    private TextField tfClientKey;

    @FXML
    private Button btnAddClient;

    @FXML
    private TextArea taLog;

    @FXML
    private TextField tfMessage;

    @FXML
    private TextField tfSenderId;

    @FXML
    private TextField tfReceiverId;

    @FXML
    private Button btnSend;

    private SignatureCenter signatureCenter = new SignatureCenter();

    @FXML
    void initialize() {
        signatureCenter.addClient("client0", 10);
        signatureCenter.addClient("client1", 31);
        refreshClientsInfo();
    }

    @FXML
    void onClickBtnAddClient(ActionEvent event) {
        String clientName = tfClientName.getText();
        int clientKey = Integer.valueOf(tfClientKey.getText());
        signatureCenter.addClient(clientName, clientKey);
        refreshClientsInfo();
        tfClientName.setText(CryptographyConstants.EMPTY_STRING);
        tfClientKey.setText(CryptographyConstants.EMPTY_STRING);
    }

    @FXML
    void onClickBtnSend(ActionEvent event) {
        String message = tfMessage.getText();
        String senderId = tfSenderId.getText();
        String receiverId = tfReceiverId.getText();
        String log = signatureCenter.doSignatureOperation(message, senderId, receiverId);
        taLog.setText(log);
    }

    private void refreshClientsInfo() {
        taClients.setText(signatureCenter.getClientsInfo());
    }
}
