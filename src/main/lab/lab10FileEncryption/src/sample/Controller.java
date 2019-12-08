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
import main.decryptor.GammaDecryptor;
import main.encryptor.Encryptor;
import main.encryptor.GammaEncryptor;
import main.encryptor.ceasar.CeasarEncryptor;
import main.util.CryptographyConstants;
import main.util.FileOperationsHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    private RadioButton rbEncrypt;

    @FXML
    private RadioButton rbDecrypt;

    @FXML
    private Button btnOpenFile;

    @FXML
    private TextField tfKey1;

    @FXML
    private TextField tfKey2;

    @FXML
    private TextField tfKey3;

    @FXML
    private TextArea taResultPreview;

    @FXML
    private Button btnSave;

    @FXML
    private Label labelFileName;

    private ToggleGroup tgOperationType = new ToggleGroup();

    private String text = CryptographyConstants.EMPTY_STRING;

    private List<Integer> gamma = new ArrayList<>();

    @FXML
    private void initialize() {
        labelFileName.setText(CryptographyConstants.EMPTY_STRING);
        rbDecrypt.setToggleGroup(tgOperationType);
        rbEncrypt.setToggleGroup(tgOperationType);
        rbEncrypt.setSelected(true);
    }

    @FXML
    private void onClickBtnOpenFile(ActionEvent event) {
        File file = openTextFile();
        if (file != null) {
            labelFileName.setText(file.getName());
            text = FileOperationsHelper.readFile(file);
            if (rbEncrypt.isSelected()) {
                Integer key1 = Integer.parseInt(tfKey1.getText());
                Integer key2 = Integer.parseInt(tfKey2.getText());
                Integer key3 = Integer.parseInt(tfKey3.getText());
                List<Integer> key = new ArrayList<>(Arrays.asList(key1, key2, key3));
                GammaEncryptor encryptor = new GammaEncryptor(key);
                text = encryptor.encrypt(text);
                this.gamma = encryptor.getGamma();
            } else if (rbDecrypt.isSelected() && gamma.size() > 0) {
                GammaDecryptor decryptor = new GammaDecryptor(gamma);
                text = text.replace("\n", "").replace("\r", "");
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
