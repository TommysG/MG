package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainMenu {

    @FXML
    private Button play,exit,credits;
    @FXML
    private MenuButton language;
    @FXML
    private ImageView flag;


    private Image greece = new Image("Images/el.png");
    private Image uk = new Image("Images/en.png");

    public void initialize(){

    }

    public void playClicked() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = (Stage) play.getScene().getWindow();
        stage.setScene(new Scene(root,800,600));
    }

    public void settingsClicked(){

    }

    public void english(){
        flag.setImage(uk);
        language.setText("English");
    }

    public void greek(){
        flag.setImage(greece);
        language.setText("Greek");
    }

    public void creditsClicked(){

    }

    public void exitClicked() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Dialog.fxml"));
        Stage dialog = new Stage();
        dialog.setTitle("Exit");
        Scene scene = new Scene(root,400,200);
        scene.setFill(Color.TRANSPARENT);
        dialog.setScene(scene);
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(exit.getScene().getWindow());
        dialog.setResizable(false);
        dialog.initStyle(StageStyle.TRANSPARENT);

        dialog.show();

    }
}
