import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * VMain pathing method of the enemies to move around the map
 * @author Kelvin Ying
 * @version 2
 */
public class PathNodes extends Nodes
{
    /**
     * Act - do whatever the PathNodes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int Cost = 0;
    

    public PathNodes()
    {
        GreenfootImage image = getImage();
        image.setTransparency(0);
    }
    public void act() 
    {
        // Add your action code here.
        
        Enemy enemy = (Enemy) getOneObjectAtOffset(0, 0, Enemy.class);
        if(enemy != null)
        {
            enemy.setList();
            
        }
    }    
}
