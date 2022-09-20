import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Volume button of the game. In the start menu, click on the + or - to change volume
 * 
 * @author Kelvin Ying
 * @version 2
 */
public class volume extends Interface
{
    /**
     * Act - do whatever the volume wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean volumeIncrease;
    GreenfootImage myImage;
    private String type;
    public volume(String type , boolean increase)
    {
        volumeIncrease = increase; 
        if(volumeIncrease == true)
        {
            myImage = new GreenfootImage("Plus.png");
            setImage(myImage);
        }else
        {
            myImage = new GreenfootImage("Minus.png");
            setImage(myImage);
        }
        this.type = type;
    }

    public void act() 
    {
        // Add your action code here.
        click();
    }    
    /**
     * if clicked increase or decrease the volume
     * 
     */
    public void click()
    {
        if(Greenfoot.mouseClicked(this)&& volumeIncrease == true&& type =="BG")
        {
            ((StartWorld)getWorld()).setBG(10);
        }
        else if(Greenfoot.mouseClicked(this)&& volumeIncrease == false&& type =="BG")
        {
            ((StartWorld)getWorld()).reduceBG(10);
        }
    }
}
