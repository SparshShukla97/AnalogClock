import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;


public class clockContainer extends JPanel {
    private static final long serialVersionId = 1L;

    private int secondx = 150, secondy = 10;
    private int minutex = 150 , minutey = 30;
    private int hourx = 150, houry=50;

    clockContainer(){
        setBackground(Color.GRAY);

        setSize(300,300);
        setPreferredSize(new Dimension(300,300));
    }

    // get  all circlefrance x , y point
    public List<Point> grabPoint(int handLen){
        if(handLen == 0){
            handLen = 140;
        }
        List<Point> pointList = new ArrayList<>();

        for(int i= 0; i < 360; i++){
            double radians = Math.toRadians(i);
            double sinValue = Math.sin(radians);
            double cosValue = Math.cos(radians);
            final int x = (int) (sinValue * handLen);
            final int y = (int)(cosValue * handLen);
            pointList.add(new Point(x,y));
        }
        return pointList;
    }

    //update minute hour second
    public void updateSecond(int secondx, int secondy ){
        this.secondx=secondx;
        this.secondy=secondy;
        repaint();
        updateUI();
    }
    public void updateMinute(int minutex, int minutey){
        this.minutex=minutex;
        this.minutey=minutey;

    }
    public void updateHour(int hourx, int houry){



        this.hourx=hourx;
        this.houry=houry;
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        drawDial(g);
        drawSecondHand(g);
        drawMinuteHand(g);
        drawHourHand(g);
        drawLabel(g);
    }

     private void drawSecondHand(Graphics g){
        g.setColor(Color.GREEN);
        g.drawLine(150,150,secondx, secondy);
     }
    private void drawMinuteHand(Graphics g){
        g.setColor(Color.RED);
        g.drawLine(150,150,minutex, minutey);
    }
    private void drawHourHand(Graphics g){
        g.setColor(Color.YELLOW);
        g.drawLine(150,150,hourx, houry);
    }


    public void drawDial(Graphics g){
        g.setColor(Color.BLACK);
        g.drawOval(0,0,300,300);
        g.fillOval(145,145,10,10);
    }

    public void drawLabel(Graphics g){
        List<Point> p = grabPoint(140);
        List<Point> points = new ArrayList<>();

        for(int i = p.size() -1; i >=0; i-- ){
            points.add(p.get(i));
        }
        for(int j = 0 ; j<360; j++){
            if((j + 1) % 30 ==0){
                int hour = (j+1 )/30;

                if(hour ==0){
                    hour = 12;
                }
                g.setColor(Color.ORANGE);
                g.drawString("" + hour , 145 - points.get(j).getX(), 150 - points.get(j).getY());

            }
            else if((j+1) % 6 == 0){
                g.setColor(Color.BLACK);
                g.fillOval(148 - points.get(j).getX(),148 - points.get(j).getY(), 4,4);
            }
        }













    }




}
