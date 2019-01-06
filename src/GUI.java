import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GUI extends Group {

    public GUI() {

        Btn play = new Btn("Play");
        Btn quit = new Btn("Quit");

        play.setX(Main.WORLD_WIDTH/2 - play.getWidth()/2);
        play.setY(Main.WORLD_HEIGHT/2 - play.getHeight()*2);

        quit.setY(Main.WORLD_HEIGHT/2 + quit.getHeight()*2);
        quit.setX(Main.WORLD_WIDTH/2 - quit.getWidth()/2);

        this.getChildren().addAll(play, quit);
    }
}
