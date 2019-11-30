package main.lab.lab10FileEncryption.src.sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;
import main.decryptor.Decryptor;
import main.decryptor.FrequencyAnalysisDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.ceasar.CeasarEncryptor;
import main.util.CryptographyConstants;
import main.util.FileOperationsHelper;

import java.io.File;

public class Controller {

    @FXML
    private RadioButton rbEncrypt;

    @FXML
    private RadioButton rbDecrypt;

    @FXML
    private Button btnOpenFile;

    @FXML
    private TextField tfKey;

    @FXML
    private TextArea taResultPreview;

    @FXML
    private Button btnSave;

    @FXML
    private Label labelFileName;

    private ToggleGroup tgOperationType = new ToggleGroup();

    private String text = CryptographyConstants.EMPTY_STRING;

    @FXML
    private void initialize() {
        labelFileName.setText(CryptographyConstants.EMPTY_STRING);
        rbDecrypt.setToggleGroup(tgOperationType);
        rbEncrypt.setToggleGroup(tgOperationType);
        rbEncrypt.setSelected(true);
        tgOperationType.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
                if (rbDecrypt.isSelected()) {
                    tfKey.setText(CryptographyConstants.EMPTY_STRING);
                    tfKey.setDisable(true);
                } else if (rbEncrypt.isSelected()) {
                    tfKey.setDisable(false);
                }

            }
        });
    }

    @FXML
    private void onClickBtnOpenFile(ActionEvent event) {
        File file = openTextFile();
        if (file != null) {
            labelFileName.setText(file.getName());
            text = FileOperationsHelper.readFile(file);
            if (rbEncrypt.isSelected()) {
                int key = Integer.parseInt(tfKey.getText());
                Encryptor encryptor = new CeasarEncryptor(key);
                text = encryptor.encrypt(text);
            } else if (rbDecrypt.isSelected()) {
                Decryptor decryptor = new FrequencyAnalysisDecryptor();
                text = decryptor.decrypt(text);
            }
            taResultPreview.setText(text);
        }
    }

    @FXML
    private void onClickBtnSave(ActionEvent event) {
        File file = openTextFile();
        if (file != null) {
            FileOperationsHelper.writeToFile(file, text);
            labelFileName.setText(CryptographyConstants.EMPTY_STRING);
            taResultPreview.setText(CryptographyConstants.EMPTY_STRING);
        }
    }

    private File openTextFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));
        return fileChooser.showOpenDialog(btnOpenFile.getScene().getWindow());
    }
}
