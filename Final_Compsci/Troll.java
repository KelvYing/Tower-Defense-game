import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 *Strongest enemy type. The hardest enemy
 * 
 * @author Andrew Wang 
 * @version 2
 */
public class Troll extends Enemy
{
    private int hitpoints = 500,
    speed = 1,
    routeIndex = 0;
    private int curr = 0;
    private double distanceTravelled = 0;
    private int wait = 40;
    private Point destination;
    Queue<PathNodes> nodes = new LinkedList();
    private boolean targeted = false; 
    public Troll() {
        GreenfootImage image = getImage();
        image.scale(40, 50);
    }

    public void addedToWorld(World w) {

        ((MyWorld)getWorld()).getEnemyContainer().add(this);

        this.nodes = ((MyWorld)getWorld()).makeQueue();
    }

    public void Pathing()
    {           
        Actor a = nodes.peek();
        if(a != null)
        {
            turnTowards(a.getX(), a. getY());
            targeted = true;
        }        
    }

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
    public void setSpeed(int num)
    {
        speed = num;
    }

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        wait --;
        Pathing();
        unfreeze();
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
        {
            die();
        }
    }

    public void die() {
        ((MyWorld)getWorld()).addGold(100);
        ((MyWorld)getWorld()).getEnemyContainer().remove(this);
        getWorld().removeObject(this);
    }

    private static float getAngle(int originX, int destinationX, int originY, int destinationY) {
        return (float) Math.atan2((destinationY - originY), (destinationX - originX));
    }
    private void unfreeze()
    {
        if(speed ==0)
        {
            freeze ++;
        }
        if(freeze >= 90)
        {
            setSpeed(1);
            freeze =0;
        }
    }
}
