import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * end point of the map. If an enemy touches it, you will lose lives
 * 
 * @author Kelvin Ying
 * @version 2
 */
public class End extends PathNodes
{
    /**
     * Act - do whatever the End wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public End()
    {
        GreenfootImage image = getImage();
        image.setTransparency(255);
    }
    public void act() 
    {
        // Add your action code here.
        Enemythrough();
    }    
    /**
     * if enemy touches, remove life from world
     * 
     */
    public void Enemythrough()
    {
        ArrayList<Enemy> gone = (ArrayList)getObjectsAtOffset(0,0,Enemy.class);
            if (gone.size()> 0)
            {
                for(int i =0; i < gone.size(); i ++)
                {
                    if(gone.get(i)!= null &&gone.get(i).getWorld()!= null)
                    {
                        ((MyWorld)getWorld()).reduceLives();
                        getWorld().removeObject(gone.get(i));
                    }                   
                }               
            }
    }
}
