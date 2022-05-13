
// BOUNCING EVIL EYE BEADS WITH STOP-PLAY-CHANGE COLOR-SPEED UP BUTTONS USING THREAD
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.JPanel;
public class BouncingEvilEyeBeadsAnimation extends JFrame implements ActionListener
{
    private Can canvas=new Can();  //Instance of a canvas
    boolean mythread=true; //flag for controllıng stop-play conditions
    boolean speed=false; //flag for controlling the speed
    public BouncingEvilEyeBeadsAnimation()
    {

        addWindowListener(new MyFinishWindow());
        this.setSize(800,600); //Setting the width and height
        this.setLocation(500,200);//Setting the location
        this.setTitle("EVIL EYE BEADS "); //Setting the title
        canvas.setBackground(Color.green); //Green for inital background color

        canvas.setPreferredSize(new Dimension(400,300)); //Canvas size

        this.add(canvas);
        this.pack();
        this.setVisible(true);


        // Creating buttons
        Button button_s = new Button();
        button_s.setLabel("Play"); //First button
        button_s.addActionListener(this);

        Button button_b = new Button();
        button_b.addActionListener(this);
        button_b.setLabel("Stop"); //Second button

        Button buttonn = new Button();
        buttonn.addActionListener(this);
        buttonn.setLabel("Speed Up"); //Second button


        Button buttonp = new Button();
        buttonp.addActionListener(this);
        buttonp.setLabel("Change Backgroundcolor"); //Second button

        //Adding Buttons to the canvas
        canvas.add(button_s);
        canvas.add(button_b);
        canvas.add(buttonp);
        canvas.add(buttonn);
        add(canvas);
        setLayout(null); //setting layout
        canvas.calldraw(); //Drawing canvas

    }
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String but = e.getActionCommand();

        if(but.equals("Stop")) { //Stop condition to stop the animation
            mythread=false;
        }
        else if(but.equals("Play")) {//Play condition to continue the animation
            mythread=true;
        }
        else if(but.equals("Speed Up")){ //Speed condition to speed up the animation
            speed=true;
        }
        else if(but.equals("Change Backgroundcolor")) { //Generating random colors to execute by third button

            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            Color randomColor = new Color(r, g, b);
            canvas.setBackground(randomColor);  //Setting the color
            repaint();
        }

    }
    public class MyFinishWindow extends WindowAdapter //Adapter controls the exit operation
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }

    private class Can extends JPanel //Canvas class
    {
        public int x=30,y=30,vx=5,vy=5; //Animation position and velocity values

        public void calldraw()
        {
            Thread thh = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(15);
                            repaint();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            thh.start();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //2D objects for oval
            Graphics2D g2d = (Graphics2D) g;
            Graphics2D g2d2 = (Graphics2D) g;
            Graphics2D g2d3 = (Graphics2D) g;
            Graphics2D g2d4 = (Graphics2D) g;
            Graphics2D g2dso = (Graphics2D) g;

            //Setting for renderigng
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2dso.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

            if(mythread) {  //Checking the flag conditions

                if(speed){  //Speed up if the user pressed Speed
                    x +=3*vx;
                    y += 3*vy;
                }
                else{ //Otherwise don't change the vx or vy
                    x += vx;
                    y += vy;
                }

                //Checking x and y conditions
                if (x < 0) {
                    x = 10;
                    vx *= -1;
                }
                if (y < 0) {
                    y = 10;
                    vy *= -1;
                }
                if (x > getWidth()) {
                    x = getWidth() - 10;
                    vx *= -1;
                }
                if (y > getHeight()) {
                    y = getHeight() - 10;
                    vy *= -1;
                }

            }


            //Setting and filling the color and sizes

            g2d.setColor(Color.blue);
            g2d.fillOval(x-10,y-10,89,100);

            g2d2.setColor(Color.white);
            g2d2.fillOval(x+2,y+2,70,70);

            g2d2.setColor(Color.yellow);
            g2d2.fillOval(x+8,y+8,50,55);


            g2d4.setColor(Color.black);
            g2d4.fillOval(x+20,y+20,25,25);


        }
    }

    public static void main(String[] args) {
        BouncingEvilEyeBeadsAnimation myAnim=new BouncingEvilEyeBeadsAnimation();  //Instance of Animation class

    }
}