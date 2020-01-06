    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    import static java.awt.Font.BOLD;

    public class Board extends JPanel implements ActionListener {

        final int WIDTH = 800;
        final int HEIGHT = 600;
        private final int EDGESPACE = 50;
        private final int DECORSIZE = 25;

        private int pScore = 0, cScore = 0;

        Paddle pPaddle;
        Paddle cPaddle;
        Ball ball;
        Timer timer;
        Game game;

        public Board(Game game){

            setPreferredSize(new Dimension(WIDTH, HEIGHT));
            setBackground(Color.BLACK);

            pPaddle = new Paddle(this, game);
            cPaddle = new Paddle(this, game);
            timer = new Timer(1000/60,this);
            timer.start();


            ball = new Ball(this);

        }

        public void init(){
            ball.setPosition(WIDTH/2,HEIGHT/2);
            pPaddle.setPosition(EDGESPACE, HEIGHT/2);
            cPaddle.setPosition(WIDTH - EDGESPACE, HEIGHT/2);
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Serif", BOLD, 72));
            printSimpleString(Integer.toString(pScore), getWidth()/2, 0, DECORSIZE *2, g);
            printSimpleString(Integer.toString(cScore), getWidth()/2, getWidth()/2, DECORSIZE *2, g);

            pPaddle.paint(g);
            cPaddle.paint(g);
            ball.paint(g);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           ball.checkCollisions(cPaddle);
           ball.checkCollisions(pPaddle);
            ball.move();
           cPaddle.move(ball);
           pPaddle.move();
           repaint();
        }

        private void printSimpleString(String s, int width, int xPos, int yPos, Graphics g){
            int stringLen = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
            int start = width/2 - stringLen/2;
            g.drawString(s,start+xPos, yPos);
        }

        public int getpScore() {
            return pScore;
        }

        public int getcScore() {
            return cScore;
        }

        public void setpScore(int pScore) {
            this.pScore = pScore;
        }

        public void setcScore(int cScore) {
            this.cScore = cScore;
        }
    }
