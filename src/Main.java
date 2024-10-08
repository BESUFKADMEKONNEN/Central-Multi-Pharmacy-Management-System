import Model.DATABASE;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    public static void main(String[] args) {
launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("View/loginpage.fxml"));
        Scene scene=new Scene(root,700,500);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() throws Exception {
        DATABASE.checkDirectory();
    }
}