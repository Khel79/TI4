import hexagon.GameBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Draw extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        int radius = 32;
        int rings = 2;
        boolean isFlatTopped = true;
        GameBoard gameBoard = new GameBoard(rings, isFlatTopped, radius);

        for (int i = 0; i < gameBoard.getGameBoard().length; i++) {
            if (i % 2 == 0) {
                gameBoard.getGameBoard()[i].setFill(Color.GREY);
                gameBoard.getGameBoard()[i].setStroke(Color.RED);
            } else {
                gameBoard.getGameBoard()[i].setFill(Color.BLACK);
                gameBoard.getGameBoard()[i].setStroke(Color.GREEN);
            }
            gameBoard.getGameBoard()[i].setStrokeWidth(2); // Math.round(radius / 10)
            gameBoard.getGameBoard()[i].setStrokeType(StrokeType.INSIDE);
            root.getChildren().add(gameBoard.getGameBoard()[i]);
            System.out.printf("Placing Hexagon %2d with center coordinates (%4d, %4d)\n", i+1, gameBoard.getGameBoardTiles()[i].getCenter().x, gameBoard.getGameBoardTiles()[i].getCenter().y);
            System.out.printf("Placing Hexagon %2d with info: %s\n", i+1, gameBoard.getGameBoardTiles()[i].toString());
            //System.out.println("Placing Hexagon " + (i + 1) + " on spot " + gameBoard.getGameBoard()[i].getPoints().toString());
        }

        // Create the Scene
        Scene scene = new Scene(root);
        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("An attempt at a TI4 map editor");
        // Display the Stage
        stage.show();
    }
}
