import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    public static double WORLD_WIDTH = 800;
    public static double WORLD_HEIGHT = 800;
    private double tileWidth = 100;
    private int nBombs = 10;
    private Tile[][] gameBoard;
    private List<Tile> bombs;
    private static Stage pStage;

    private Group root = new Group();
    private Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineSweeper");
        setPrimaryStage(primaryStage);

        //initGame();
        userInterface();


        primaryStage.show();
    }

    private void initGame(){
        root.getChildren().clear();
        int nTiles = (int) (WORLD_WIDTH/tileWidth);
        gameBoard = new Tile[nTiles][nTiles];
        for (int col = 0; col < nTiles; col++) {

            for (int row = 0; row < nTiles; row++) {

                gameBoard[col][row] = new Tile(tileWidth, col, row, gameBoard, false);

            }
            
        }
        placeBombs(nTiles);
        renderTiles();

        scene.setOnMouseClicked(event -> {
            checkWin();
        });


        getPrimaryStage().setScene(scene);
    }


    private void userInterface(){
        Group group = new GUI();
        Scene scene = new Scene(group, WORLD_WIDTH, WORLD_HEIGHT);

        getPrimaryStage().setScene(scene);
    }


    public static void loss(){

    }

    public void checkWin(){

        if(getMarkedBombs() == Tile.markedTiles.size() && getMarkedBombs() == bombs.size()){

            System.out.println("WIN");

            initGame();

        }


    }

    private int getMarkedBombs(){
        int marked = 0;
        for (int i = 0; i < bombs.size(); i++) {
            if(bombs.get(i).isMarked()){
                marked++;
            }
        }
        return marked;
    }

    private void placeBombs(int nTiles){
        bombs = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < nBombs; i++) {
            Tile t = gameBoard[rand.nextInt(nTiles)][rand.nextInt(nTiles)];
            t.setBomb();
            bombs.add(t);
        }
    }

    private void renderTiles(){

        for (int col = 0; col < gameBoard.length; col++) {

            for (int row = 0; row < gameBoard.length; row++) {

                root.getChildren().add(gameBoard[row][col]);
                gameBoard[row][col].setNearbyBombs();
            }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }
}