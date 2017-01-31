import java.awt.*; 
import java.util.*;
import javax.swing.JFrame; 
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.event.*; 
import javax.sound.sampled.*;
public class Herbert extends JFrame implements KeyListener, MouseListener
{ 
    private BufferedImage buffered; 
    private int mousex; 
    private int mousey; 
    private Image icon, regcursor, targetcursor, startScreen, pauseScreen, second, scene1 ,scene2, scene3 ,scene4, scene5, human, finalalien, aim, bullet, level, story1, story2, story3, story4, story5;
    private Toolkit toolkit;
    private Cursor c1, c2;
    private boolean display;
    private boolean canStart, canPause, canStory, canPlay, canLevel, canFinal, canPlay1, canPlay2, canPlay3, canPlay4, canPlay5, canShoot, canStory1;
    private ArrayList<Bullet> bullets;
    private ArrayList<Integer> move;
    private ArrayList<Boolean> canShoot2;
    private ArrayList<Alien1> aliens1, aliens2, aliens3, aliens4, aliens5;
    private int alien1death = 0;
    private int alien2death = 0;
    private int alien3death = 0;
    private int alien4death = 0;
    private int alien5death = 0;
    private double realtime1 = 3000, realtime2 = 750, realtime3 = 450, realtime4 = 300, realtime5 = 200;
    boolean gameOver = true;
    int x = 100; 
    int y = 370; 
    //Instantiate Input 
    Input input = new Input(this); 
    int count = 0;
    int bulletnum = 100;  
    public Herbert() 
    { 
        super("Human vs. Aliens"); 
        toolkit = Toolkit.getDefaultToolkit();
        startScreen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/Start.png"));
        icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/icon.jpg"));
        pauseScreen = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/pause.png"));
        second = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/Second.png"));
        scene1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/scene1.png"));
        scene2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/scene2.png"));
        scene3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/scene3.png"));
        scene4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/scene4.png"));
        scene5 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/scene5.png"));
        story1 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/story1.png"));
        story2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/story2.png"));
        story3 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/story3.png"));
        story4 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/story4.png"));
        story5 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/story5.png"));
        human = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/human.png"));
        aim = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/aim.png"));
        regcursor = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/cur.png"));
        targetcursor = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/aim.png"));
        bullet = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/aim.png"));
        level = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/level.png"));
        finalalien = Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/finalalien.png"));
        c1 = toolkit.createCustomCursor(regcursor , new Point(getX(), getY()), "img");
        c2 = toolkit.createCustomCursor(targetcursor , new Point(getX(), getY()), "img");
        bullets = new ArrayList<Bullet>();
        move = new ArrayList<Integer>();
        aliens1 = new ArrayList<Alien1>();
        aliens2 = new ArrayList<Alien1>();
        aliens3 = new ArrayList<Alien1>();
        aliens4 = new ArrayList<Alien1>();
        aliens5 = new ArrayList<Alien1>();
        canShoot2 = new ArrayList<Boolean>();     
        display = true;
        canStart = true;
        canStory = false;
        canPlay = false;
        canPlay1 = false;
        canPlay2 = false;
        canPlay3 = false;
        canPlay4 = false;
        canPlay5 = false;
        canFinal = false;
        canShoot = false;
        setSize(800,600); 
        setVisible(true); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
        setCursor(c1);
        setIconImage(icon);
        addKeyListener(this);
        addMouseListener(this); 
        int alienx = 850;
        int aliendiff = 0;
        for(int i = 0; i<200; i++)
        {
            aliens1.add(i, new Alien1(alienx+aliendiff ,(int)(Math.random() * 400), "images/alien21.gif"));
            aliendiff += (int)(Math.random() * 100) + 70;
        }
        aliendiff = 0;
        for(int i = 0; i<1000; i++)
        {
            aliens2.add(i, new Alien1(alienx+aliendiff ,(int)(Math.random() * 400), "images/alien51.gif"));
            aliendiff += (int)(Math.random() * 100) + 70;
        }
        aliendiff = 0;
        for(int i = 0; i<1000; i++)
        {
            aliens3.add(i, new Alien1(alienx+aliendiff ,(int)(Math.random() * 400), "images/alien1.png"));
            aliendiff += (int)(Math.random() * 100) + 70;
        }
        aliendiff = 0;
        for(int i = 0; i<1000; i++)
        {
            aliens4.add(i, new Alien1(alienx+aliendiff ,(int)(Math.random() * 400), "images/pig1.gif"));
            aliendiff += (int)(Math.random() * 100) + 70;
        }
        aliendiff = 0;
        for(int i = 0; i<1000; i++)
        {
            aliens5.add(i, new Alien1(alienx+aliendiff ,(int)(Math.random() * 400), "images/finalalien.png"));
            aliendiff += (int)(Math.random() * 100) + 70;
        }
        for(int i = 0; i<250000; i++)
        {
            bullets.add(i,new Bullet(x-200,y));
            move.add(0);
            canShoot2.add(false);
        }
        for(int i = 0; i<1; i++)
        {
            //aliens1.add(i,new Alien1());
            
        }
       
        bulletnum = bullets.size(); 
    }      
    public void paint(Graphics window)  
    { 
        if(buffered==null) 
            buffered = (BufferedImage)(createImage(getWidth(),getHeight())); 
  
        Graphics windowTemp = buffered.createGraphics(); 
        if (!display)
            paintStart(windowTemp);
        if (display)
        {
            if(canStart)
                paintStart(windowTemp);
            if(canStory)
            {
                paintStory(windowTemp);
            }    
            if(canPlay1)
            {
                paintScene1(windowTemp);  
            }
            if(canPlay2)
            {
                paintScene2(windowTemp);
            }
            if(canPlay3)
            {
                paintScene3(windowTemp);
            }
            if(canPlay4)
            {
                paintScene4(windowTemp);
            }
            if(canPlay5)
            {
                paintScene5(windowTemp);
            }
            if(canPause)
            {
                paintPause(windowTemp);            
            }
            if(canStory1)
            {
                if(canPlay1)
                {
                    windowTemp.drawImage(story1, 0, 0, this); 
                }
                if(canPlay2)
                {
                    windowTemp.drawImage(story2, 0, 0, this);
                }
                if(canPlay3)
                {
                    windowTemp.drawImage(story3, 0, 0, this);
                }
                if(canPlay4)
                {
                    windowTemp.drawImage(story4, 0, 0, this);
                }
                if(canPlay5)
                {
                    windowTemp.drawImage(story5, 0, 0, this);
                }             
            }
            if(canLevel)
            {
                windowTemp.drawImage(level, 0, 0, this);
            }
            if(canFinal)
            {
                paintFinal(windowTemp);
                canFinal = false;
            }
        } 
        window.drawImage(buffered, 0, 0, null);     
    } 
    public void animate() 
    { 
        double time = 0;
        while(true) 
        { 
            try{  
                Thread.sleep(20);  
            }catch(InterruptedException ex){  
                Thread.currentThread().interrupt();  
            } 
            if(Input.keyboard[83]) y+=10; 
            if(Input.keyboard[87]) y-=10; 
            if(Input.keyboard[65]) x-=10;
            if(Input.keyboard[68]) x+=10; 
           
            if(canShoot && (canPlay1||canPlay2||canPlay3||canPlay4||canPlay5) && canPlay)
            {
                for(int i = 0;i < 2500;i++)
                {
                    if (canShoot2.get(i))
                    {
                        if(move.get(i) == 0)
                        {
                            bullets.get(i).set(x+65,y+5);
                        }
                        move.set(i,move.get(i)+1);  
                        bullets.get(i).canMove(true);  
                    }
                }
            }
            for(int i = 0; i < 2500; i++)
            {
                for(int j = 0;j < 200; j++)
                {
                    if(aliens1.get(j).checkBullet(bullets.get(i).getX(),bullets.get(i).getY()) && canShoot2.get(i) )
                    {
                        System.out.println("HITTT");
                        hitsound();
                        alien1death++;
                        bullets.get(i).set(1000,1000);
                        move.set(i,0);
                        bullets.get(i).canMove(false);
                        canShoot2.set(i,false);
                        aliens1.get(j).set(1000,1000);
                        //Jdeaths++;
                    }
                }
            }
            if(canPlay1)
            {
                for(int i = 0; i<aliens1.size(); i++)
                {
                    aliens1.get(i).move(time, 0.01);
                    //time++;
                }
                realtime1--;
                time++;
                if(Input.keyboard[83]) y+=5; 
                if(Input.keyboard[87]) y-=5; 
                if(Input.keyboard[65]) x-=5;
                if(Input.keyboard[68]) x+=5; 
                repaint();
            }
            if(realtime1==0)
            {
                time = 0;
                canLevel = false;
                canPause = false;
                canPlay2 = true;

                canPlay1 = false;
                canPlay3 = false;
                canPlay4 = false;
                canPlay5 = false;
                realtime1 = 11111;
                repaint();
            }
            for(int i = 0; i < 2500; i++)
            {
                for(int j = 0;j < 200; j++)
                {
                    if(aliens2.get(j).checkBullet(bullets.get(i).getX(),bullets.get(i).getY()) && canShoot2.get(i) )
                    {
                        System.out.println("HITTT");
                        hitsound();
                        alien2death++;
                        bullets.get(i).set(1000,1000);
                        move.set(i,0);
                        bullets.get(i).canMove(false);
                        canShoot2.set(i,false);
                        aliens2.get(j).set(1000,1000);
                        //Jdeaths++;
                    }
                }
            }
            if(canPlay2)
            {
                for(int i = 0; i<aliens2.size(); i++)
                {
                    aliens2.get(i).move(time,0.1);
                    //time++;
                }
                realtime2--;
                time++;
                if(Input.keyboard[83]) y+=5; 
                if(Input.keyboard[87]) y-=5; 
                if(Input.keyboard[65]) x-=5;
                if(Input.keyboard[68]) x+=5; 
                repaint();
            }

            if(realtime2==0)
            {
                time=0;
                canLevel = false;
                canPause = false;
                canPlay3 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay4 = false;
                canPlay5 = false;
                realtime2 = 11111;

                repaint();
            }


            for(int i = 0; i < 2500; i++)
            {
                for(int j = 0;j < 200; j++)
                {
                    if(aliens3.get(j).checkBullet(bullets.get(i).getX(),bullets.get(i).getY()) && canShoot2.get(i) )
                    {
                        System.out.println("HITTT");
                        hitsound();
                        alien3death++;
                        bullets.get(i).set(1000,1000);
                        move.set(i,0);
                        bullets.get(i).canMove(false);
                        canShoot2.set(i,false);
                        aliens3.get(j).set(1000,1000);
                        //Jdeaths++;
                    }
                    
                }
            }
             if(canPlay3)
            {
                for(int i = 0; i<aliens3.size(); i++)
                {
                    aliens3.get(i).move(time, 0.1);
                    //time++;
                }
                realtime3--;
                time++;
                if(Input.keyboard[83]) y+=5; 
                if(Input.keyboard[87]) y-=5; 
                if(Input.keyboard[65]) x-=5;
                if(Input.keyboard[68]) x+=5; 
                repaint();
            }
            if(realtime3==0)
            {
                time=0;
                canLevel = false;
                canPause = false;
                canPlay4 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay3 = false;
                canPlay5 = false;
                realtime3 = 11111;

                repaint();
            }
            for(int i = 0; i < 2500; i++)
            {
                for(int j = 0;j < 200; j++)
                {
                    if(aliens4.get(j).checkBullet(bullets.get(i).getX(),bullets.get(i).getY()) && canShoot2.get(i) )
                    {
                        System.out.println("HITTT");
                        hitsound();
                        alien4death++;
                        bullets.get(i).set(1000,1000);
                        move.set(i,0);
                        bullets.get(i).canMove(false);
                        canShoot2.set(i,false);
                        aliens4.get(j).set(1000,1000);
                        //Jdeaths++;
                    }
                   
                }
            }
            if(canPlay4)
            {
                for(int i = 0; i<aliens4.size(); i++)
                {
                    aliens4.get(i).move(time, 0.2);
                    //time++;
                }
                realtime4--;
                time++;
                if(Input.keyboard[83]) y+=5; 
                if(Input.keyboard[87]) y-=5; 
                if(Input.keyboard[65]) x-=5;
                if(Input.keyboard[68]) x+=5; 
                repaint();
            }
            if(realtime4==0)
            {
                time=0;
                canLevel = false;
                canPause = false;
                canPlay5 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay3 = false;
                canPlay4 = false;
                realtime4 = 11111;

                repaint();
            }
            for(int i = 0; i < 2500; i++)
            {
                for(int j = 0;j < 200; j++)
                {
                    if(aliens5.get(j).checkBullet(bullets.get(i).getX(),bullets.get(i).getY()) && canShoot2.get(i) )
                    {
                        System.out.println("HITTT");
                        hitsound();
                        alien5death++;
                        bullets.get(i).set(1000,1000);
                        move.set(i,0);
                        bullets.get(i).canMove(false);
                        canShoot2.set(i,false);
                        aliens5.get(j).set(1000,1000);
                        //Jdeaths++;
                    }
                   
                }
            }  
            if(canPlay5)
            {
                for(int i = 0; i<aliens5.size(); i++)
                {
                    aliens5.get(i).move(time, 0.5);
                    //time++;
                }
                realtime5--;
                time++;
                if(Input.keyboard[83]) y+=5; 
                if(Input.keyboard[87]) y-=5; 
                if(Input.keyboard[65]) x-=5;
                if(Input.keyboard[68]) x+=5; 
                repaint();
            }
            if(realtime5==0)
            {
                time=0;
                canLevel = false;
                canPause = false;
                canPlay5 = false;
                canFinal = true;
                canPlay1 = false;
                canPlay2 = false;
                canPlay3 = false;
                canPlay4 = false;
                realtime5 = 11111;
                gameOver = false;
                repaint();

                break;
            }  
            paint(getGraphics()); 
        } 
    } 
    public void paintStart(Graphics window)
    {
        window.drawImage(startScreen, 0, 0, this);
        
    }
    public void paintStory(Graphics window)
    {
        window.drawImage(second, 0, 0, this);
        window.setColor(new Color(68, 218, 92));
        window.fillRect(670, 560, 100, 30);
        window.setColor(new Color(255, 59, 37));
        window.fillRect(550, 560, 100, 30); 
    }
    public void paintScene1(Graphics window)
    {       
        window.drawImage(scene1, 0, 0, this);
        window.setColor(Color.GREEN);
        //for(int i = 0; i<bulletnum; i++)
           // window.fillOval(bulletx+50, bullety+40, 15, 15);
        
        for(int i = 0; i < 2500; i++)
        {
            if(canShoot2.get(i) == true)
            {
                bullets.get(i).drawMe(window,this);
            }
        }
        //j.drawMe(window);

        //window.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("images/alien51.gif")), 500, 300, this);

        window.drawImage(human, x, y, this);
        if(canPlay)
        {
            for(int i = 0;i < 200 ;i++)
            {
                aliens1.get(i).drawMe(window,this);
                aliens1.get(i).canMove(true);
            }
        }
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 50)); 
        window.drawString(""+alien1death, 64, 575);
        window.drawString(""+(realtime1/25), 500, 575);
        setCursor(c2);
        
    }
    public void paintScene2(Graphics window)
    {       
        window.drawImage(scene2, 0, 0, this);
        window.setColor(Color.GREEN);

        for(int i = 0; i < 2500; i++)
        {
            if(canShoot2.get(i) == true)
            {
                bullets.get(i).drawMe(window,this);
            }
        }
        //window.fillOval(bulletx+30, bullety+40, 15, 15);
        window.drawImage(human, x, y, this);
        if(canPlay)
        {
            for(int i = 0;i < 200 ;i++)
            {
                aliens2.get(i).drawMe(window,this);
                aliens2.get(i).canMove(true);
            }
        }
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 50)); 
        window.drawString(""+alien2death, 64, 575);
        window.drawString(""+(realtime2/25), 500, 575);
        //window.drawImage(aim, dragx-25, dragy-25, this);
       
        
        setCursor(c2);
        
    }
    public void paintScene3(Graphics window)
    {       
        window.drawImage(scene3, 0, 0, this);
        window.setColor(Color.GREEN);

        for(int i = 0; i < 2500; i++)
        {
            if(canShoot2.get(i) == true)
            {
                bullets.get(i).drawMe(window,this);
            }
        }

        //window.fillOval(bulletx+30, bullety+40, 15, 15);
        window.drawImage(human, x, y, this);
        if(canPlay)
        {
            for(int i = 0;i < 200 ;i++)
            {
                aliens3.get(i).drawMe(window,this);
                aliens3.get(i).canMove(true);
            }
        }
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 50)); 
        window.drawString(""+alien3death, 64, 575);
        window.drawString(""+(realtime3/25), 500, 575);
        //window.drawImage(aim, dragx-25, dragy-25, this);
       
        
        setCursor(c2);
        
    }
    public void paintScene4(Graphics window)
    {       
        window.drawImage(scene4, 0, 0, this);
        window.setColor(Color.GREEN);

        for(int i = 0; i < 2500; i++)
        {
            if(canShoot2.get(i) == true)
            {
                bullets.get(i).drawMe(window,this);
            }
        }
        //window.fillOval(bulletx+30, bullety+40, 15, 15);
        window.drawImage(human, x, y, this);
        if(canPlay)
        {
            for(int i = 0;i < 200 ;i++)
            {
                aliens4.get(i).drawMe(window,this);
                aliens4.get(i).canMove(true);
            }
        }
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 50)); 
        window.drawString(""+alien4death, 64, 575);
        window.drawString(""+(realtime4/25), 500, 575);
        //window.drawImage(aim, dragx-25, dragy-25, this);
       
        
        setCursor(c2);
        
    }
    public void paintScene5(Graphics window)
    {       
        window.drawImage(scene5, 0, 0, this);
        window.setColor(Color.GREEN);

        for(int i = 0; i < 2500; i++)
        {
            if(canShoot2.get(i) == true)
            {
                bullets.get(i).drawMe(window,this);
            }
        }
        //window.fillOval(bulletx+30, bullety+40, 15, 15);
        window.drawImage(human, x, y, this);
        if(canPlay)
        {
            for(int i = 0;i < 200 ;i++)
            {
                aliens5.get(i).drawMe(window,this);
                aliens5.get(i).canMove(true);
            }
        }
        
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 50)); 
        window.drawString(""+alien5death, 64, 575);
        window.drawString(""+(realtime5/25), 500, 575);
        //window.drawImage(aim, dragx-25, dragy-25, this);
       
        
        setCursor(c2);
        
    }
    public void paintFinal(Graphics window)
    {
        setCursor(c1);
        endSound();
        int high;
        try 
        {
            high = read();
        }
        catch (IOException ioe)
        {
             throw new RuntimeException(ioe);
        }
        window.setColor(Color.BLACK);
        window.fillRect(0, 0, 800, 600);
        window.setColor(Color.GREEN);
        window.setFont(new Font("Arial", Font.BOLD, 100)); 
        window.drawString("Score: "+countScore(), 160, 130);
        
        if(high >= countScore())
        {
            window.drawString("High Score: "+high, 50, 330);
        }
        else
        {
            window.drawString("High Score: "+countScore(), 50, 330);
            try 
            {
                save();
            }
            catch (IOException ioe)
            {
                 throw new RuntimeException(ioe);
            }
        }

    }
    public void paintPause(Graphics window)
    {
        window.drawImage(pauseScreen, 0, 0, this);
        setCursor(c1);
        
        
    }
    public int countScore()
    {
        int num = alien1death + alien2death + alien3death + alien4death + alien5death;
        return num;
    }
    public void playgun() 
    {
        
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("laser.wav")));
            clip.start();
            clip.loop(0);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    
    }
    public void hitsound() 
    {
        
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("hit.wav")));
            clip.start();
            clip.loop(0);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    
    }
    public void endSound() 
    {
        
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("end.wav")));
            clip.start();
            clip.loop(0);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    
    }
    public void musicSound() 
    {
        
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("game.wav")));
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    
    }

    public void keyPressed(KeyEvent e) 
    { 
        if (e.getKeyCode()== 10)
        {
            canStory = true;
            canStart = false; 

            repaint();
        }
        if (e.getKeyCode()== 80)
        {
            canPause = true;
            canPlay1 = false;
            

            repaint();
        }
        if (e.getKeyCode()==32)
        {

        }

        
        System.out.println(e.getKeyCode());
        repaint();
    } 
    public void keyReleased(KeyEvent e) 
    { 

    } 
    public void keyTyped(KeyEvent e) 
    { 
          
    } 
    public void mousePressed(MouseEvent e) 
    { 
        mousex= e.getX(); 
        mousey= e.getY(); 
        System.out.println(mousex+"//"+mousey);
        if(canStory == true)
        {
            if(mousex>670&&mousex<770&&mousey>560&&mousey<590)
            { 
                canPlay1 = true;
                canPlay = true;
                canStory = false;
            }
            if(mousex>550&&mousex<650&&mousey>560&&mousey<590)
            {
                canStory = false;
                canStart = true; 
                repaint();
            }
        } 
        if(canPause)    
        {
            canShoot = false;
            if(mousex>244&&mousex<525&&mousey>124&&mousey<163)
            { 
                canPause = false;
                canPlay1 = true;
                repaint();
            }
            if(mousex>267&&mousex<498&&mousey>206&&mousey<242)
            {
                //canPause = false;
                canStory1 = true;
                canPause = false;
                repaint();
                

            }
            if(mousex>243&&mousex<525&&mousey>286&&mousey<324)
            {
                canPause = false;
                canLevel = true;           
            }
            if(mousex>308&&mousex<495&&mousey>359&&mousey<398)
            {

            }
            if(mousex>319&&mousex<485&&mousey>439&&mousey<480)
            {
                System.exit(0);
            }   
        } 
        if(canStory1)
        {
            if(mousex>601&&mousex<713&&mousey>485&&mousey<562)
                {
                    canStory1 = false;
                    canPause = false;
                    repaint();
                }
        }
        if(canLevel)
        {
            if(mousex>62&&mousex<157&&mousey>172&&mousey<268)
            {
                canLevel = false;
                canPause = false;
                canPlay1 = true;

                canPlay2 = false;
                canPlay3 = false;
                canPlay4 = false;
                canPlay5 = false;

                repaint();
            }
            if(mousex>243&&mousex<348&&mousey>174&&mousey<260)
            {
                canLevel = false;
                canPause = false;
                canPlay2 = true;

                canPlay1 = false;
                canPlay3 = false;
                canPlay4 = false;
                canPlay5 = false;

                repaint();
            }
            if(mousex>424&&mousex<524&&mousey>172&&mousey<268)
            {
                canLevel = false;
                canPause = false;
                canPlay3 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay4 = false;
                canPlay5 = false;

                repaint();
            }
            if(mousex>605&&mousex<705&&mousey>174&&mousey<260)
            {
                canLevel = false;
                canPause = false;
                canPlay4 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay3 = false;
                canPlay5 = false;


                repaint();
            }
            if(mousex>69&&mousex<166&&mousey>331&&mousey<419)
            {
                canLevel = false;
                canPause = false;
                canPlay5 = true;

                canPlay1 = false;
                canPlay2 = false;
                canPlay3 = false;
                canPlay4 = false;


                repaint();
            }
        }
        if(canPlay1||canPlay2||canPlay3||canPlay4||canPlay5)
        {
 
            canShoot2.set(count,true);
            playgun();
            count++;
            

            repaint();

        } 
    } 
    public void mouseReleased(MouseEvent e) 
    { 
        if(canPlay1||canPlay2||canPlay3||canPlay4||canPlay5)
        {   
            canShoot = true;
            
            repaint();
            
        }
    } 
    public void mouseClicked(MouseEvent e) 
    { 
    } 
    public void mouseEntered(MouseEvent e) 
    { 
         
    } 
    public void mouseExited(MouseEvent e) 
    { 
        
    } 
    public void save() throws IOException
    {
        PrintWriter writer = new PrintWriter(new FileWriter(System.getProperty("user.dir")+"/score.txt")); 
        //System.out.println(System.getProperty("user.dir"));
        writer.print(""+countScore()); 
        writer.close();
        
    }
    public int read() throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/score.txt")); 
        String text = in.readLine(); 
        in.close();
        System.out.println(text);
        return Integer.parseInt(text);

        //repaint();
    }
}