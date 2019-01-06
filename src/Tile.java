import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Tile extends Group {

    private double x;
    private double y;
    private double width;
    private int arrRow;
    private int arrCol;
    private List<Tile> neighbors;
    private boolean isBomb;
    private Tile[][] gameBoard;
    private boolean isOpened = false;
    private int nearbyBombs;
    private boolean marked = false;
    public static ArrayList<Tile> markedTiles = new ArrayList<>();

    public Tile(double width, int arrCol, int arrRow, Tile[][] gameBoard, boolean isBomb) {
        x = arrRow * width;
        y = arrCol * width;
        this.width = width;
        this.arrRow = arrRow;
        this.arrCol = arrCol;
        this.isBomb = isBomb;
        this.gameBoard = gameBoard;

        Rectangle r = createCover();
        this.getChildren().add(r);

        r.setOnMouseClicked(event -> {

            if (event.getButton() == MouseButton.SECONDARY) {
                if (!isOpened) {
                    if (marked) {
                        marked = false;
                        this.getChildren().remove(1);
                        markedTiles.remove(this);
                    } else {
                        marked = true;
                        this.getChildren().add(createFlag());
                        markedTiles.add(this);
                    }
                }
            } else {
                open();
            }


        });

    }

    private void showNeighbuors() {
        for (int i = 0; i < neighbors.size(); i++) {
            Rectangle t = (Rectangle) neighbors.get(i).getChildren().get(0);
            t.setFill(Color.GREEN);
        }
    }


    private void open() {

        if (!isOpened && !marked) {
            isOpened = true;



            openTile();

            if (nearbyBombs == 0 && !isBomb) {
                openAllNerbyNeighbors();
            }

            if(isBomb){
                Main.loss();
            }


        }

    }

    private void openAllNerbyNeighbors() {
        for (int i = 0; i < neighbors.size(); i++) {
            neighbors.get(i).open();
        }
    }

    public void setNearbyBombs() {
        initNeighbors();
        if(isBomb){
            nearbyBombs = 0;
        }else {
            nearbyBombs = getNeighborsContainsBomb();
        }
    }

    private int getNeighborsContainsBomb() {
        int bombs = 0;
        for (int i = 0; i < neighbors.size(); i++) {
            if (neighbors.get(i).hasBomb()) {
                bombs++;
            }
        }
        return bombs;
    }

    private void initNeighbors() {

        neighbors = new ArrayList<>();
        for (int col = -1; col < 2; col++) {
            for (int row = -1; row < 2; row++) {
                int indexX = arrRow + row;
                int indexY = arrCol + col;

                if (!(col == 0 && row == 0) && isValid(indexY, indexX)) {
                    neighbors.add(gameBoard[indexY][indexX]);
                }
            }
        }
    }

    private boolean isValid(int y, int x) {
        return !((y < 0 || y >= gameBoard.length) || (x < 0 || x >= gameBoard[0].length));
    }

    private void openTile() {
        this.getChildren().clear();
        Rectangle r = new Rectangle(arrRow * width, arrCol * width, width, width);
        r.setFill(Color.BLUE);
        r.setStroke(Color.BLACK);
        this.getChildren().add(r);

        if (isBomb) {
            r.setFill(Color.YELLOW);
        }

        if (nearbyBombs > 0) {
            Text t = new Text(nearbyBombs + "");
            t.setTranslateX(arrRow * width + width / 2);
            t.setTranslateY(arrCol * width + width / 2);
            this.getChildren().add(t);
        }
    }

    private Group createFlag() {
        Group g = new Group();
        double flagPoleWidth = width / 20;
        double basX = arrRow * width;
        double basY = arrCol * width;
        Rectangle r = new Rectangle(basX + width / 2 - flagPoleWidth/2,basY + width / 6     , flagPoleWidth  , width * 2 / 3);
        Rectangle f = new Rectangle(basX + width / 2 + flagPoleWidth/2,basY + width *2/8    , width/7  , width/10);
        Rectangle b = new Rectangle(basX + width * 2/6                ,basY + width * 5/6   , width*1/3, flagPoleWidth);
        f.setFill(Color.RED);
        g.getChildren().addAll(r, f, b);
        return g;
    }

    private Rectangle createCover() {
        Rectangle r = new Rectangle(arrRow * width, arrCol * width, width, width);
        r.setFill(Color.GRAY);
        r.setStroke(Color.BLACK);
        return r;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean hasBomb() {
        return isBomb;
    }

    public void setBomb() {
        isBomb = true;
    }

}
