import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The instructions world so that players know what to do
 * 
 * @author Kelvin YingKelvin Ying 
 * @version 1
 */
public class Instructions extends World
{

    /**
     * Constructor for objects of class Instructions.
     * 
     */
    public Instructions()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        this.addObject(new Button("Back to Menu"), 300,360);
    }
}
