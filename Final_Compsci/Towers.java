import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.*;
/**
 *Towers superclass that is build to kill the enemies
 * @author Andrew Wang
 * @version 2
 */
public class Towers extends Actor
{
    /**
     * Act - do whatever the Towers wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static int totalMoney;
    static int RELOAD = 50;
    int reload = 0;
    int range = 500;

    public void addedToWorld(World w) {

        for (Actor obj : getWorld().getObjects(BuildingMenu.class))
        {
            if(obj!=null && obj.getWorld()!= null)
            {
                getWorld().removeObject(obj);
            }

        }

    }

    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        reload -= 1;

        if(reload < 0) {
            reload = RELOAD;
            shoot();
        }
        sell();
    }    

    private void shoot() {
        EnemyContainer enemies = ((MyWorld)getWorld()).getEnemyContainer();

        Enemy target = enemies.getFurthestEnemyWithinRange(getX(), getY(), range);

        if(target == null) return;

        double angle = getAngle(getX(), target.getX(), getY(), target.getY());

        getWorld().addObject(new Projectile(angle), getX(), getY());
    }

    private static float getAngle(int originX, int destinationX, int originY, int destinationY) {
        return (float) Math.atan2((destinationY - originY), (destinationX - originX));
    }
    public void sell()
    {
        if(Greenfoot.mouseClicked(this))
        {
            getWorld().addObject(new BuildingMenu("S"), getX()-50, getY());
        }
        
    }

    public Towers()
    {
        totalMoney = 0;
    }  

    public static int totalMoney()
    {
        return totalMoney;
    }
}
