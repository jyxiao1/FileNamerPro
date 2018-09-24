package FXMLControllers;

import Singletons.Database;
import Types.ExperimentManager;
import Types.ExperimentType;
import Types.KeywordManager;
import Types.KeywordType;
import Utilities.ITypeObserver;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.image.DataBufferDouble;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ExperimentsTable extends ScreenController implements Initializable {

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton addKeywordsButton;

    @FXML
    private TableView<ExperimentType> experimentTableView;

    @FXML
    private TableColumn experimentName;

    @FXML
    private TableColumn experimentAbbrev;

    @FXML
    private TableColumn experimentDescription;

    private final static ObservableList<ExperimentType> experimentTypeObservableList = FXCollections.observableArrayList();

    public static ObservableList<ExperimentType> getExperimentTypeObservableList() {
        return experimentTypeObservableList;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        experimentName.setCellValueFactory(new PropertyValueFactory<Keyword, String>("experimentName"));
        experimentAbbrev.setCellValueFactory(new PropertyValueFactory<Keyword, String>("experimentAbbreviation"));
        experimentDescription.setCellValueFactory(new PropertyValueFactory<Keyword, String>("experimentDescription"));

        experimentTableView.setEditable(true);
        HashMap<String, ExperimentType> listOfExperiments = ExperimentManager.getInstance().getExperiments();
        for(Map.Entry<String, ExperimentType> entry : listOfExperiments.entrySet()) {
            experimentTypeObservableList.add(entry.getValue());
        }
        experimentTableView.setItems(experimentTypeObservableList);


    }

    @FXML
    public void handleAddExperiment (ActionEvent e) throws IOException {

        popupScreen("FXML/addNewKeywordsDB.fxml", addKeywordsButton.getScene().getWindow(),"Add Keyword DB Menu");

    }

    @FXML
    public void handleDeleteButton (ActionEvent e) throws IOException {

        ExperimentType selectedItem = experimentTableView.getSelectionModel().getSelectedItem();
        experimentTableView.getItems().remove(selectedItem);
        try {
            Database.removeExperiment(selectedItem.getLongName());
            Database.writeKeywordsToCSV("Libraries/defaultExperiments.csv");
        }catch (SQLException e1){
            e1.printStackTrace();
        }

    }

    @FXML
    public void handleCancelButton (ActionEvent e) throws IOException {

        Stage primaryStage = (Stage) cancelButton.getScene().getWindow();
        primaryStage.close();

    }

}
