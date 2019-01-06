import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Btn extends Group {

    private double x;
    private double y;
    private double width;
    private double height;
    private Font font;
    private String text;

    public Btn(String s) {
        this(s, 0.0, 0.0);
    }

    public Btn(String s, Font font) {
        this(s, 0.0, 0.0, font);
    }

    public Btn(String s, int x, int y) {
        this(s, (double) x, (double) y);
    }

    public Btn(String s, int x, int y, Font font) {
        this(s, (double) x, (double) y, font);
    }

    public Btn(String s, double x, double y) {
        this.x = x;
        this.y = y;
        text = s;

        Group g = createTextBtn(s, font);
        g.setTranslateX(x);
        g.setTranslateY(y);

        this.getChildren().add(g);
    }

    public Btn(String s, double x, double y, Font font) {
        this.x = x;
        this.y = y;
        this.font = font;
        text = s;

        Group g = createTextBtn(s, font);
        g.setTranslateX(x);
        g.setTranslateY(y);

        this.getChildren().add(g);
    }

    public void setX(double x) {
        this.setTranslateX(x);
    }

    public void setY(double y) {
        this.setTranslateY(y);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Font getFont() {
        return font;
    }

    public String getText() {
        return text;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    private Group createTextBtn(String s, Font f){


        Text t = new Text(s);

        if(f == null){
            t.setFont(Font.font ("Arial", 20));
        }else{
            t.setFont(f);
        }



        double textWidth = t.getLayoutBounds().getWidth();
        double textHeight = t.getLayoutBounds().getHeight();

        Text x = new Text("x");
        x.setFont(f);
        double wordSpacing = x.getLayoutBounds().getWidth();

        Rectangle r = new Rectangle(textWidth+ wordSpacing*2, textHeight+ textHeight/2);
        r.setArcHeight(textHeight/3);
        r.setArcWidth(textHeight/3);
        r.setStrokeWidth(textHeight/15);
        r.setStroke(Color.BLACK);
        r.setFill(Color.rgb(204, 204, 255));


        t.setTranslateX(wordSpacing);
        t.setTranslateY(textHeight + textHeight/8);

        Group g = new Group();
        g.getChildren().addAll(r,t);

        width = g.getLayoutBounds().getWidth();
        height = g.getLayoutBounds().getHeight();



        g.setOnMouseEntered(event->{

            r.setFill(Color.rgb(255, 153, 102));

        });

        g.setOnMouseExited(event->{

            r.setFill(Color.rgb(204, 204, 255));

        });


        return g;

    }




}
