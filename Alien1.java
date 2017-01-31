import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Image;
import java.awt.Toolkit;
public class Alien1
{
	private int x; 
    private int y;
    private boolean canMove; 
    private Image alien1;
    private int hit;
    public Alien1()
    {
        x = 850;
        y = (int)(Math.random()*600);
        canMove = false; 
        hit = 0;
        
        alien1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/alien1.png"));
    }
    public Alien1(int x,int y, String url) 
    { 
        this.x = x; 
        this.y = y; 
        canMove = false; 
		
        alien1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource(url));
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
        window.drawImage(alien1,x,y,Canvas);
        /*
        if(canMove == true)
        {
            x=x-2;
        }
        */
        
    } 
    
    public void move(double time, double speed)
    {
        if(canMove == true)
        {
            x=(int)(x-(speed*time));
           // System.out.println(x);
        }
    }
    public boolean checkBullet(int bulletX, int bulletY)
    {
        if(bulletX>x&&bulletX<x+150&&bulletY>y&&bulletY<y+155&&bulletX<800)
        {
            hit++;
            if(hit == 10)
            {
                x=1000;
                y=1000;
            }
            return true;
            
        }
        return false;
    }
}