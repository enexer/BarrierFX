package sample;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import sample.Service.Starter;


public class Controller implements Initializable, CallBackInterface {


    public Starter service;

    @FXML
    private Button button1;
    @FXML
    private ListView<String> listView1;
    @FXML
    private ListView<String> listView2;
    @FXML
    private TextField textFieldWord;
    @FXML
    private Slider sliderMin;
    @FXML
    private Slider sliderMax;
    @FXML
    private Label sliderLabelMin;
    @FXML
    private Label sliderLabelMax;
    @FXML
    private Button buttonFile;
    @FXML
    private TextField textFieldDivider;
    @FXML
    private  TextField textFieldParties;
    @FXML
    private  ProgressBar progressBar;


    @FXML
    private void handleButtonAction(ActionEvent event) {
        service.setWord(textFieldWord.getText());
        service.setDivider(Integer.parseInt(textFieldDivider.getText()));
        service.setParties(Integer.parseInt(textFieldParties.getText()));
        service.startS();
    }

    @FXML
    private void handleButtonFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        File file = fileChooser.showOpenDialog(buttonFile.getScene().getWindow());
        if (file != null) {
            String path = file.toPath().toString();
            System.out.println(path);
            buttonFile.setText(path);
//            file_src = path;
            service.setFile_name(path);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldWord.setText("");
        textFieldDivider.setText("1");
        textFieldParties.setText("8");

        service = new Starter(this, 0, 100, textFieldWord.getText(), 1, 8, "dane.txt");

        // Sliders
        sliderMin.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int minVal = newValue.intValue();
                service.setMin(minVal);
                sliderLabelMin.setText("Min " + String.valueOf(newValue.intValue()));
            }
        });

        sliderMax.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int maxVal = newValue.intValue();
                service.setMax(maxVal);
                sliderLabelMax.setText("Max " + String.valueOf(newValue.intValue()));
            }
        });

    }

    @Override
    public void updateView(String val) {
        Platform.runLater(() -> {
            listView1.getItems().add(val);
            listView1.scrollTo(listView1.getItems().size() - 1);
            progressBar.setProgress(0);
        });
    }

    @Override
    public void updateView(String val, double val2) {
        Platform.runLater(() -> {
            listView1.getItems().add(val);
            listView1.scrollTo(listView1.getItems().size() - 1);
            progressBar.setProgress(val2);
        });
    }

    @Override
    public void updateView2(String val) {
        Platform.runLater(() -> {
            listView2.getItems().add(val);
            listView2.scrollTo(listView2.getItems().size() - 1);
        });
    }
}
