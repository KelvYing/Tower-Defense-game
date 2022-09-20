import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An interface class that display all the simulator information
 * 
 * @author Weiming Quan 
 * @version 1.0 - 4/20
 */
public class Interface extends Actor
{
    World world = getWorld();
    // need a day night cycle
    // add a clock
    // need to display how much money we have
    // need to display the number of current houses out of maximum houses
    //need to diaplay how many huiman alive
    private GreenfootImage dataBar; 
    private GreenfootImage textImage;
    private String text;
    
    private Color backGround;
    private Color textColor;
    private Font textFont;
    
    static Integer hour;
    static Integer second;
    private String clockText;
    private static int actNum;
   
    
    //the only purpose of this constructor is to get rid of an error for dayNight()
    public Interface()
    {
        
    }
    
    /**
     * Set up the basic interface, the color, font, basic data, and etc.
     * 
     */
    public Interface (int width)
    {
        resetStaticValues();
        dataBar = new GreenfootImage (width, 30);
        backGround = new Color (0,0,0);
        textColor = new Color (255, 255, 255);
        textFont = new Font ("Courier New", 24);
        
        //set the background color and font
        dataBar.setColor (backGround);
        dataBar.fill();
        dataBar.setFont (textFont);

        setImage(dataBar);
        
        actNum = 0;
        hour = new Integer (0);
        second = new Integer (0);
    }
    
    private void resetStaticValues()
    {
        //Human.numHumans = 0;
        //Human.totalMoney = 0;
        //Structure.numHouses = 0;
        
    }
    
    /**
     * Continuously update the clock, as well as the text in the interface
     */
    public void act() 
    {
        clock();
        updateText("Money: " + ((MyWorld)getWorld()).totalGold() + "   # lives: " + ((MyWorld)getWorld()).lives() + "   " + clockText);
    }    
    
    
    /**
     * Update the data bar into different numbers about the simulator
     * 
     */
    public void updateText(String text)
    {
        this.text = text;
        textImage = new GreenfootImage (text, 25, textColor, backGround);
        
        //add the text image on top of the background, and center it
        dataBar.drawImage (textImage, dataBar.getWidth()/2 - textImage.getWidth()/2, dataBar.getHeight()/2 - textImage.getHeight()/2);
        setImage (dataBar);
    }
    
    /**
     * Create a miliarty clock for the simulation, 
     * every 5 ticks in game is a minute in game
     * 
     */
    public void clock()
    {
        String minuteText, hourText;
        
        actNum++;
        //every 60 ticks is 1 minute in game
        if (actNum >= 60)
        {
            second++;
            actNum = 0;
        }
        if (second >= 60)
        {
            hour++;
            second = 0;
        }
        if (hour >= 24)
        {
            hour = 0;
        }
        
        //make sure when the time is in single digit, we add a 0 in front
        if (second >= 0 && second <= 9)
        {
            minuteText = "0" + second.toString();
        }
        else
        {
            minuteText = second.toString();
        }
        if (hour >= 0 && hour <= 9)
        {
            hourText = "0" + hour.toString();
        }
        else
        {
            hourText = hour.toString();
        }
        
        //create the military clock
        clockText = hourText + ":" + minuteText;
    }
    
    public static int actNum()
    {
       return actNum; 
    }
}
