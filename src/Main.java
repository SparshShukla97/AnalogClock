import javax.swing.*;
import java.util.List;

public class Main extends JFrame {



    private static final long serialVersionId;

    static {
        serialVersionId = 1L;
    }

    clockContainer container = null;

    public Main(){
        setLayout(null);

        container = new clockContainer();
        container.setLocation(0,0);
        add(container);

        setBounds(20,20,300,320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void startClock(){
        List<Point> secondPoint = container.grabPoint(140);
        List<Point> minutePoint = container.grabPoint(130);
        List<Point> hourPoint = container.grabPoint(120);

        while(true){
            for(int i= 0 ; i < 360; i+= 6){
                Point hour = hourPoint.get(i);
                container.updateHour(150 + hour.getX(), 150- hour.getY());
                for(int j=0; j<360;j+= 6){
                    Point minute = minutePoint.get(j);
                    container.updateMinute( 150 + minute.getX(),150 - minute.getY());
                    for(int k=0;k< 360; k += 6){
                        Point second = secondPoint.get(k);
                        container.updateSecond( 150 + second.getX(), 150 - second.getY());
                    // wait 1 sec
                        try {
                            Thread.sleep(1000);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }



                    }
                }
            }
        }
    }



    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setTitle("Analog Clock");
            new Thread(()->{
                app.startClock();
            }).start();
        });

    }
}