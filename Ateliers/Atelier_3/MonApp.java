import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MonApp extends Application
{
    public void start(Stage primaryStage)
    {
        Button button = new Button("Clic");

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 400, 400);

        primaryStage.setTitle("MonApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }    
    public static void main(String[] args)
    {
        launch(args);
    }
}
