package cssystem;

import cssystem.Controllers.LoadingSceneController;
import cssystem.backend.Configuration;
import cssystem.backend.DataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLFiles/main.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXMLFiles/login.fxml"));
        Parent root = (Parent) loader.load();
        Parent root2 = (Parent) loader2.load();

        Stage loginStage = new Stage();

        Scene loadingScene = new Scene(root);
        Scene loginScene = new Scene(root2);
        primaryStage.setScene(loadingScene);
        loginStage.setScene(loginScene);

        LoadingSceneController loadingSceneController = new LoadingSceneController();

        primaryStage.initStyle(StageStyle.UNDECORATED);
        loginStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        new Thread(() -> {
            for (int i = 0; i <= 2; i++) {
                try {
                    Thread.sleep(750);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final double progress = i * 0.1;
                loadingSceneController.updateProgress(progress);

            }
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    primaryStage.close();
                    Platform.setImplicitExit(false);
                    loginStage.show();

                }
            });
        }).start();
    }


    public static void main(String[] args) {
        // Configuring the application
        Configuration configuration = Configuration.getInstance();
        configuration.configure();

        DataSource dataSource = DataSource.getInstance();
        // Launching the application
        launch(args);
    }
}