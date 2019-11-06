package org.edb.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;

public class mainController {

    @FXML
    private Label lbl1;

    @FXML
    private Label lbl2;
    @FXML
    private Label lbl3;
    @FXML
    private Label lbl4;

    @FXML
    private Button loginViewbutton;


    private String token = Authorization.getToken();






    public void loginView(ActionEvent event) throws Exception{

        //화면 이동 가장 쉬운 방법은 stage에서 새로운 scene세팅하는것

        try {

            Parent login = FXMLLoader.load(getClass().getResource("../../../fxml/login.fxml"));
            Scene scene = new Scene(login);
            Stage primaryStage = (Stage)loginViewbutton.getScene().getWindow();//현재 윈도우 가져오기

            primaryStage.setScene(scene);


        }catch(IOException e){
            e.printStackTrace();
        }
    }




}
