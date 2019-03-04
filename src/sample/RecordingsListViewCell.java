package sample;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class RecordingsListViewCell extends ListCell<Recording> {

    @FXML
    private JFXTextArea label1;

    @FXML
    private JFXTextArea label2;

    @FXML
    private GridPane gridPane;

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Recording recording, boolean empty) {
        super.updateItem(recording, empty);

        if(empty || recording == null) {
            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("ListCell.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            label1.setText(String.valueOf(recording.getTitle()));
            label2.setText(String.valueOf(recording.getTitle()));

            setText(null);
            setGraphic(gridPane);
        }

    }
}