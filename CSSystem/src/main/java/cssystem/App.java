package cssystem;

import cssystem.Controllers.LoadingSceneController;
import cssystem.Controllers.MenuSceneController;
import cssystem.backend.Configuration;
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
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/FXMLFiles/menu.fxml"));
        Parent root = (Parent) loader.load();
        Parent root2 = (Parent) loader2.load();

        Stage menuStage = new Stage();

        Scene loadingScene = new Scene(root);
        Scene menuScene = new Scene(root2);
        primaryStage.setScene(loadingScene);
        menuStage.setScene(menuScene);

        LoadingSceneController loadingSceneController = new LoadingSceneController();
        MenuSceneController menuSceneController = new MenuSceneController();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        menuStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
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
                    menuStage.show();

                }
            });
        }).start();
    }


    public static void main(String[] args) {
        // Configuring the application
        Configuration configuration = Configuration.getInstance();
        configuration.configure();
        // Launching the application
        launch(args);
    }
}