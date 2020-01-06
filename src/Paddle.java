
import java.awt.*;

public class Paddle {

    private int x, y;
    private final int WIDTH=20, HEIGHT=100;
    Board board;
    Game game;
    private int dy = 2;
    public Paddle(Board board, Game game){
        x=0;
        y=0;
        this.board = board;
        this.game = game;
    }

    public void setPosition(int x, int y){
        this.x = x - WIDTH/2;
        this.y= y - HEIGHT/2;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,WIDTH,HEIGHT);
    }

    public void move(){
        if(game.isUpPressed()) {
            if(y>0){
                y -= dy;
            }
        }
        if(game.isDownPressed()){
            if(y+HEIGHT < board.getHeight()){
                y+=dy;
            }
        }
    }

    public void move(Ball ball){

        if(ball.getX() + ball.getDiam() > board.getWidth()/2){
            if(ball.getY() > y + HEIGHT/2){
                y+=3;
            }
            if(ball.getY() < y + HEIGHT/2){
                y-=3;
            }
        }

    }


    public void paint(Graphics g){
        g.fillRect(x,y,WIDTH,HEIGHT);
    }


}
