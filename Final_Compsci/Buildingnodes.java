import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Building nodes of the game. This is where towers are build and marked
 * 
 * @author Kelvin Ying
 * @version 2
 */
public class Buildingnodes extends Nodes
{
    /**
     * Act - do whatever the Buildingnodes wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private boolean build = false;
    public Buildingnodes()
    {
        GreenfootImage image = getImage();
        image.scale(70, 50);
    }

    public void act() 
    {
        // Add your action code here.
        building();
        hide();
    }    
/**
     * click on will remove the building menus in the world and reset the building nodes 
     * 
     */
    public void building()
    {
        if(Greenfoot.mouseClicked(this)&& !build)
        {
            for (Actor obj : getWorld().getObjects(BuildingMenu.class))
            {
                if(obj!=null && obj.getWorld()!= null)
                {
                    for(Actor obje : getWorld().getObjectsAt(obj.getX(),obj.getY()+30,Buildingnodes.class))
                    {
                        Buildingnodes te= (Buildingnodes) obje;
                        if(te!= null)
                        {
                            te.appear();
                        }
                    }
                    getWorld().removeObject(obj);
                }
            }
            getWorld().addObject(new BuildingMenu("Wiz"), getX()-30, getY());
            getWorld().addObject(new BuildingMenu("normal"), getX(), getY()-30);
            getWorld().addObject(new BuildingMenu("Ice"), getX()+30, getY());
            List<Actor> actors = new ArrayList<Actor>();
            build = true;
        }

    }
    /**
     * turn invisable if build on
     * 
     */
    public void hide()
    {
        if(build)
        {
            GreenfootImage image = getImage();
            image.setTransparency(0);
        }else if(!build)
        {
            GreenfootImage image = getImage();
            image.setTransparency(255);
        }
    }

    public void appear()
    {
        build = false;
    }
}
