package FXMLControllers;

import Singletons.Config;
import Types.Filename;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CompactNamer extends Namer implements Initializable {

    @FXML
    private DatePicker experimentDate;

    @FXML
    private TextField trialNumber;

    @FXML TextField sampleNumber;

    @FXML
    private JFXToggleButton switchNamers;

    @FXML
    private JFXButton closeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){

        String pattern = "dd/MM/yyyy";
        experimentDate.setPromptText(pattern.toLowerCase());
        experimentDate.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        experimentDate.setValue(LocalDate.now());

        if(trialNumber.getText().equals("-1")){
            trialNumber.setText("0");
        }
        else{
            trialNumber.setText(String.valueOf(sharedFilename.getTrialNumber()));
        }

        if(sampleNumber.getText()=="-1"){
            sampleNumber.setText("0");
        }
        else{
            sampleNumber.setText(String.valueOf(sharedFilename.getSampleNumber()));
        }


        trialNumber.textProperty().addListener((obs, oldTrialNumber, newTrialNumber) ->
        {
            Config.getInstance().setProperty("trialNumber",newTrialNumber);
            sharedFilename.setTrialNumber(Integer.parseInt(newTrialNumber));
        });

        sampleNumber.textProperty().addListener((obs, oldSampleNumber, newSampleNumber) ->
        {
            Config.getInstance().setProperty("sampleNumber",newSampleNumber);
            sharedFilename.setSampleNumber(Integer.parseInt(newSampleNumber));
        });

        trialNumberCheckbox.selectedProperty().addListener((obs, oldIsSelected, newIsSelected) -> {
            trialNumber.setDisable(!newIsSelected);
            if(!newIsSelected)
            {
                sharedFilename.setTrialNumber(-1);
            }else{
                sharedFilename.setTrialNumber(Integer.parseInt(trialNumber.getText()));
            }
        });

        sampleNumberCheckbox.selectedProperty().addListener((obs, oldIsSelected, newIsSelected) -> {
            sampleNumber.setDisable(!newIsSelected);
            if(!newIsSelected)
            {
                sharedFilename.setSampleNumber(-1);
            }else{
                sharedFilename.setSampleNumber(Integer.parseInt(sampleNumber.getText()));
            }
        });



    }

    @FXML
    public void copyFileToClipboard(ActionEvent e) {
        String nameToCopy = updateName(sharedFilename);
        StringSelection stringSelection = new StringSelection(nameToCopy);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @FXML
    public void incrementSampleNumber(ActionEvent e) {
        int currSample = Integer.parseInt(sampleNumber.getText());
        currSample++;
        sampleNumber.setText(String.valueOf(currSample));
    }

    @FXML
    public void incrementTrialNumber(ActionEvent e) throws IOException{
        int currTrial = Integer.parseInt(trialNumber.getText());
        currTrial++;
        trialNumber.setText(String.valueOf(currTrial));
    }

    @FXML
    public void decrementSampleNumber(ActionEvent e) throws IOException{
        int currSample = Integer.parseInt(sampleNumber.getText());
        if (currSample >= 1)
        {
            currSample--;
            sampleNumber.setText(String.valueOf(currSample));
        }
    }

    @FXML
    public void decrementTrialNumber(ActionEvent e) throws IOException{
        int currTrial = Integer.parseInt(trialNumber.getText());
        if (currTrial >= 1)
        {
            currTrial--;
            trialNumber.setText(String.valueOf(currTrial));
        }
    }

    @FXML
    public void handleToggleButton (ActionEvent e) throws IOException {
        Stage primaryStage = (Stage) switchNamers.getScene().getWindow();
        primaryStage.close();
        popupScreen("FXML/fullNamer.fxml", switchNamers.getScene().getWindow());
    }

    @FXML
    public void closeCompactNamer(ActionEvent e) {
        closeProgram(closeButton);
    }
}
