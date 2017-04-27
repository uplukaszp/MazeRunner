package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MutationController {

    @FXML private TextField seqLen;
    @FXML private TextField amount;
    @FXML private TextField mutation;
    @FXML private Button btStart;
    @FXML private Button btPause;
    @FXML private Button btSave;
    @FXML private Button btReset;

    @FXML
    void PauseAction(ActionEvent event) {
    	System.out.println("ggg");
    	System.exit(0);
    }

    @FXML
    void ResetAction(ActionEvent event) {
    	System.out.println("ggg");
    	System.exit(0);
    }

    @FXML
    void SaveAction(ActionEvent event) {
    	System.out.println("ggg");
    	System.exit(0);
    }

    @FXML
    void StartAction(ActionEvent event) {
    	System.out.println("ggg");
    	System.exit(0);
    }
}
