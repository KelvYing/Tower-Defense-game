import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;
/**
 * Game world of the project. This is where the gameplay is held. 
 * 
 * @author Kelvin Ying
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    GreenfootSound backgroundMusic = new GreenfootSound("Kingdom Rush OST - Wasteland Battle.wav");

    private int gridLength, gridWidth;
    static int lives;
    EnemyContainer enemies;
    private Interface dataBar;
    Start start = new Start();
    End end = new End();
    private boolean gameOver = false;
    private int spawnTime, totalGold;
    private int acts;
    private int GoblinRate, TrollRate, WolfRate;
    private int multiplier = 0;
    private int bgVolume;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        GreenfootImage bg = new GreenfootImage("Finished map.png");
        bg.scale(getWidth(), getHeight());
        bgVolume = StartWorld.returnBG();
        setBackground(bg);
        backgroundMusic.setVolume(bgVolume);
        backgroundMusic.playLoop();
        gridLength = 76;
        gridWidth = 50;
        int[][]gameMap = {{2,0,1,0,0,0},
                {0,1,2,0,2,0},
                {2,1,1,1,1,1},
                {0,2,0,2,0,1},
                {0,0,0,2,0,1},
                {0,0,0,0,1,2},
                {0,0,0,2,1,1}};
        for(int i = 0; i < 7; i ++)
        {
            for(int x = 0; x < 6; x ++)
            {
                if(gameMap[i][x] == 2)
                {
                    addObject(new Buildingnodes(),gridLength+(x*gridLength), gridWidth+(i*gridWidth));
                }

            }
        }

        addObject(end, 580, 350);
        addObject(start, 275, 30);
        lives = 20;
        enemies = new EnemyContainer();

        dataBar = new Interface (getWidth());
        addObject (dataBar, 300, 385);
        dataBar.updateText ("Welcome to Kingdom defense");
        spawnTime = 120;
        acts = 1;
        GoblinRate = 250;
        TrollRate = 600;
        WolfRate = 400;
        
        totalGold = 300;
    }
    /**
     * Makes the queues for pathfinding to be distrubuted to the enemies
     * when they spawn
     * 
     */
    public Queue<PathNodes> makeQueue()
    {
        int[][]gameMap = {{2,0,1,0,0,0},
                {0,1,2,0,2,0},
                {2,1,1,1,1,1},
                {0,2,0,2,0,1},
                {0,0,0,2,0,1},
                {0,0,0,0,1,2},
                {0,0,0,2,1,1}};
        Queue<PathNodes> nodes = new LinkedList<>();
        for(int i = 0; i < 7; i ++)
        {
            for(int x = 0; x < 6; x ++)
            {
                if(gameMap[i][x] == 1)
                {
                    PathNodes pathnodes = new PathNodes();
                    addObject(pathnodes,gridLength+(x*gridLength), gridWidth+(i*gridWidth));
                    nodes.add(pathnodes);
                }

            }
        }
        return nodes;
    }

    public void act()
    {
        spawn();
        clickAway();
        if(!gameOver)
        {
            acts ++;
        }

        gameOver();
        difficulty();
    }
    /**
     * Increase the spawn rate of enemies over time
     * 
     */
    public void difficulty()
    {
        if(acts % 1800 == 0)
        {
            multiplier++;
        }
        GoblinRate = 250 - multiplier* 20;
        TrollRate = 600- multiplier* 20;
        WolfRate = 400- multiplier* 20;
    }
    /**
     * When you lose the game
     * 
     */
    public void gameOver()
    {
        if (lives <=0&& !gameOver) {//only an example;
            gameOver = true;
            this.removeObjects(this.getObjects(null)); //removes all the objects in the world;
            this.addObject(new Button("You survived for: "), 300,100);
            this.addObject(new Button(acts/60 + " seconds"), 300,140);

        }
        if(gameOver)
        {
            this.addObject(new Button("Back to Menu"), 300,260);

        }
    }
    /**
     * Spawner for spawning enemies
     * 
     */
    public void spawn()
    {
        if(!gameOver)
        {
            if(( acts %GoblinRate) ==0)
            {
                addObject(new Goblin(),start.getX(), start.getY());
            }
            if((acts %TrollRate ) == 0)
            {
                addObject(new Troll(),start.getX(), start.getY());
            }
            if((acts% WolfRate) == 0)
            {
                addObject(new Wolf(),start.getX(), start.getY());
            }
        }

    }
    /**
     * Closes menues when click away from them
     * 
     */
    public void clickAway()
    {
        if (Greenfoot.mouseClicked(this))
        {
            for (Actor obj : getObjects(BuildingMenu.class))
            {
                if(obj!=null && obj.getWorld()!= null)
                {
                    for(Actor obje : getObjectsAt(obj.getX(),obj.getY()+30,Buildingnodes.class))
                    {
                        Buildingnodes te= (Buildingnodes) obje;
                        if(te!= null)
                        {
                            te.appear();
                        }
                    }
                    removeObject(obj);
                }
            }
        }
    }

    public EnemyContainer getEnemyContainer() {
        return enemies;
    }

    public int lives()
    {
        return lives; 
    }

    public void reduceLives()
    {
        lives --;
    }

    public int totalGold()
    {
        return totalGold;
    }

    public void addGold(int num)
    {
        totalGold += num;
    }

    public void stopped()
    {
        backgroundMusic.pause();
    }

    public void started()
    {
        backgroundMusic.playLoop();
    }

}
