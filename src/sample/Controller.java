package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private Pane centerPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        canvas.widthProperty().bind(centerPane.widthProperty());
        canvas.heightProperty().bind(centerPane.heightProperty());
        canvas.widthProperty().addListener(e->draw());
        canvas.heightProperty().addListener(e->draw());
    }

    @FXML
    private void draw() {
        GraphicsContext gc=canvas.getGraphicsContext2D();
        gc.setFill(Color.AQUA);

        brazenchem(0,0,100,100,gc);
        System.out.println("LOL");

    }
    private void brazenchem(int x1, int y1, int x2, int y2, GraphicsContext gc){
        int xErr=0; int yErr=0;
        int dx=x2-x1; int dy=y2-y1;
        int incX=0; int incY=0;
        if (dx>0)  incX=1;
        if (dx==0) incX=0;
        if (dx<0) incX=-1;
        int d=0;
        if(dy>0) incY=1;
        if(dy==0) incY=0;
        if(dy<0) incY=-1;
        dx=Math.abs(dx); dy= Math.abs(dy);
        if (dx>dy)  d=dx;
        else  d=dy;
        int x=x1;
        int y=y1;
        gc.fillRect(x,y,1,1);
        for (int i = 0; i < d; i++) {
            xErr=xErr+dx;
            yErr=yErr+dy;
            if (xErr>d){
                xErr=xErr-d;
                x=x+incX;
            }
            if(yErr>d) {
                yErr=yErr-d;
                y=y+incY;
            }
            gc.fillRect(x,y,4,4);
        }



    }


}