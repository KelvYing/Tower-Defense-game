import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * Superclass of the enemies
 * 
 * @author Andrew Wang, Kelvin Ying 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    private int hitpoints = 100,
    
    routeIndex = 0;
    public int speed = 1;
    private int curr = 0;
    private double distanceTravelled = 0;
    private int wait = 40;
    private Point destination;
    Queue<PathNodes> nodes = new LinkedList();
    private boolean targeted = false; 
    protected int freeze = 0;
    public Enemy() {
        GreenfootImage image = getImage();
        image.scale(40, 50);
    }

    public void addedToWorld(World w) {
        
        ((MyWorld)getWorld()).getEnemyContainer().add(this);
        
        this.nodes = ((MyWorld)getWorld()).makeQueue();
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(1);
        wait --;
        Pathing();
    }    
    /**
     *pathing to move to a node on the queue
     * 
     */
    public void Pathing()
    {           
            Actor a = nodes.peek();
            if(a != null)
            {
                turnTowards(a.getX(), a. getY());
                targeted = true;
            }        
    }
    /**
     * remove the first element of the queue 
     * 
     */

    public void setList()
    {
        if(!(this.nodes.size()<= 0))
        {
            if(wait<=0)
            {
                this.nodes.remove();
                targeted = false;
                wait = 40;
            }
        }

    }
    
    public void moveTo(int x, int y, int amount) {
        int currentX = getX();
        int currentY = getY();

        float angle = getAngle(currentX, x, currentY, y);

        int xMove = (int)(Math.cos(angle) * (float) speed);
        int yMove = (int)(Math.sin(angle) * (float) speed);

        setLocation(currentX + xMove, currentY + yMove);

        double dist = Math.sqrt(Math.pow(xMove, 2) + Math.pow(yMove, 2));

        this.distanceTravelled += dist;

        this.setRotation((int)(angle * 180 / Math.PI));
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void damage(int damage) {
        hitpoints -= damage;
        if(hitpoints <= 0) 
            die();
    }

    public void die() {
        ((MyWorld)getWorld()).getEnemyContainer().remove(this);
        getWorld().removeObject(this);
    }

    private static float getAngle(int originX, int destinationX, int originY, int destinationY) {
        return (float) Math.atan2((destinationY - originY), (destinationX - originX));
    }

    public void setSpeed(int num)
    {
        speed = num;
    }
/**
     * method to unfreeze enemy from the ice attack
     * 
     */
    private void unfreeze()
    {
        if(speed ==0)
        {
            freeze ++;
        }
        if(freeze >= 180)
        {
            setSpeed(1);
            freeze =0;
        }
    }
}
