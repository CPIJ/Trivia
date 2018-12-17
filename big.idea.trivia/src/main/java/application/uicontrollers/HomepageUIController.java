package application.uicontrollers;

import application.Application;
import game.model.Game;
import game.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import question.model.Enums.Difficulty;

import java.io.IOException;
import java.util.Optional;

public class HomepageUIController {

    public Button btn_singleplayer;

    private Game game;
    private Application application;

    public HomepageUIController() {

    }

    public void initialize(){
        application = Application.getInstance();
    }

    @FXML
    public void btnSinglePlayerClicked(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Difficulty");
        alert.setContentText("Choose a difficulty for the game.");

        ButtonType easy = new ButtonType("Easy");
        ButtonType medium = new ButtonType("Medium");
        ButtonType hard = new ButtonType("Hard");

        alert.getButtonTypes().setAll(easy, medium, hard);

        game = Game.getInstance();

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == easy) {
            game.setDifficulty(Difficulty.EASY);
        }
        else if (result.get() == medium) {
            game.setDifficulty(Difficulty.MEDIUM);
        }
        else if (result.get() == hard) {
            game.setDifficulty(Difficulty.HARD);
        }

        application.openStage("category_ui.fxml");

        Player player = new Player(Application.currentUser.getId(),0,0);
        game.addPlayer(player);

        Stage stageToClose = (Stage) btn_singleplayer.getScene().getWindow();
        stageToClose.close();
    }

    public void btnMultiplayerGameClicked(ActionEvent actionEvent) throws IOException {
        application.openStage("lobby_ui.fxml");

        Stage stageToClose = (Stage) btn_singleplayer.getScene().getWindow();
        stageToClose.close();
    }

    public void btnLeaderBoardClicked(ActionEvent actionEvent) {
    }

    public void btnLogoutClicked(ActionEvent actionEvent) throws IOException {
        Application.currentUser = null;

        application.openStage("login_ui.fxml");

        Stage stageToClose = (Stage) btn_singleplayer.getScene().getWindow();
        stageToClose.close();
    }
}
