import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;
public class Bullet
{
	private int x; 
    private int y;
    private boolean canMove; 
    private Image bullet;
  	public Bullet(){}
    public Bullet(int x,int y) 
    { 
        this.x = x; 
        this.y = y; 
        canMove = false; 
		
        bullet = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/bullet.png"));
    } 
    public int getX() 
    { 
        return x; 
    } 
    public int getY() 
    { 
        return y; 
    } 
    public void set(int x,int y)
    {
        this.x = x;
        this.y = y;
    }
	public void canMove(boolean find)
    {
        canMove = find;
    }
    public void drawMe(Graphics window, JFrame Canvas) 
    { 
        window.drawImage(bullet,x,y+20,Canvas);
        
        if(canMove == true)
        {
            x=x+10;
            //x = voX*(time) + x0;
            //y = voY*(time) + y0;
        }
        
    } 
    public void move(double time)
    {
        /*
        if(canMove == true)
        {
            x = (int)(x + time);
        }
        */
    }
}