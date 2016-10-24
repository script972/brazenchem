package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {


    @FXML
    private Canvas canvas;
    @FXML
    private Pane centerPane;
    @FXML
    private ColorPicker bgColor;
    @FXML
    private TextField widthF;


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
        gc.clearRect(0,0,900,900);
        gc.setFill(bgColor.getValue());
        //LEFT M
            //left
       // brazenchem(30,30,30,400,gc);//left
        brazenchem(30,15,30,350,gc);//left
        brazenchem(50,55,50,350,gc);//right
        brazenchem(30,15,50,15,gc);//verh
        brazenchem(30,350,50,350,gc);//nith
            //45
        brazenchem(50,55,130,250,gc);//nith
        brazenchem(50,15,130,210,gc);//verh
            //right
        brazenchem(210,55,210,350,gc);//left
        brazenchem(230,15,230,350,gc);//right
        brazenchem(210,15,230,15,gc);//verh
        brazenchem(210,350,230,350,gc);//nith
            //45
        brazenchem(210,55,130,250,gc);//nith
        brazenchem(210,15,130,210,gc);//verh



        //RIGHT 3
        brazenchem(300,50,300,75,gc);
        brazenchem(300,50,330,30,gc);
        brazenchem(330,30,380,30,gc);
        brazenchem(380,30,410,50,gc);
        brazenchem(410,50,410,120,gc);
        brazenchem(410,120,385,150,gc);
        brazenchem(385,150,440,190,gc);
        brazenchem(440,190,440,310,gc);
        brazenchem(440,310,400,335,gc);
        brazenchem(400,335,315,335,gc);
        brazenchem(315,335,290,300,gc);
        brazenchem(290,300,290,275,gc);

        brazenchem(285,45,285,75,gc);
        brazenchem(285,45,330,15,gc);
        brazenchem(285,75,300,75,gc);
        brazenchem(330,15,380,15,gc);
        brazenchem(380,15,425,45,gc);
        brazenchem(425,45,425,120,gc);
        brazenchem(425,120,405,145,gc);
        brazenchem(405,145,455,180,gc);
        brazenchem(454,179,454,318,gc);
        brazenchem(454,317,400,352,gc);
        brazenchem(400,352,310,352,gc);
        brazenchem(310,352,275,305,gc);
        brazenchem(275,305,275,275,gc);
        brazenchem(275,275,290,275,gc);







      //  brazenchem(50,30,50,400,gc);
        /*Liter M*/
      /*  brazenchem(30,30,30,400,gc); //left line
        brazenchem(30,30,140,200,gc);//left 45 line
        brazenchem(240,30,240,400,gc);//right line
        brazenchem(240,30,140,200,gc);//right 45 line*/


        brenenchemCircul(600,125,92, gc);
        brenenchemCircul(600,318,92, gc);




    }
    private void brazenchem(int x1, int y1, int x2, int y2, GraphicsContext gc){
        int width=1;

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
        gc.fillRect(x,y,width,width);
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
            gc.fillRect(x,y,width,width);
        }
    }

    private void brenenchemCircul(int X1, int Y1, int R, GraphicsContext gc)
    {
        int width=Integer.valueOf(widthF.getText());

        int x= 0;

        int y = R;
        int delta= 1 - 2 * R;
        int error = 0;
        while (y >= 0) {
           // if (y<R){y--; continue;}
            gc.fillRect(X1 + x, Y1 + y, width, width);
            gc.fillRect(X1 + x, Y1 - y, width, width);
            //gc.fillRect(X1 - x, Y1 + y,10,10); //3
            //gc.fillRect(X1 - x, Y1 - y,10,10);//2
            error = 2 * (delta + y) - 1;
            if ((delta < 0) && (error <= 0)) {
                delta += 2 * ++x + 1;
                continue;
            }
                    error=2 * (delta - x) - 1;
            if ((delta > 0) && (error > 0)) {
                delta += 1 - 2 * --y;
                continue;
            }
                x++;
                delta += 2 * (x - y);

            y--;
        }
    }


    public void inc(ActionEvent actionEvent) {
        widthF.setText(String.valueOf((Integer.valueOf(widthF.getText())+1)));

    }
    public void dec(ActionEvent actionEvent) {
        widthF.setText(String.valueOf((Integer.valueOf(widthF.getText())-1)));
    }
}
