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
        /*Liter M*/
        brazenchem(30,30,30,400,gc); //left line
        brazenchem(30,30,140,200,gc);//left 45 line
        brazenchem(240,30,240,400,gc);//right line
        brazenchem(240,30,140,200,gc);//right 45 line


        brenenchemCircul(600,125,92, gc);
        brenenchemCircul(600,318,92, gc);




    }
    private void brazenchem(int x1, int y1, int x2, int y2, GraphicsContext gc){
        int width=Integer.valueOf(widthF.getText());

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
