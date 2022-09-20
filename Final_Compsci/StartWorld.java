import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Start Menu of the game
 * 
 * @author Kelvin Ying
 * @version 1
 */
public class StartWorld extends World
{
    GreenfootSound backgroundMusic = new GreenfootSound("Kingdom Rush OST - Main Menu Theme(1).wav");
    private static int BGvolume, SFXvolume;
    /**
     * Constructor for objects of class StartWorld.
     * 
     */
    public StartWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        this.addObject(new Button("Start Game"), 300,260);
        this.addObject(new Button("Instructions"), 300,310);
        this.addObject(new Button("BG music"), 63,14);
        this.addObject(new volume("BG", true), 125,68);
        this.addObject(new volume("BG",false), 29,67);
        BGvolume = 50;
    }
    public void act()
    {
        setUp();
    }

    public void setUp()
    {
        backgroundMusic.setVolume(BGvolume);
    }

    public void stopped()
    {
        backgroundMusic.pause();
    }

    public void started()
    {
        backgroundMusic.playLoop();
    }
    
    public static void setBG(int num)
    {
        if(BGvolume < 100)
        {
            BGvolume += num;
        }        
    }
    public static void reduceBG(int num)
    {
        if( BGvolume > 0)
        {
            BGvolume -= num;
        }        
    }
    
    
    public static int returnBG()
    {
       return BGvolume;
    }
}
