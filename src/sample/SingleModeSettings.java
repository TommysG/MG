package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class SingleModeSettings {

    @FXML
    private Button start,backButton,multiplayer;
    @FXML
    private CheckBox normal,doublesize,trio,red,black;
    private GameMode mode;
    private double width,height;
    private Image theme;
    @FXML
    private ImageView redImage,blackImage,normalMode,doubleMode,trioMode;

    private Properties properties = new Properties();
    private OutputStream output = null;
    private InputStream input = null;
    @FXML
    private Label gamemodeLabel,themesLabel;

    private boolean fullScreen;

    private ResourceBundle bundle;
    private Locale locale;
    private Glow glow = new Glow(0.6);


    public SingleModeSettings(){
        mode = new GameMode();
    }
    @FXML
    public void initialize() throws IOException{
        File f = new File("config.properties");

        if(f.exists()) {
            input = new FileInputStream("config.properties");
            properties.load(input);

            String lang = properties.getProperty("flag");
            loadLang(lang);

            width = Double.parseDouble(properties.getProperty("width"));
            height = Double.parseDouble(properties.getProperty("height"));
            fullScreen = Boolean.parseBoolean(properties.getProperty("fullScreen"));

            if(width == 800 || fullScreen || width == 1280){
                redImage.setScaleX(1.5);
                redImage.setScaleY(1.5);
                blackImage.setScaleX(1.5);
                blackImage.setScaleY(1.5);
                normalMode.setScaleX(2);
                normalMode.setScaleY(2);
                doubleMode.setScaleX(2);
                doubleMode.setScaleY(2);
                trioMode.setScaleX(2);
                trioMode.setScaleY(2);
            }
            if(fullScreen){
                redImage.setScaleX(2);
                redImage.setScaleY(2);
                blackImage.setScaleX(2);
                blackImage.setScaleY(2);
            }
        }
        normal.setSelected(true);
        red.setSelected(true);
        normalMode.setEffect(glow);
        redImage.setEffect(glow);
    }
    @FXML
    public void startClicked() throws IOException {
        mode.setGlobalMode("SingleMode");
        if(normal.isSelected()) {
            mode.setMode(1);
        }
        if(doublesize.isSelected()) {
            mode.setMode(2);
        }
        if(trio.isSelected()) {
            mode.setMode(3);
        }
        if(red.isSelected())
            theme = new Image("Images/Cards/backgroundSmall.png");
        if(black.isSelected())
            theme = new Image("Images/Cards/background1Small.png");


        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("Game.fxml"));
        Loader.load();
        Stage stage = (Stage) start.getScene().getWindow();
        Game game = Loader.getController();
        game.setMode(mode,theme);
        game.gameStart();
        stage.getScene().setRoot(Loader.getRoot());


    }


    @FXML
    public void backButtonClicked() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.getScene().setRoot(root);
    }

    @FXML
    public void normalModeClicked(){
        normalMode.setEffect(glow);
        doubleMode.setEffect(null);
        trioMode.setEffect(null);
        normal.setSelected(true);
        doublesize.setSelected(false);
        trio.setSelected(false);
    }
    @FXML
    public void doubleModeClicked(){
        normalMode.setEffect(null);
        trioMode.setEffect(null);
        doubleMode.setEffect(glow);
        normal.setSelected(false);
        doublesize.setSelected(true);
        trio.setSelected(false);
    }
    @FXML
    public void trioModeClicked(){
        trioMode.setEffect(glow);
        normalMode.setEffect(null);
        doubleMode.setEffect(null);
        normal.setSelected(false);
        doublesize.setSelected(false);
        trio.setSelected(true);
    }
    @FXML
    public void redClicked(){
        redImage.setEffect(glow);
        blackImage.setEffect(null);
        red.setSelected(true);
        black.setSelected(false);
    }
    @FXML
    public void blackClicked(){
        redImage.setEffect(null);
        blackImage.setEffect(glow);
        red.setSelected(false);
        black.setSelected(true);
    }
    @FXML
    public void redCheckClicked(){
        red.setSelected(true);
        black.setSelected(false);
    }
    @FXML
    public void blackCheckClicked(){
        red.setSelected(false);
        black.setSelected(true);
    }
    @FXML
    public void normalClicked(){
        normal.setSelected(true);
        doublesize.setSelected(false);
        trio.setSelected(false);
    }
    @FXML
    public void doubleClicked(){
        normal.setSelected(false);
        doublesize.setSelected(true);
        trio.setSelected(false);
    }
    @FXML
    public void trioClicked(){
        normal.setSelected(false);
        doublesize.setSelected(false);
        trio.setSelected(true);
    }

    private void loadLang(String lang) {
        locale = new Locale(lang);
        bundle = ResourceBundle.getBundle("sample.lang",locale);

        gamemodeLabel.setText(bundle.getString("gameMode"));
        themesLabel.setText(bundle.getString("themes"));
        normal.setText(bundle.getString("normal"));
        doublesize.setText(bundle.getString("doubleSize"));
        trio.setText(bundle.getString("trio"));
        red.setText(bundle.getString("red"));
        black.setText(bundle.getString("black"));
        start.setText(bundle.getString("singleMode"));


    }

}
